package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.RoleRepository;

import com.dailycodework.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + "already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public void deleteUser(String email) {
        //이미 getUser()메소드 만들었으니까 써먹을 수 있음
        User theUser = getUser(email);
        if(theUser != null ) {
            userRepository.deleteByEmail(email);
        }
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user is not found"));
    }
}
