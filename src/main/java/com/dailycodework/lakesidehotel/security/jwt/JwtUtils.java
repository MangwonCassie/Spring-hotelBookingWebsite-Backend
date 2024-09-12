package com.dailycodework.lakesidehotel.security.jwt;

import com.dailycodework.lakesidehotel.security.user.HotelUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${auth.token.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.token.expirationInMils}")
    private int jwtExpirationMs;


    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwtTokenForUser(Authentication authentication) {
        HotelUserDetails userPriciple = (HotelUserDetails) authentication.getPrincipal(); //casting
        List<String> roles = userPriciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder().setSubject(userPriciple.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        }catch(MalformedJwtException e){
            logger.error("Invalid jwt token : {} ", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired token : {} ", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("This token is not supported : {} ", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("No  claims found : {} ", e.getMessage());
        }
        return false;
    }


    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String generateRefreshTokenForUser(Authentication authentication) {
        HotelUserDetails userPrinciple = (HotelUserDetails) authentication.getPrincipal(); // casting
        List<String> roles = userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        // Refresh Token의 만료 시간을 설정 (예: 3일)
        long refreshTokenExpirationMs = 3 * 24 * 60 * 60 * 1000; // 3일 in milliseconds

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshTokenExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
