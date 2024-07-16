package com.restapiproductos.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


// CLASE PERSONALIZADA QUE NOS VA A PERMITIR MANEJAR RESPUESTAS HTTP
public class ProductsUtils {


    public ProductsUtils () {

    }

    public static ResponseEntity<String> getResponseEntity(String mensaje, HttpStatus httpStatus) {
        return new ResponseEntity<String>("Mensaje: "  + mensaje, httpStatus);
    }


}
