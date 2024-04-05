package com.dailycodework.lakesidehotel.security.jwt;


import com.dailycodework.lakesidehotel.security.user.HotelUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.jwtExpirationTime}")
    private int jwtExprirationTime;

    public String generateTokenForUser(Authentication authentication){
        HotelUserDetails userPriciple = (HotelUserDetails) authentication.getPrincipal(); //casting
        List<String> roles = userPriciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder().setSubject(userPriciple.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExprirationTime))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    private Key key() {
        return null;
    }

}

