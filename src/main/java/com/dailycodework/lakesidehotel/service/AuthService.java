package com.dailycodework.lakesidehotel.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> signIn(String authorizeCode, String type); // 소셜 로그인 (카카오) 처리 메서드
}
