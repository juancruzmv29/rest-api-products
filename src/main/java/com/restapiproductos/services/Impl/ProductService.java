package com.restapiproductos.services.Impl;

import com.restapiproductos.models.Producto;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ProductService {


    public Optional<Producto> findByName(String name);

    public void saveProduct(Producto producto);

    public void deleteProduct(Long id);

}
