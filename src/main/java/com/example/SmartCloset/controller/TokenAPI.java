package com.example.SmartCloset.controller;

import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenAPI {
    
    public String makeJWT(String id) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
            .setIssuer("Byunk")
            .signWith(key)
            .compact();
    }
}
