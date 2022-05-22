package com.antonio.proyecto;

/**
 * Clase abstracta padre de los productos del restaurante
 */
public abstract class Producto implements Imprimible {
    private double precio;
    private int calorias;

    /**
     * Nombre del Producto
     * @return 
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
     * @return 
     */
    public double getPrecio(){
        return this.precio;
    }
    /**
     * calorias del producto
     * @return 
     */
    public int getCalorias(){
        return this.calorias;
    }

    @Override
    public void Imprimir() {
        System.out.println("***"+getNombre());
        System.out.println("Calorias: " + getCalorias());
        System.out.println("Precio: " + getPrecio());
    }

    /**
     * Imprime los datos de los productos del restaurante
     */
    public static void ImprimirProductos() {
        System.out.println("Productos disponibles:");
        Ensalada ensalada = new Ensalada();
        ensalada.Imprimir();
        Sopa sopa = new Sopa();
        sopa.Imprimir();
        Yogur yogur = new Yogur();
        yogur.Imprimir();
    }
}
