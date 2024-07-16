package com.restapiproductos.services.Impl;

import com.restapiproductos.repository.UserRepository;
import com.restapiproductos.security.jwt.CustomDetailsService;
import com.restapiproductos.security.jwt.JwtUtil;
import com.restapiproductos.utils.ProductsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.restapiproductos.models.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager; // invocamos a authentication para el login para autenticar el email y password

    @Autowired
    private CustomDetailsService customDetailsService; // invocamos customDetailsService para obtener datos del usuario

    @Autowired
    private JwtUtil jwtUtil; // invocamps a jwtUtil para generar el token cuando nos logueamos


    // Aca vamos a retornar el usuario que vamos a buscar por email
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Aca vamos a usar el servicio de registrar al usuario
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            // Primero validamos si se valida el mapeo de la consulta osea de la request
            if(validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmail(requestMap.get("email")); // obtenemos por email
                // si el usuario es nulo guardamos lo ingresado y retornamos una respuesta personalizada
                if(Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap)); //
                    return ProductsUtils.getResponseEntity("Usuario registrado exitosamente", HttpStatus.CREATED);
                } else {
                    return ProductsUtils.getResponseEntity("Usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
                }
            } else {
                return ProductsUtils.getResponseEntity("Ha habido un error", HttpStatus.BAD_REQUEST);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // Si no pasa ninguna de las validaciones devolvemos que hubo un error
        return new ResponseEntity<String>("Ha habido un error, intente de nuevo", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Aca vamos a usar el servicio para loguearse
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            // Autenticamos el user con el requestMap, invocamos a Authentication obteniendo de la request el email y el password
            Authentication auth =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            // si se autentica
            if(auth.isAuthenticated()) {
                // obtenemos de customUserDetailsService validamos si el email es correcto
                if(customDetailsService.getUserDetail().getEmail().equalsIgnoreCase(requestMap.get("email"))) {
                    // retornamos una respuesta para el token y lo generamos con el email de getUserDetail y los roles
                    return new ResponseEntity<String>("{\"token\":\" "+ jwtUtil.generateToken(customDetailsService.getUserDetail().getEmail(),
                            customDetailsService.getUserDetail().getRoles().toString()) + "\"}",
                            HttpStatus.OK);
                } else {
                    // Si no se obtiene el email mandamos una mala respuesta
                    return new ResponseEntity<String>("{\"mensaje\":\" "+" Espere la aprobación del administrador" + "\"}", HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si no pasa ninguna retornamos una respuesta con un mensaje y una bad request
        return new ResponseEntity<String>("{\"mensaje\":\" "+" Credenciales incorrectas" + "\"}", HttpStatus.BAD_REQUEST);
    }





    private boolean validateSignUpMap(Map<String, String> requestMap) {

        if(requestMap.containsKey("nombre") && requestMap.containsKey("email") && requestMap.containsKey("username") && requestMap.containsKey("roles")) {
            return true;
        } else {
            return false;
        }

    }

    // Obtener el user de requestMap y lo pasamos a userDTO asi nos da el user con las anotaciones que queremos que muestre
    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setNombre(requestMap.get("nombre"));
        user.setEmail(requestMap.get("email"));
        user.setUsername(requestMap.get("username"));
        user.setRoles(requestMap.get("roles"));

        return user;
    }
}
