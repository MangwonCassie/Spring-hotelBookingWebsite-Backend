package com.dailycodework.lakesidehotel.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestAuthDto {
    private String nickName; // 카카오 API에서 제공되는 닉네임
    private String email; // 카카오 API에서 제공되는 이메일 (동의한 경우에만)
    private String profileUrl; // 카카오 API에서 제공되는 프로필 이미지 URL
    private String loginType; // "KAKAO" (로그인 방법)
    private boolean useAble; // 사용 가능한 상태
}

