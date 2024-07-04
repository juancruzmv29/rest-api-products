package com.restapiproductos.services.Impl;

import com.restapiproductos.models.Producto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Optional<Producto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void saveProduct(Producto producto) {

    }


    @Override
    public void deleteProduct(Long id) {

    }
}
