package com.dailycodework.lakesidehotel.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String profileUrl; // 카카오 프로필 이미지 URL
    private String loginType;  // "KAKAO", "GOOGLE" 등 로그인 유형 구분
    private boolean useAble;   // 계정 활성 상태

//    @OneToMany(mappedBy="guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<BookedRoom> bookings;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();




}
