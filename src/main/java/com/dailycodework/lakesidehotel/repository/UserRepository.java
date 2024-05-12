package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD

import java.util.Optional;

/**
 * @author Simpson Alfred
 */


=======
import java.util.Optional;

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

<<<<<<< HEAD
   Optional<User> findByEmail(String email);
=======
    Optional<User> findByEmail(String email);
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
