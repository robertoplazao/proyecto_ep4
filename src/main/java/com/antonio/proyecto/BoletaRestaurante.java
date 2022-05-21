package com.antonio.proyecto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase para boleta de restaurante que debe pagar el cliente
 */
public class BoletaRestaurante implements Imprimible {
    private int CodigoBoleta;
    private Date FechaBoleta;
    private ClienteRestaurante CCliente;
    private int NumeroCalorias;
    private double Total;

    /**
     * Crea una boleta a pagar por cliente del restaurante
     * @param CodigoBoleta codigo de la boleta
     * @param FechaBoleta fecha en que se registra
     * @param CCliente cliente que debe pagar la boleta
     * @param NumeroCalorias numero de calorias consumidas
     * @param Total total a pagar
     */
    public BoletaRestaurante(int CodigoBoleta, Date FechaBoleta, ClienteRestaurante CCliente, int NumeroCalorias, double Total)
    {
        this.CodigoBoleta = CodigoBoleta;
        this.FechaBoleta = FechaBoleta;
        this.CCliente = CCliente;   
        this.NumeroCalorias = NumeroCalorias;
        this.Total = Total;
    }

    /**
     * codigo de la boleta
     */
    public int getCodigoBoleta() {
        return CodigoBoleta;
    }

    /**
     * fecha de registro de la boleta
     */
    public Date getFechaBoleta() {
        return FechaBoleta;
    }

    /**
     * cliente registrado en la boleta
     */
    public ClienteRestaurante getCCliente() {
        return CCliente;
    }

    /**
     * numero de calorias consumidas
     */
    public int getNumeroCalorias() {
        return NumeroCalorias;
    }

    /**
     * total a pagar
     */
    public double getTotal() {
        return Total;
    }

    /**
     * codigo de la boleta
     */
    public void setCodigo(int CodigoBoleta) {
        this.CodigoBoleta = CodigoBoleta;
    }

    /**
     * fecha de registro de la boleta
     */
    public void setFechaBoleta(Date FechaBoleta) {
        this.FechaBoleta = FechaBoleta;
    }

    /**
     * cliente registrado en la boleta
     */
    public void setCCliente( ClienteRestaurante  CCliente) {
        this.CCliente = CCliente;
    }
       
    /**
     * Menú para crear nuevo boleta
     * @throws ParseException
     */
    public static void InsertarDatosBoleta() throws ParseException
    {
       
       int IDBoleta;
       String Fecha2;
       Date Fecha = null;
       String RCliente;
       double Total;
       int Calorias;
       
       Scanner Entrada=new Scanner(System.in);
       Entrada.useDelimiter("\n");
       
       System.out.println("Digite el Rut Cliente");
       RCliente=Entrada.nextLine();

       if(ClienteRestaurante.BuscarPersona(RCliente) != null)
       {
            ClienteRestaurante cliente = ClienteRestaurante.BuscarPersona(RCliente);

            System.out.println("Datos de cliente");
            System.out.println("Rut: "+cliente.getRutCliente());
            System.out.println("Nombre: "+cliente.getNombreCliente());

            System.out.println("Digite numero de la Boleta");
            IDBoleta=OperacionesRestaurante.ValidarEntero();

            System.out.println("Digite la Fecha Boleta");
            Fecha2=Entrada.nextLine();

            Fecha=new SimpleDateFormat("dd/MM/yyyy").parse(Fecha2);
            
            System.out.println("Numero de calorias consumidas");
            Calorias=OperacionesRestaurante.ValidarEntero();

            System.out.println("Total de Factura");
            Total=OperacionesRestaurante.ValidarDouble();

            BoletaRestaurante boleta = new BoletaRestaurante(IDBoleta,Fecha,cliente,Calorias,Total);
            cliente.GuardarBoleta(boleta, -1);

            System.out.println("Boleta creada con exito");

       }
   }

    /**
     * Menú para registrar nueva orden de alimentos
     * @throws ParseException
     */
    public static void RegistrarOrdenBoleta() throws ParseException
    {
       int IDBoleta;
       Date Fecha = null;
       String RCliente;
       double Total = 0;
       int Calorias = 0;
       
       Scanner Entrada=new Scanner(System.in);
       Entrada.useDelimiter("\n");
       
       System.out.println("Digite el Rut Cliente");
       RCliente=Entrada.nextLine();

       if(ClienteRestaurante.BuscarPersona(RCliente) != null)
       {
            ClienteRestaurante cliente = ClienteRestaurante.BuscarPersona(RCliente);

            System.out.println("Datos de cliente");
            System.out.println("Rut: "+cliente.getRutCliente());
            System.out.println("Nombre: "+cliente.getNombreCliente());

            System.out.println("Digite numero de la Boleta");
            IDBoleta=OperacionesRestaurante.ValidarEntero();

            // FECHA ACTUAL
            Fecha=new Date();

            int opcionProducto=0;
            do {
                opcionProducto=Entrada.nextInt();
                System.out.println("Seleccione el producto");
                System.out.println("1-Ensalada");
                System.out.println("2-Pescado");
                System.out.println("3-Yogur");
                System.out.println("4-Salir del menu");
                Producto producto = null;
                switch(opcionProducto) {
                    case 1:
                        System.out.println(" A seleccionado Ensalada");
                        producto = new Ensalada();
                        break;
                    case 2:
                        System.out.println(" A seleccionado Pescado");
                        producto = new Pescado();
                        break;
                    case 3:
                        System.out.println(" A seleccionado Yogur");
                        producto = new Yogur();
                        break;
                    case 4:
                        System.out.println(" A seleccionado Salir del menu");
                        break;
                }
                if(producto != null) {
                    Calorias+=producto.getCalorias();
                    Total+=producto.getPrecio();
                }
                System.out.println("Calorias:"+Calorias);
                System.out.println("Total:"+Total);
            } while(opcionProducto != 4);


            BoletaRestaurante boleta = new BoletaRestaurante(IDBoleta,Fecha,cliente,Calorias,Total);
            cliente.GuardarBoleta(boleta, -1);
            System.out.println("Boleta creada con exito");
       }
   }

    /**
     * Menú para actualizar datos de boleta
     * @param indiceBoleta indice de boleta existente a actualizar
     * @param RCliente cliente que registró esta boleta
     * @throws ParseException
     */
    public static void InsertarDatosBoletaExistente(int indiceBoleta, String RCliente) throws ParseException
    {
       
       int IDBoleta;
       String Fecha2;
       Date Fecha = null;
       double Total;
       int Calorias;
       
       Scanner Entrada=new Scanner(System.in);
       Entrada.useDelimiter("\n");

       if(ClienteRestaurante.BuscarPersona(RCliente) != null)
       {
            ClienteRestaurante cliente = ClienteRestaurante.BuscarPersona(RCliente);

            System.out.println("Datos de cliente");
            System.out.println("Rut: "+cliente.getRutCliente());
            System.out.println("Nombre: "+cliente.getNombreCliente());

            System.out.println("Digite numero de la Boleta");
            IDBoleta=OperacionesRestaurante.ValidarEntero();

            System.out.println("Digite la Fecha Boleta");
            Fecha2=Entrada.nextLine();

            Fecha=new SimpleDateFormat("dd/MM/yyyy").parse(Fecha2);
            
            System.out.println("Numero de calorias consumidas");
            Calorias=OperacionesRestaurante.ValidarEntero();

            System.out.println("Total de Factura");
            Total=OperacionesRestaurante.ValidarDouble();

            BoletaRestaurante boleta = new BoletaRestaurante(IDBoleta,Fecha,cliente,Calorias,Total);
            cliente.GuardarBoleta(boleta, indiceBoleta);

            System.out.println("Boleta actualizada con exito");
       }
   }

    /**
     * Menú para eliminar una boleta existente
     */
    public static void EliminarBoletaMenu() {
        System.out.println("Digite el Codigo Boleta");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        int CodigoBoleta=Entrada.nextInt();
        ClienteRestaurante.EliminarBoletaLista(CodigoBoleta);
    }
        
    /**
     * Imprime los datos de las boletas asociadas a un cliente
     * @param CCliente
     */
    public static void ImprimirDatos(ClienteRestaurante CCliente){
        ArrayList<BoletaRestaurante> LBoletasCliente = CCliente.getLBoletaCliente();
        for (int i = 0; i < LBoletasCliente.size(); i++) {
            System.out.println("Boleta #" + (i + 1));
            LBoletasCliente.get(i).Imprimir();
        }
    }

    /**
     * Imprime los datos de esta boleta
     */
    @Override
    public void Imprimir() {
        System.out.println("Codigo: " + this.CodigoBoleta);
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(this.FechaBoleta);
        System.out.println("Fecha: " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR));
        System.out.println("Calorias: " + this.NumeroCalorias);
        System.out.println("Total: " + this.Total);
    }

}
