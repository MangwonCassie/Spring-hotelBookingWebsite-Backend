package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String role);


    boolean existsByName(String role);
=======
public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(String roleUser);

    boolean existsByName(Role role);
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
