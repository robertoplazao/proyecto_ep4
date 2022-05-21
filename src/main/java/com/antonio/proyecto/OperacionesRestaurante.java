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
            Mensaje("1-Crear Clientes");
            Mensaje("2-Listar Clientes");
            Mensaje("3-Registrar Orden");
            Mensaje("4-Crear Boleta");
            Mensaje("5-Listar Boletas");
            Mensaje("6-Listar Boletas de Cliente");
            Mensaje("7-Actualizar Cliente");
            Mensaje("8-Actualizar Boleta");
            Mensaje("9-Eliminar Cliente");
            Mensaje("10-Eliminar Boleta");
            Mensaje("11-Reporte Clientes");
            Mensaje("12-Reporte Boletas");
            Mensaje("13- Salir");
            Mensaje("Digite la opcion");
            opcion=Entrada.nextInt();
            
            switch(opcion) {
                case 1:
                    Mensaje(" A seleccionado Crear Cliente");
                    ClienteRestaurante.CrearDatosCliente(-1);
                    break;
                case 2:
                    Mensaje(" A seleccionado Listar Clientes");
                    ClienteRestaurante.ImprimirDatos();
                    break;
                    
                /* Nueva Funcionalidad */
                case 3:
                    Mensaje(" A seleccionado Registrar Orden");
                    BoletaRestaurante.RegistrarOrdenBoleta();
                    break;
                    
                case 4:
                    Mensaje(" A seleccionado Crear Boleta");
                    BoletaRestaurante.InsertarDatosBoleta();
                    break;
                    
                    
                case 5:
                    Mensaje(" A seleccionado Listar Boletas");
                    ClienteRestaurante.ImprimirBoletas();
                    break;
                    
                    /* muestra lista anidada boletas de cliente */
                case 6:
                    Mensaje(" A seleccionado Listar Boletas de Cliente");
                    ClienteRestaurante.ListarBoletasClienteMenu();
                    break;

                case 7:
                    Mensaje(" A seleccionado Actualizar Cliente");
                    ClienteRestaurante.ActualizarPersonaMenu();
                    break;
                case 8:
                    Mensaje(" A seleccionado Actualizar Boleta");
                    ClienteRestaurante.ActualizarBoletaMenu();
                    break;


                case 9:
                    Mensaje(" A seleccionado Eliminar Cliente");
                    ClienteRestaurante.EliminarPersonaMenu();
                    break;

                case 10:
                    Mensaje(" A seleccionado Eliminar Boleta");
                    BoletaRestaurante.EliminarBoletaMenu();
                    break;

                case 11:
                    Mensaje(" A seleccionado Reporte Clientes");
                    ClienteRestaurante.GenerarReporteClientes();
                    break;
                case 12:
                    Mensaje(" A seleccionado Reporte Boletas");
                    ClienteRestaurante.GenerarReporteBoletas();
                    break;
                    
                case 13:
                    Mensaje(" A seleccionado salir del menu, muchas gracias");
                    break;
            }
        } while(opcion != 12);
        
    }
    
    public static void Mensaje(String texto){
        System.out.println(texto);
    }

    public static int ValidarEntero(){
        Entrada.useDelimiter("\n");
        int Cadena=-1;
        do{
            try{
                Cadena=Entrada.nextInt();
                return Cadena;
                
            }catch(Exception e){
                Mensaje("Dato no válido "+e.toString());
            }
            
        }while(Cadena<0 || Cadena==0);
        
        
        return Cadena;
    }
    
    public static double ValidarDouble(){
        double Cadena;
        try{
            Cadena=Entrada.nextDouble();
            return Cadena;
            
        }catch(Exception e){
            Mensaje("Este dato no es válido");
        }
        return -1;
        
    }
    
    public static String ValidarAlfanumerico(){
        String cadena=Entrada.next();
        if(cadena.matches("[A-Za-z0-9]*"))
            return cadena;
        else
            Mensaje("Dato no válido");
        
        return null;
    }
}
