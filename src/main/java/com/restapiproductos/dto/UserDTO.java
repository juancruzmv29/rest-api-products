package com.restapiproductos.dto;

import com.restapiproductos.models.Rol;

import java.util.List;


// Clase que voy a devolver para traer al usuario
public class UserDTO {


    private String nombre;
    private String email;
    private String username;
    private List<Rol> roles;

    public UserDTO(String nombre, String email, String username, List<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    public UserDTO() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
