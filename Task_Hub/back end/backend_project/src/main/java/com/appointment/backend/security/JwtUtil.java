 package com.appointment.backend.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String SECRET =
            "appointmentbookingprojectsecretkey123456789123456";

    private static final Key KEY =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes()
            );

    // Generate JWT Token
    public static String generateToken(
            String email,
            String role
    ) {

        return Jwts.builder()

                .setSubject(email)

                .claim("role", role)

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                + 1000 * 60 * 60 * 24
                        )
                )

                .signWith(
                        KEY,
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    // Validate JWT Token
    public static Claims validateToken(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(KEY)

                .build()

                .parseClaimsJws(token)

                .getBody();
    }

    // Extract Email
    public static String getEmail(
            String token
    ) {

        return validateToken(token)
                .getSubject();
    }

    // Extract Role
    public static String getRole(
            String token
    ) {

        return validateToken(token)
                .get("role", String.class);
    }
}