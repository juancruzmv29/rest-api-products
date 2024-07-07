package com.restapiproductos.exceptions;


import org.springframework.web.bind.annotation.ExceptionHandler;


public class ErrorPersonalizado extends RuntimeException{


    public ErrorPersonalizado(String mensaje) {
        super(mensaje);
    }

    public ErrorPersonalizado(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }


}
