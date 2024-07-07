package com.restapiproductos.controllers;


import com.restapiproductos.models.Producto;
import com.restapiproductos.services.Impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductService service;




    // Obtener los productos de la api
    @GetMapping("/products")
    public List<Producto> obtenerProductos() {
        return service.findAll();
    }

    // Obtener un producto por nombre
    @GetMapping("/products/{nombre}")
    public ResponseEntity<?> obtenerProducto(@PathVariable String nombre) {

        // Ponemos un hashMap para un mensaje y una respuesta
        Map<String, Object> response = new HashMap<>();
        Producto product = null;

        try {
            product = service.findByName(nombre);
        } catch (Exception e) {
            response.put("mensaje", "Producto por nombre no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "El producto se ha obtenido con exito");
        return  new ResponseEntity<Producto>(product, HttpStatus.OK);

    }

    // Buscamos un producto por id
    @GetMapping("/products/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {

        // Ponemos un hashMap para un mensaje y una respuesta
        Map<String, Object> response = new HashMap<>();
        Producto product = null;

        try {
            product = service.findById(id);
        } catch (Exception e) {
            response.put("mensaje", "Producto por nombre no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "El producto se ha obtenido con exito");
        return  new ResponseEntity<Producto>(product, HttpStatus.OK);

    }



    // Para publicar un producto
    @PostMapping("/products")
    public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();

        try {
            service.saveProduct(producto);
        } catch (Exception e) {
            response.put("mensaje", "El producto no se pudo guardar con exito");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto se ha agregado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Para actualizar un producto
    @PutMapping("/products/{id}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();


        try {
            Producto p = service.findById(id);

            p.setNombre(producto.getNombre());
            p.setCantidad(producto.getCantidad());
            p.setCategoria(producto.getCategoria());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.hayStock());
            service.saveProduct(p);
        } catch (Exception e) {
            response.put("mensaje", "El producto no se pudo actualizar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto se ha actualizado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    // Para eliminar un producto
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            service.deleteProduct(id);
        } catch (Exception e) {
            response.put("mensaje", "El producto no se pudo eliminar correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto se ha eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
