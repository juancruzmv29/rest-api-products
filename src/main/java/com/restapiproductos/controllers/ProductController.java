package com.restapiproductos.controllers;


import com.restapiproductos.models.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ProductController {


    // Agregamos el servicio para realizar el crud


    // Obtener los productos de la api
    @GetMapping("/products")
    public List<Producto> obtenerProductos() {
        return null;
    }

    // Obtener un producto por nombre
    @GetMapping("/products/{id}")
    public ResponseEntity<?> obtenerProducto(@RequestBody Producto producto, @PathVariable String nombre) {

        Map<String, Object> response = new HashMap<>();


        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);

    }



    // Para publicar un producto
    @PostMapping("/products")
    public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Para actualizar un producto
    @PutMapping("/products/{id}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    // Para eliminar un producto
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(@RequestBody Producto producto, @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
