package com.restapiproductos.services.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface UserService {


    // Servicio para registrarse como usuario de la api, le paamos la request
    public ResponseEntity<String> signUp(Map<String, String> requestMap);

    // Servicio para loguarse en la api, le pasamos la request
    public ResponseEntity<String> login(Map<String, String> requestMap);



}
