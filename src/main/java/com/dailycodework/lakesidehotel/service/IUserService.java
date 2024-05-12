package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.User;

import java.util.List;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

public interface IUserService {
=======
public interface IUserService {

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
<<<<<<< HEAD
=======


>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
