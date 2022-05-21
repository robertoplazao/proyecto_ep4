package com.antonio.proyecto;

/**
 * Clase para producto: Yogur
 */
public class Yogur extends Producto{
    public Yogur(){
        super(3000, 300);
    }
    @Override
    public String getNombre(){
        return "Yogur";
    }
    
}
