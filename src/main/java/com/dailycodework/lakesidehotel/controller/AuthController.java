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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){

        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);


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

}
