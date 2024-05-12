package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.RoleRepository;
<<<<<<< HEAD
import com.dailycodework.lakesidehotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
=======

import com.dailycodework.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
=======
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

<<<<<<< HEAD
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Role userRole = roleRepository.findByName("ROLE_USER").get();
=======


    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + "already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_ADMIN").get();
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

<<<<<<< HEAD
    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

=======

    @Transactional
    @Override
    public void deleteUser(String email) {
        //이미 getUser()메소드 만들었으니까 써먹을 수 있음
        User theUser = getUser(email);
        if(theUser != null ) {
            userRepository.deleteByEmail(email);
        }
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    }

    @Override
    public User getUser(String email) {
<<<<<<< HEAD
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
=======
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user is not found"));
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    }
}