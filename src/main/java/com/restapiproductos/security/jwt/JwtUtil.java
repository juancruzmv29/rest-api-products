package com.restapiproductos.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {


    private String clave = "racing2014";

    // Extraemos el username, le pasamos el token
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Extraemos la fecha de expiracion, le pasamos el token
    public Date extractDateExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    // Extraer la informacion/data del token. Le pasamos el token, los claims en Function
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraemos toda la infor para firmar el token
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody();
    }

    // Para verificar si el token expiro
    private boolean isTokenExpired(String token) {
        return extractDateExpiration(token).before(new Date());
    }


    // Generamos el token encriptado
    public String generateToken(String username, String rol) {
        Map<String, Object> claims =new HashMap<>();
        claims.put("rol", rol);
        return createToken(claims, username);
    }

    // Creamos el token
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, clave).compact();
    }

    // Validamos el token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
