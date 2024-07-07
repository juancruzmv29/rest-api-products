package com.restapiproductos.services.Impl;

import com.restapiproductos.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    public List<Producto> findAll();


    public Producto findByName(String name);

    // ACA IMPLEMENTAMOS LA LOGICA, EN PRODUCTSERVICE LA FIRMAMOS
    public Producto findById(Long id);

    public void saveProduct(Producto producto);

    public void deleteProduct(Long id);

}
