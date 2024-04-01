package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByEmail(String email);
}
