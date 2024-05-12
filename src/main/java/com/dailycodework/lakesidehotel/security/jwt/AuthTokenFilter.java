package com.dailycodework.lakesidehotel.security.jwt;

import com.dailycodework.lakesidehotel.security.user.HotelUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD

=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Component;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

=======
@Component
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HotelUserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Override
<<<<<<< HEAD
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateToken(jwt)){
=======
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = takeJwtFromClient(request);
        try {
            if (jwt != null && jwtUtils.validateToken(jwt)) {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
                String email = jwtUtils.getUserNameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                var authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
<<<<<<< HEAD
        }catch (Exception e){
=======
        } catch (Exception e) {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
            logger.error("Cannot set user authentication : {} ", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

<<<<<<< HEAD
    private String parseJwt(HttpServletRequest request) {
=======
    private String takeJwtFromClient(HttpServletRequest request) {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
<<<<<<< HEAD
}
=======

}

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
