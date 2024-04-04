package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.exception.RoleAlreadyExistException;
import com.dailycodework.lakesidehotel.model.Role;
import com.dailycodework.lakesidehotel.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<List<Role>>(roleService.getRoles(), FOUND);
    }

    public ResponseEntity<String> createRole(@RequestBody Role theRole) {
        try {
            roleService.createRole(theRole);
            return ResponseEntity.ok("New role created successfully");
        } catch (RoleAlreadyExistException re) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());


        }
    }
}
