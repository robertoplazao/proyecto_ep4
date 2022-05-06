package com.antonio.proyecto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRestaurante {

    private String NombreCliente;
    private String RutCliente;
    private Date FechaN;
    private static ArrayList<ClienteRestaurante> LClienteRestaurante = new ArrayList<ClienteRestaurante>();

    /* lista anidada boletas del cliente */
    private ArrayList<BoletaRestaurante> LBoletaCliente=new ArrayList<BoletaRestaurante>();

    public ArrayList<BoletaRestaurante> getLBoletaCliente() {
        return LBoletaCliente;
    }

    public void EliminarBoleta(int CodigoBoleta) {
        ListIterator<BoletaRestaurante> i = LBoletaCliente.listIterator();
        while(i.hasNext()) {
            if(i.next().getCodigoBoleta() == CodigoBoleta) {
                i.remove();
            }
        }
    }

    public ClienteRestaurante(String NombreCliente, String RutCliente, Date FechaN) {
        this.NombreCliente = NombreCliente;
        this.RutCliente = RutCliente;
        this.FechaN = FechaN;

    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public String getRutCliente() {
        return RutCliente;
    }

    public Date getFechaN() {
        return FechaN;
    }

    public void setNombre(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public void setRutCliente(String RutCliente) {
        this.RutCliente = RutCliente;
    }

    public void setFechaN(Date FechaN) {
        this.FechaN = FechaN;
    }

    public void GuardarBoleta(BoletaRestaurante boleta) {
        BoletaRestaurante.Guardar(boleta);
        LBoletaCliente.add(boleta);
    }

    public static void Guardar(ClienteRestaurante cliente, int indiceCliente) {
        if(indiceCliente == -1) {
            LClienteRestaurante.add(cliente);
        } else {
        // si el cliente con este RUT ya existe, lo actualizamos
            LClienteRestaurante.set(indiceCliente, cliente);
        }
    }

    public static ClienteRestaurante BuscarPersona(String RutCliente) {
        for(ClienteRestaurante cliente : LClienteRestaurante) {
            if(cliente.RutCliente.equals(RutCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public static void ListarBoletasClienteMenu() {
        System.out.println("Digite el Rut Cliente");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        String RutCliente=Entrada.nextLine();
        ClienteRestaurante Cliente = ClienteRestaurante.BuscarPersona(RutCliente);
        BoletaRestaurante.ImprimirDatos(Cliente);
    }

    public static void EliminarPersona(String RutCliente) {
        // Primero borramos las boletas asociadas al cliente
        ClienteRestaurante cliente = BuscarPersona(RutCliente);
        for(BoletaRestaurante boleta : cliente.LBoletaCliente) {
            BoletaRestaurante.EliminarBoleta(boleta.getCodigoBoleta());
        }

        // Y luego borramos el cliente
        ListIterator<ClienteRestaurante> i = LClienteRestaurante.listIterator();
        while(i.hasNext()) {
            if(i.next().RutCliente.equals(RutCliente)) {
                i.remove();
            }
        }
    }

    public static void CrearDatosCliente(int indiceCliente) throws ParseException {
        String Nombre, Rut;
        Date Fecha;
        String Fecha2;

       Scanner Entrada=new Scanner(System.in);
       Entrada.useDelimiter("\n");

        System.out.println("Ingrese el Nombre Cliente:");
        Nombre = Entrada.nextLine();

        System.out.println("Ingrese el Rut Cliente:");
        Rut = Entrada.nextLine();

        System.out.println("Ingrese el Fecha(dd/MM/yyyy):");
        Fecha2 = Entrada.nextLine();

        Fecha = new SimpleDateFormat("dd/MM/yyyy").parse(Fecha2);
        Guardar(new ClienteRestaurante(Nombre, Rut, Fecha), indiceCliente);

        System.out.println("Dato de cliente creado con exito!!!!");


    }

    public static void ActualizarPersonaMenu() {
        System.out.println("Digite el Rut Cliente");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        String RutCliente=Entrada.nextLine();
        int indiceCliente = -1;
        for(int i = 0; i < LClienteRestaurante.size(); i++) {
            if(LClienteRestaurante.get(i).getRutCliente().equals(RutCliente)) {
                indiceCliente = i;
                break;
            }
        }
        if(indiceCliente == -1) {
            System.out.println("El cliente no existe");
        } else {
            try {
                ClienteRestaurante.CrearDatosCliente(indiceCliente);
            } catch (ParseException ex) {
                Logger.getLogger(ClienteRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void EliminarPersonaMenu() {
        System.out.println("Digite el Rut Cliente");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        String RutCliente=Entrada.nextLine();
        ClienteRestaurante.EliminarPersona(RutCliente);
    }
    

    public static void ImprimirDatos() {
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            System.out.println("***Cliente #" + (i + 1));
            System.out.println("Nombre: " + LClienteRestaurante.get(i).NombreCliente);
            System.out.println("Rut: " + LClienteRestaurante.get(i).RutCliente);
            Calendar fechaN = Calendar.getInstance();
            fechaN.setTime(LClienteRestaurante.get(i).FechaN);
            System.out.println("Fecha N: " + fechaN.get(Calendar.DAY_OF_MONTH) + "/" + fechaN.get(Calendar.MONTH) + "/" + fechaN.get(Calendar.YEAR));
            System.out.println("**Boletas:");
            // Imprimiendo coleccion dentro de coleccion
            for(BoletaRestaurante boleta : LClienteRestaurante.get(i).LBoletaCliente) {
                boleta.Imprimir();
            }
        }
    }

    public static void ImprimirBoletas() {
        System.out.println("***Boletas:");
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            // Imprimiendo coleccion dentro de coleccion
            for(BoletaRestaurante boleta : LClienteRestaurante.get(i).LBoletaCliente) {
                boleta.Imprimir();
            }
        }
    }

    public static void GenerarReporteClientes() {
        String separador = ",";
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("reporte_clientes.csv"));
            writer.write("NOMBRE"+separador+"RUT"+separador+"FECHA NACIMIENTO\n");
            for(ClienteRestaurante cliente : LClienteRestaurante) {
                writer.append(cliente.getNombreCliente());
                writer.append(separador);
                writer.append(cliente.getRutCliente());
                writer.append(separador);
                Calendar fechaN = Calendar.getInstance();
                fechaN.setTime(cliente.getFechaN());
                writer.append(fechaN.get(Calendar.DAY_OF_MONTH) + "/" + fechaN.get(Calendar.MONTH) + "/" + fechaN.get(Calendar.YEAR));
                writer.append("\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
