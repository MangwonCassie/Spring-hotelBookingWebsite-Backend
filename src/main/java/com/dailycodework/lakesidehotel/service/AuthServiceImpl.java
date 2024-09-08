package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.controller.dto.RequestAuthDto;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.AuthDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthDAO authDAO;

    @Value("${kakao.client.id}")
    private String kakaoClientKey;

    @Value("${kakao.redirect.url}")
    private String kakaoRedirectUrl;

    @Value("${kakao.accesstoken.url}")
    private String kakaoAccessTokenUrl;

    @Value("${kakao.userinfo.url}")
    private String kakaoUserInfoUrl;

    private User getKakaoUserInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secure_resource", "true");

        HttpEntity<?> entity = new HttpEntity<>(requestBody,headers);

        ResponseEntity<String> response = restTemplate.postForEntity(kakaoUserInfoUrl,entity,String.class);

        try{
            Map<String, Object> responseMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            Map<String, Object> kakaoAccount = (Map<String, Object>) responseMap.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            // Kakao에서 가져온 정보를 User 객체로 변환
            return User.builder()
                    .email((String) kakaoAccount.get("email")) // 카카오에서 제공되는 이메일 (동의한 경우에만)
                    .firstName((String) profile.get("nickname")) // 카카오에서 제공되는 닉네임
                    .profileUrl((String) profile.get("profile_image_url")) // 카카오에서 제공되는 프로필 이미지 URL
                    .loginType("KAKAO") // 로그인 타입
                    .useAble(true) // 사용 가능 상태
                    .build();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 로그인 처리 로직
    @Override
    public ResponseEntity<?> signIn(String authorizeCode, String type) {
        if (!"kakao".equals(type)) {
            return ResponseEntity.status(400).body("Unsupported login type");
        }

        try {
            // Access Token 요청
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", kakaoClientKey);
            params.add("redirect_uri", kakaoRedirectUrl);
            params.add("code", authorizeCode);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(kakaoAccessTokenUrl, request, Map.class);
            String accessToken = (String) response.getBody().get("access_token");

            // 유저 정보 가져오기
            RequestAuthDto requestAuthDto = getKakaoUserInfo(accessToken);
            if (requestAuthDto == null) {
                return ResponseEntity.status(500).body("Failed to retrieve user information");
            }


            // 로그인 또는 회원가입 처리
            if (authDAO.checkUserExist(user.getEmail(), "KAKAO")) {
                return authDAO.login(user); // requestAuthDto 대신 User 객체 전달
            } else {
                authDAO.saveUser(user); // User 객체 저장
                return authDAO.login(user); // User 객체로 로그인
            }

        } catch (Exception e) {
            log.error("Error during Kakao login", e);
            return ResponseEntity.status(500).body("Error during Kakao login");
        }
    }
}
