package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.KakaoLoginRequest;
import com.dailycodework.lakesidehotel.model.KakaoUser;
import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.request.LoginRequest;
import com.dailycodework.lakesidehotel.response.JwtResponse;
import com.dailycodework.lakesidehotel.security.jwt.JwtUtils;
import com.dailycodework.lakesidehotel.security.user.HotelUserDetails;
import com.dailycodework.lakesidehotel.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){

        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);


        HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));
    }

    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front.vercel.app", "http://localhost:5173/"})
    @PostMapping("/login/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KakaoLoginRequest kakaoLoginRequest){
        try{

            String code = kakaoLoginRequest.getToken();

            // 1. 인가 코드를 사용해 카카오에서 액세스 토큰 요청
            String tokenUrl = "https://kauth.kakao.com/oauth/token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", clientId);
            params.add("redirect_uri", redirectUri);
            params.add("code", code);
            params.add("client_secret", clientSecret);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            // 2. 액세스 토큰을 추출
            String accessToken = response.getBody().get("access_token").toString();


            // 3. 액세스 토큰을 사용해 사용자 정보 요청
            KakaoUser kakaoUser = userService.getKakaoUserInfo(accessToken);

            // 사용자 이메일을 통해 기존 사용자 확인 및 로그인 처리
            Optional<User> optionalUser = userService.findByEmail(kakaoUser.getEmail());
            User user = optionalUser.orElse(null);

            if (user == null) {
                // 사용자가 없으면 자동으로 회원가입 처리
                user = new User();
                user.setEmail(kakaoUser.getEmail());
                user.setFirstName(kakaoUser.getNickname()); // 카카오에서 제공한 닉네임을 사용자의 이름으로 설정
                user.setPassword(""); // 소셜 로그인에서는 일반적으로 비밀번호를 설정하지 않음 (하지만 필수적으로 필요한 경우 임시 비밀번호를 설정할 수 있음)
                userService.registerUser(user);
            }

            // 추가적인 로그인 처리 로직 (토큰 생성 등)

            // 사용자 인증 후 JWT 토큰 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getRoles());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtTokenForUser(authentication);

            // 사용자 정보와 JWT 토큰을 반환
            List<String> roles = user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .toList();

            // 콘솔에 로그 출력
            logger.info("KakaoUser Info: {}", kakaoUser);
            logger.info("User Info: {}", user);
            logger.info("Generated JWT: {}", jwt);


            return ResponseEntity.ok(new JwtResponse(
                    user.getId(),
                    user.getEmail(),
                    jwt,
                    roles
            ));
        } catch (RestClientException e) {
            logger.error("Failed to retrieve access token from Kakao", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve access token.");
        }
        }
    }


