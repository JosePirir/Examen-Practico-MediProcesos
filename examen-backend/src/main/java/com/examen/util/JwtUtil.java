package com.examen.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Clave secreta del servidor para firmar y validar tokens.
    // En un entorno real, esto vendría de una variable de entorno, no hardcodeado.
    private static final String SECRET = "examen-junior-clave-secreta-debe-tener-al-menos-32-caracteres";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRACION_MS = 1000 * 60 * 60; // 1 hora

    public static String generarToken(String usuario, String rol) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRACION_MS);

        return Jwts.builder()
                .setSubject(usuario)
                .claim("rol", rol)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validarYObtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}