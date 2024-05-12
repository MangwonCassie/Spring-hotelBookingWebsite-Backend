package com.dailycodework.lakesidehotel.security.user;

import com.dailycodework.lakesidehotel.model.User;
import com.dailycodework.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */
@Service
@RequiredArgsConstructor
public class HotelUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
=======
@Service
@RequiredArgsConstructor
public class HotelUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        return HotelUserDetails.buildUserDetails(user);
    }
}
