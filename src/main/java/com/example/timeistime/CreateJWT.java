package com.example.timeistime;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;

public class CreateJWT {

    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(String email) {
        JwtBuilder builder = Jwts.builder();
        return builder
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
    }

    public static Claims validateToken(String token) {

        System.out.println(token);
        return   Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

    public static void checkLocalToken(String token) {
        if (token != null) {
            Claims claims = CreateJWT.validateToken(token);
            if (claims != null) {
                System.out.println("Token valid, user: " + claims.getSubject());
            } else {
                System.out.println("Invalid token.");
            }
        } else {
            System.out.println("No token available.");
        }
    }

}
