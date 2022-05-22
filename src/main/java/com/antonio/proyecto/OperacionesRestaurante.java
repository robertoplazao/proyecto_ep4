package com.antonio.proyecto;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Clase principal para operaciones del restaurante
 */
public class OperacionesRestaurante {
    private static Scanner Entrada=new Scanner(System.in);
    
    public OperacionesRestaurante() { }

    /**
     * Muestra el menú principal de la aplicación
     * @throws ParseException
     */
    public static void Menu() throws ParseException {
        int opcion;
        do {
            System.out.println("1-Crear Clientes");
            System.out.println("2-Listar Clientes");
            System.out.println("3-Ver Productos");
            System.out.println("4-Registrar Orden");
            System.out.println("5-Crear Boleta");
            System.out.println("6-Listar Boletas");
            System.out.println("7-Listar Boletas de Cliente");
            System.out.println("8-Actualizar Cliente");
            System.out.println("9-Actualizar Boleta");
            System.out.println("10-Eliminar Cliente");
            System.out.println("11-Eliminar Boleta");
            System.out.println("12-Reporte Clientes");
            System.out.println("13-Reporte Boletas");
            System.out.println("14- Salir");
            System.out.println("Digite la opcion");
            opcion=Entrada.nextInt();
            
            switch(opcion) {
                case 1:
                    System.out.println(" A seleccionado Crear Cliente");
                    ClienteRestaurante.CrearDatosCliente(-1);
                    break;
                case 2:
                    System.out.println(" A seleccionado Listar Clientes");
                    ClienteRestaurante.ImprimirDatos();
                    break;
                    
                /* Nuevas Funcionalidades */
                case 3:
                    System.out.println(" A seleccionado Ver Productos");
                    Producto.ImprimirProductos();
                    break;
                case 4:
                    System.out.println(" A seleccionado Registrar Orden");
                    BoletaRestaurante.RegistrarOrdenBoleta();
                    break;
                    
                case 5:
                    System.out.println(" A seleccionado Crear Boleta");
                    BoletaRestaurante.InsertarDatosBoleta();
                    break;
                    
                    
                case 6:
                    System.out.println(" A seleccionado Listar Boletas");
                    ClienteRestaurante.ImprimirBoletas();
                    break;
                    
                    /* muestra lista anidada boletas de cliente */
                case 7:
                    System.out.println(" A seleccionado Listar Boletas de Cliente");
                    ClienteRestaurante.ListarBoletasClienteMenu();
                    break;

                case 8:
                    System.out.println(" A seleccionado Actualizar Cliente");
                    ClienteRestaurante.ActualizarPersonaMenu();
                    break;
                case 9:
                    System.out.println(" A seleccionado Actualizar Boleta");
                    ClienteRestaurante.ActualizarBoletaMenu();
                    break;


                case 10:
                    System.out.println(" A seleccionado Eliminar Cliente");
                    ClienteRestaurante.EliminarPersonaMenu();
                    break;

                case 11:
                    System.out.println(" A seleccionado Eliminar Boleta");
                    BoletaRestaurante.EliminarBoletaMenu();
                    break;

                case 12:
                    System.out.println(" A seleccionado Reporte Clientes");
                    ClienteRestaurante.GenerarReporteClientes();
                    break;
                case 13:
                    System.out.println(" A seleccionado Reporte Boletas");
                    ClienteRestaurante.GenerarReporteBoletas();
                    break;
                    
                case 14:
                    System.out.println(" A seleccionado salir del menu, muchas gracias");
                    break;
            }
        } while(opcion != 14);
        
    }
    
    /**
     * valida el ingreso de un valor de tipo int
     * @return el valor ingresado correctamente
     */
    public static int ValidarEntero(){
        Entrada.useDelimiter("\n");
        int Cadena=-1;
        do{
            try{
                Cadena=Entrada.nextInt();
                return Cadena;
                
            }catch(Exception e){
                System.out.println("Dato no válido "+e.toString());
            }
            
        }while(Cadena<0 || Cadena==0);
        
        
        return Cadena;
    }
    
    /**
     * valida el ingreso de un valor de tipo double
     * @return el valor ingresado correctamente
     */
    public static double ValidarDouble(){
        double Cadena;
        try{
            Cadena=Entrada.nextDouble();
            return Cadena;
            
        }catch(Exception e){
            System.out.println("Este dato no es válido");
        }
        return -1;
        
    }
}
