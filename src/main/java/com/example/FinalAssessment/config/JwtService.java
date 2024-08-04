package com.example.FinalAssessment.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static  final String KEY = "5ccab34e5db2bcd62559f668df44d7705ed329955ae22d9c78fd1070d0c5bb58";

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*1800))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){

        return extractClaim(token, Claims::getExpiration);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignIngKey(){
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}