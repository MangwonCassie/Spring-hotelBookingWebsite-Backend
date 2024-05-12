package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.model.User;

import java.util.List;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

public interface IRoleService {
=======
public interface IRoleService {

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
