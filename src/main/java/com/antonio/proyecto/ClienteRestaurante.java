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

/**
 * Clase de cliente del restaurante
 */
public class ClienteRestaurante implements Imprimible {

    private String NombreCliente;
    private String RutCliente;
    private Date FechaN;
    private static ArrayList<ClienteRestaurante> LClienteRestaurante = new ArrayList<ClienteRestaurante>();

    /* lista anidada boletas del cliente */
    private ArrayList<BoletaRestaurante> LBoletaCliente=new ArrayList<BoletaRestaurante>();

    public ArrayList<BoletaRestaurante> getLBoletaCliente() {
        return LBoletaCliente;
    }

    /**
     * Elimina una boleta asociada a este cliente
     * @param CodigoBoleta codigo de la boleta a eliminar
     */
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

    /**
     * nombre del cliente
     */
    public String getNombreCliente() {
        return NombreCliente;
    }

    /**
     * RUT del cliente
     */
    public String getRutCliente() {
        return RutCliente;
    }

    /**
     * Fecha de nacimiento del cliente
     */
    public Date getFechaN() {
        return FechaN;
    }

    /**
     * nombre del cliente
     */
    public void setNombre(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    /**
     * RUT del cliente
     */
    public void setRutCliente(String RutCliente) {
        this.RutCliente = RutCliente;
    }

    /**
     * Fecha de nacimiento del cliente
     */
    public void setFechaN(Date FechaN) {
        this.FechaN = FechaN;
    }

    /**
     * Guarda una boleta asociada a este cliente
     * @param boleta datos de la boleta a guardar
     * @param indiceBoleta indice de la boleta si ya existe en la lista
     */
    public void GuardarBoleta(BoletaRestaurante boleta, int indiceBoleta) {
        if(indiceBoleta == -1) {
            LBoletaCliente.add(boleta);
        } else {
            LBoletaCliente.set(indiceBoleta, boleta);
        }
    }

    /**
     * Elimina una boleta del restaurante
     * @param CodigoBoleta codigo de la boleta a eliminar
     */
    public static void EliminarBoletaLista(int CodigoBoleta) {
        // buscamos el cliente con la boleta correspondiente
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            for(BoletaRestaurante boleta : LClienteRestaurante.get(i).LBoletaCliente) {
                if(boleta.getCodigoBoleta() == CodigoBoleta) {
                    LClienteRestaurante.get(i).EliminarBoleta(CodigoBoleta);
                }
            }
        }
    }

    /**
     * Registra un cliente del restaurante
     * @param cliente datos del cliente
     * @param indiceCliente indice del cliente por si ya existe en la lista
     */
    public static void Guardar(ClienteRestaurante cliente, int indiceCliente) {
        if(indiceCliente == -1) {
            LClienteRestaurante.add(cliente);
        } else {
        // si el cliente con este RUT ya existe, lo actualizamos
            LClienteRestaurante.set(indiceCliente, cliente);
        }
    }

    /**
     * Busca un cliente en la lista interna del restaurante
     * @param RutCliente rut del cliente a buscar
     * @return cliente encontrado o nulo
     */
    public static ClienteRestaurante BuscarPersona(String RutCliente) {
        for(ClienteRestaurante cliente : LClienteRestaurante) {
            if(cliente.RutCliente.equals(RutCliente)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Menú para listar boletas de un cliente en particular
     */
    public static void ListarBoletasClienteMenu() {
        System.out.println("Digite el Rut Cliente");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        String RutCliente=Entrada.nextLine();
        ClienteRestaurante Cliente = ClienteRestaurante.BuscarPersona(RutCliente);
        BoletaRestaurante.ImprimirDatos(Cliente);
    }

    /**
     * Elimina un cliente registrado en el restaurante
     * @param RutCliente rut del cliente a eliminar
     */
    public static void EliminarPersona(String RutCliente) {
        // Primero borramos las boletas asociadas al cliente
        ClienteRestaurante cliente = BuscarPersona(RutCliente);
        for(BoletaRestaurante boleta : cliente.LBoletaCliente) {
            ClienteRestaurante.EliminarBoletaLista(boleta.getCodigoBoleta());
        }

        // Y luego borramos el cliente
        ListIterator<ClienteRestaurante> i = LClienteRestaurante.listIterator();
        while(i.hasNext()) {
            if(i.next().RutCliente.equals(RutCliente)) {
                i.remove();
            }
        }
    }

    /**
     * Menú para registrar datos de cliente
     * @param indiceCliente indice del cliente por si ya existe registrado
     * @throws ParseException
     */
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

    /**
     * Menú para actualizar datos de un cliente existente
     */
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

    /**
     * Menú para actualizar datos de boleta
     */
    public static void ActualizarBoletaMenu() {
        System.out.println("Digite el Codigo Boleta");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        int CodigoBoleta=Entrada.nextInt();
        int indiceBoleta = -1;
        String RCliente = "";
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            for(int j = 0; j < LClienteRestaurante.get(i).LBoletaCliente.size(); j++) {
                if(LClienteRestaurante.get(i).LBoletaCliente.get(j).getCodigoBoleta() == CodigoBoleta) {
                    indiceBoleta = j;
                    RCliente = LClienteRestaurante.get(i).getRutCliente();
                    break;
                }
            }
        }
        if(indiceBoleta == -1) {
            System.out.println("La boleta no existe");
        } else {
            try {
                BoletaRestaurante.InsertarDatosBoletaExistente(indiceBoleta, RCliente);
            } catch (ParseException ex) {
                Logger.getLogger(ClienteRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Menú para eliminar cliente registrado en el restaurante
     */
    public static void EliminarPersonaMenu() {
        System.out.println("Digite el Rut Cliente");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        String RutCliente=Entrada.nextLine();
        ClienteRestaurante.EliminarPersona(RutCliente);
    }
    
    /**
     * Imprime los datos de los clientes registrados
     */
    public static void ImprimirDatos() {
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            System.out.println("***Cliente #" + (i + 1));
            LClienteRestaurante.get(i).Imprimir();
        }
    }

    /**
     * Imprime los datos de este cliente
     */
    @Override
    public void Imprimir() {
        System.out.println("Nombre: " + this.NombreCliente);
        System.out.println("Rut: " + this.RutCliente);
        Calendar fechaN = Calendar.getInstance();
        fechaN.setTime(this.FechaN);
        System.out.println("Fecha N: " + fechaN.get(Calendar.DAY_OF_MONTH) + "/" + fechaN.get(Calendar.MONTH) + "/" + fechaN.get(Calendar.YEAR));
        System.out.println("**Boletas:");
        // Imprimiendo coleccion dentro de coleccion
        for(BoletaRestaurante boleta : this.LBoletaCliente) {
            boleta.Imprimir();
        }
    }

    /**
     * Imprime todas las boletas registradas en el restaurante
     */
    public static void ImprimirBoletas() {
        System.out.println("***Boletas:");
        for (int i = 0; i < LClienteRestaurante.size(); i++) {
            // Imprimiendo coleccion dentro de coleccion
            for(BoletaRestaurante boleta : LClienteRestaurante.get(i).LBoletaCliente) {
                boleta.Imprimir();
            }
        }
    }

    /**
     * Genera un reporte CSV con los datos de los clientes registrados
     */
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

    /**
     * Crea un reporte con los datos de las boletas registradas del restaurante
     */
    public static void GenerarReporteBoletas() {
        String separador = ",";
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("reporte_boletas.csv"));
            writer.write("CLIENTE"+separador+"CODIGO"+separador+"FECHA"+separador+"CALORIAS"+separador+"TOTAL\n");
            for(ClienteRestaurante cliente : LClienteRestaurante) {
                for(BoletaRestaurante boleta : cliente.LBoletaCliente) {
                    writer.append(cliente.getNombreCliente());
                    writer.append(separador);
                    writer.append(Integer.toString(boleta.getCodigoBoleta()));
                    writer.append(separador);
                    Calendar fechaN = Calendar.getInstance();
                    fechaN.setTime(boleta.getFechaBoleta());
                    writer.append(fechaN.get(Calendar.DAY_OF_MONTH) + "/" + fechaN.get(Calendar.MONTH) + "/" + fechaN.get(Calendar.YEAR));
                    writer.append(separador);
                    writer.append(Integer.toString(boleta.getNumeroCalorias()));
                    writer.append(separador);
                    writer.append(Double.toString(boleta.getTotal()));
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
