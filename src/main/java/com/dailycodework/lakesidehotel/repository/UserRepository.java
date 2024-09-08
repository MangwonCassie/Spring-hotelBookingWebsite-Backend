package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);



    // 이메일과 로그인 유형으로 사용자 존재 여부 확인
    boolean existsByEmailAndLoginType(String email, String loginType);

    Optional<User> findByEmail(String email);

    // 이메일과 로그인 유형으로 사용자 조회
    Optional<User> findByEmailAndLoginType(String email, String loginType);
}
