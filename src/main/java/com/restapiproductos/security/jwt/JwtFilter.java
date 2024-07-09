package com.restapiproductos.security.jwt;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Clase para validar el token
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomDetailsService customDetailsService;

    Claims claims = null;

    private String username;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Si la peticion coincide con las siguientes rutas hacemos algo y si no obtenemos algo para hacer algo
        if(request.getServletPath().matches("/user/login|user/forgotPassword|/user/signup")) {
            filterChain.doFilter(request, response);
        } else {
            // Capturamos de la consulta el header de Authorization
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;

            // Si la cabecera de autorizacion no es nula y empieza con Bearer, extraemos el token de authorizationHeader y completamos el username y los claims
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7); // recortamos la cadena y extraemos las primeras 7 caracteres
                username = jwtUtil.extractUsername(token); // extramos el nombre del token
                claims = jwtUtil.extractAllClaims(token); // extraemos la info del token
            }

            // Si el username no es null y ademas no esta autenticado hacemos algo
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Cargamos el username del userDetails
                UserDetails userDetails = customDetailsService.loadUserByUsername(username);
                // Validamos el token para los datos del usuario
                if (jwtUtil.validateToken(token, userDetails)) {
                    // Creamos el usuario con el token si es que esta validado
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    new WebAuthenticationDetailsSource().buildDetails(request);
                    // Al contexto de seguridad le seteamos la authenticacion de usernamePasswordAutheticacionToken
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            // Finalmente filtramos con filterChain la peticion y la respuesta
            filterChain.doFilter(request, response);
        }
    }

    public Boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("rol"));
    }

    public Boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("rol"));
    }

    public String getCurrentUsername() {
        return username;
    }
}
