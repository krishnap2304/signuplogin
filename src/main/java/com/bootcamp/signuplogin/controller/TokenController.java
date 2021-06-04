package com.bootcamp.signuplogin.controller;

import com.bootcamp.signuplogin.model.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class TokenController {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private int jwtExpirationInMs;

    @GetMapping("token")
    public ResponseEntity<Token>getToken(@RequestParam  String username){
        String token = Jwts.builder()
                .setSubject(username) // A random user ID
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return ResponseEntity.ok (new Token(token));
    }
}
