package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleUser);
}
