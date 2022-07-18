package com.example.SmartCloset.controller;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenAPI {
    
    public static String makeJWT(String id) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date now = new Date();
        return Jwts.builder()
            .setSubject(id)
            .setIssuedAt(now)
            .setIssuer("Byunk")
            .setExpiration(new Date(now.getTime() + 1000 * 60L * 60L * 1L))
            .signWith(key)
            .compact();
    }
}
