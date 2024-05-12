package com.dailycodework.lakesidehotel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import org.hibernate.annotations.ManyToAny;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Simpson Alfred
 */
=======

import java.util.Collection;
import java.util.HashSet;


>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
<<<<<<< HEAD
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();



=======
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
