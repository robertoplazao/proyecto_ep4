package com.antonio.proyecto;

/**
 * Clase para producto: Pescado
 */
public class Pescado extends Producto{
    public Pescado(){
        super(7000, 1000);
    }
    @Override
    public String getNombre(){
        return "Pescado";
    }
    
}
