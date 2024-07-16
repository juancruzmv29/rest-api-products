package com.restapiproductos.controllers;


import com.restapiproductos.dto.UserDTO;
import com.restapiproductos.models.User;
import com.restapiproductos.services.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class AuthController {


    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> findAll() {
        return service.findAll();
    }


    // Vamos a retornar un userDTO en plano
    @GetMapping("/{email}")
    public UserDTO obtenerUserPorEmail(@PathVariable String email) {
        UserDTO userDTO = new UserDTO();
        try {
            User user = service.findByEmail(email);
            userDTO.setNombre(user.getNombre());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDTO;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap) {
        try {
            service.signUp(requestMap);
            return new ResponseEntity<String>("Se ha registrado exitosamente!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("No se ha podido registrar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        try {
            service.login(requestMap);
            return new ResponseEntity<String>("Se ha iniciado sesion correctamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("No se ha podido iniciar sesion", HttpStatus.BAD_REQUEST);
        }
    }


}
