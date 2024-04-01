package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;



    @Override
    public User registerUser(User user) {

        if (userRepository.existByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + "already exists");


        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void DeleteUser(String email) {

    }

    @Override
    public User getUser(String email) {
        return null;
    }
}
