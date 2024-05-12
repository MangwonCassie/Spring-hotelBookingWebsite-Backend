package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.exception.UserAlreadyExistsException;
import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.request.LoginRequest;
import com.dailycodework.lakesidehotel.response.JwtResponse;
import com.dailycodework.lakesidehotel.security.jwt.JwtUtils;
import com.dailycodework.lakesidehotel.security.user.HotelUserDetails;
import com.dailycodework.lakesidehotel.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.http.HttpStatusCode;
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

<<<<<<< HEAD

/**
 * @author Simpson Alfred
 */
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
<<<<<<< HEAD
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
=======
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest request){
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
<<<<<<< HEAD
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
=======
        String jwt = jwtUtils.generateTokenForUser(authentication);
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));
    }
<<<<<<< HEAD
=======



>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
