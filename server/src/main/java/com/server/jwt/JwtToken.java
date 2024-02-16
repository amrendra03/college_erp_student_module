package com.server.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken {

    private static final long JWT_TOKEN_VALIDITY = 1*60;

    private final String secret ="amrendrayadavsecretkeyforjwtauthenticationforthecollegeerpstudentmoduleselfworkproject";

    private Logger log = LoggerFactory.getLogger(JwtController.class);

    public String getUsernameFromToken(String token){
        log.info("At JwtToken getUsernameFromToken stage.");
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){

        return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        log.info("At JwtToken getClaimFromToken stage.");
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        log.info("At JwtToken getAllClaimsFromToken stage.");
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();

    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String , Object>claims,String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY *1000))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){

        log.info("Process JwtToken ValidationToken...");
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && ! isTokenExpired(token));
    }


}
