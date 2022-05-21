package com.antonio.proyecto;

/**
 * Clase para producto: Ensalada
 */
public class Ensalada extends Producto {
    public Ensalada(){
        super(5000, 600);
    }
    @Override
    public String getNombre(){
        return "Ensalada";
    }
}
