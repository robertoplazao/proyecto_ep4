package com.antonio.proyecto;

/**
 * Clase para producto: Pescado
 */
public class Sopa extends Producto{
    public Sopa(){
        super(7000, 1000);
    }
    @Override
    public String getNombre(){
        return "Sopa";
    }
    
}
