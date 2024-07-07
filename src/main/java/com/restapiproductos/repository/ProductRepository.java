package com.restapiproductos.repository;

import com.restapiproductos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Producto, Long> {

    public Producto findByName(String name);


}
