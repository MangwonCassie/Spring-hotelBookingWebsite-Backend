package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.security.jwt.JwtUtils;
import com.dailycodework.lakesidehotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;


@Service
public class AuthDAOImpl implements AuthDAO {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final UserService userService; // userService 주입

    public AuthDAOImpl(UserRepository userRepository, JwtUtils jwtUtils, UserService userService) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    public ResponseEntity<?> login(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            if (existingUser.isUseAble()) {

                // User 객체로 인증 생성
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        existingUser, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

                // 토큰 생성
                String accessToken = jwtUtils.generateJwtTokenForUser(authentication);
                String refreshToken = jwtUtils.generateRefreshTokenForUser(authentication); // Refresh Token 따로 관리


                // 직접 ResponseEntity로 응답 반환
                return ResponseEntity.status(HttpStatus.OK)
                        .body(Map.of(
                                "accessToken", accessToken,
                                "refreshToken", refreshToken,
                                "name", existingUser.getFirstName() + " " + existingUser.getLastName(),
                                "profileUrl", existingUser.getProfileUrl(),
                                "status", "Success"
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.GONE)
                        .body("User has been deleted");
            }
        } else {
            // 사용자 존재하지 않으면 회원가입 처리
            saveUser(user);
            return login(user);
        }
    }

    @Override
    public boolean checkUserExist(String email, String loginType) {
        return userRepository.findByEmailAndLoginType(email, loginType).isPresent();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
