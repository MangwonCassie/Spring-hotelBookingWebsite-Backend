package com.dailycodework.lakesidehotel.repository;
import org.springframework.http.ResponseEntity;
import com.dailycodework.lakesidehotel.model.User;

public interface AuthDAO {

    ResponseEntity<?> login(User user);
    boolean checkUserExist(String email, String loginType);
    void saveUser(User user);



}
