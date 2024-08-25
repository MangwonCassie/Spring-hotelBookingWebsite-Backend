package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.KakaoUser;
import com.dailycodework.lakesidehotel.model.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);

    KakaoUser getKakaoUserInfo(String token);

    Optional<User> findByEmail(String email);
}
