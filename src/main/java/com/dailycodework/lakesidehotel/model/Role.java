package com.dailycodework.lakesidehotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
<<<<<<< HEAD

/**
 * @author Simpson Alfred
 */
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void assignRoleToUser(User user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

    public void removeUserFromRole(User user){
        user.getRoles().remove(this);
        this.getUsers().remove(user);

    }

    public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<User> roleUsers = this.getUsers().stream().toList();
            roleUsers.forEach(this :: removeUserFromRole);
        }
    }
    public  String getName(){
        return name != null? name : "";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
