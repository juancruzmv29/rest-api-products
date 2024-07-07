package com.restapiproductos.services.Impl;

import com.restapiproductos.exceptions.ErrorPersonalizado;
import com.restapiproductos.models.Producto;
import com.restapiproductos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    // Invocamos al repository
    @Autowired
    ProductRepository repository;


    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    public Producto findByName(String name) {

        List<Producto> productos = repository.findAll();

        Producto producto = null;

        for(Producto p : productos) {
            if(p.getNombre().equalsIgnoreCase(name)) {
                producto = p;
            } else {
                new ErrorPersonalizado("Producto con el nombre: " + name + " no encontrado");
            }
        }

        return producto;
    }

    // ACA IMPLEMENTAMOS LA LOGICA, EN PRODUCTSERVICE LA FIRMAMOS
    @Override
    public Producto findById(Long id) {

        Producto producto = repository.findById(id)
                .orElseThrow(() -> new ErrorPersonalizado("Producto no encontrado con el id " + id));

        return producto;

    }

    @Override
    public void saveProduct(Producto producto) {

        repository.save(producto);

    }


    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
