package com.antonio.proyecto;

/**
 * Clase abstracta padre de los productos del restaurante
 */
public abstract class Producto {
    private double precio;
    private int calorias;

    /**
     * Nombre del Producto
     */
    public abstract String getNombre();
    
    /**
     * Crea un producto con su precio y calorias
     * @param precio precio del producto
     * @param calorias numero de calorias en este alimento
     */
    public Producto(double precio, int calorias) {
        this.precio = precio;
        this.calorias = calorias;
    }

    /**
     * precio del producto
     */
    public double getPrecio(){
        return this.precio;
    }
    /**
     * calorias del producto
     */
    public int getCalorias(){
        return this.calorias;
    }
}
