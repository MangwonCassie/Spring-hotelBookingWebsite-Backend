package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.exception.RoleAlreadyExistException;
import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.RoleRepository;
import com.dailycodework.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
<<<<<<< HEAD
        String roleName = "ROLE_"+theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleRepository.existsByName(roleName)){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
=======
        String roleName = "ROLE_" + theRole.getName().toUpperCase();
        Role role = new Role(roleName); // 이미 one argument 있는 생성자 Role에 있음

        if (roleRepository.existsByName(role)) {
            throw new RoleAlreadyExistException(theRole.getName() + "role already exists");
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
<<<<<<< HEAD
        Optional<Role>  role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())){
=======
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

<<<<<<< HEAD
    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role>  role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(
                    user.get().getFirstName()+ " is already assigned to the" + role.get().getName()+ " role");
        }
        if (role.isPresent()){
=======

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent() && role.get().getUsers().contains(role.get())) {
          throw new UserAlreadyExistsException(
                  user.get().getFirstName()+"is already assigned to" + role.get().getName()+"role");
        }

        if(role.isPresent()){
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
            role.get().assignRoleToUser(user.get());
            roleRepository.save(role.get());
        }
        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
<<<<<<< HEAD
        role.ifPresent(Role::removeAllUsersFromRole);
        return roleRepository.save(role.get());
=======
        role.ifPresent(Role:: removeAllUsersFromRole);
        return null;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    }
}
