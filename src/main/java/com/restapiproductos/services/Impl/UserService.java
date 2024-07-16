package com.restapiproductos.services.Impl;

import com.restapiproductos.dto.UserDTO;
import com.restapiproductos.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {


    // Aca vamos a retornar el usuario que vamos a buscar por email
    User findByEmail(String email);

    List<User> findAll();

    // Servicio para registrarse como usuario de la api, le paamos la request
    public ResponseEntity<String> signUp(Map<String, String> requestMap);

    // Servicio para loguarse en la api, le pasamos la request
    public ResponseEntity<String> login(Map<String, String> requestMap);





}
