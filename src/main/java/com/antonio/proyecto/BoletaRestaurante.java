package com.antonio.proyecto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BoletaRestaurante 
{
    private int CodigoBoleta;
    private Date FechaBoleta;
    private ClienteRestaurante CCliente;
    private int NumeroCalorias;
    private double Total;
    private static ArrayList<BoletaRestaurante> LBoletaRestaurante=new ArrayList<BoletaRestaurante>();

    public BoletaRestaurante(int CodigoBoleta, Date FechaBoleta, ClienteRestaurante CCliente, int NumeroCalorias, double Total)
    {
        this.CodigoBoleta = CodigoBoleta;
        this.FechaBoleta = FechaBoleta;
        this.CCliente = CCliente;   
        this.NumeroCalorias = NumeroCalorias;
        this.Total = Total;
    }

    public static void Guardar(BoletaRestaurante boleta) {
        LBoletaRestaurante.add(boleta);
    }

    public int getCodigoBoleta() {
        return CodigoBoleta;
    }

    public Date getFechaBoleta() {
        return FechaBoleta;
    }

    public ClienteRestaurante getCCliente() {
        return CCliente;
    }

    public int getNumeroCalorias() {
        return NumeroCalorias;
    }

    public double getTotal() {
        return Total;
    }

    public void setCodigo(int CodigoBoleta) {
        this.CodigoBoleta = CodigoBoleta;
    }

    public void setFechaBoleta(Date FechaBoleta) {
        this.FechaBoleta = FechaBoleta;
    }

    public void setCCliente( ClienteRestaurante  CCliente) {
        this.CCliente = CCliente;
    }
       
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
            cliente.GuardarBoleta(boleta);

            System.out.println("Boleta creada con exito");

       }
   }

    public static void EliminarBoletaMenu() {
        System.out.println("Digite el Codigo Boleta");
        Scanner Entrada=new Scanner(System.in);
        Entrada.useDelimiter("\n");
        int CodigoBoleta=Entrada.nextInt();
        BoletaRestaurante.EliminarBoleta(CodigoBoleta);
    }
    
        
    public static void ImprimirDatos(){
        for (int i = 0; i < LBoletaRestaurante.size(); i++) {
            System.out.println("Boleta #" + (i + 1));
            LBoletaRestaurante.get(i).Imprimir();
        }
    }

    public static void EliminarBoleta(int CodigoBoleta) {
        ListIterator<BoletaRestaurante> i = LBoletaRestaurante.listIterator();
        while(i.hasNext()) {
            BoletaRestaurante boleta = i.next();
            if(boleta.CodigoBoleta == CodigoBoleta) {
                boleta.CCliente.EliminarBoleta(CodigoBoleta);
                i.remove();
            }
        }
    }

    /* lista anidada boletas de cliente */
    public static void ImprimirDatos(ClienteRestaurante CCliente){
        ArrayList<BoletaRestaurante> LBoletasCliente = CCliente.getLBoletaCliente();
        for (int i = 0; i < LBoletasCliente.size(); i++) {
            System.out.println("Boleta #" + (i + 1));
            LBoletasCliente.get(i).Imprimir();
        }
    }

    public void Imprimir() {
        System.out.println("Codigo: " + this.CodigoBoleta);
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(this.FechaBoleta);
        System.out.println("Fecha: " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR));
        System.out.println("Calorias: " + this.NumeroCalorias);
        System.out.println("Total: " + this.Total);
    }

    public static void GenerarReporteBoletas() {
        String separador = ",";
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("reporte_boletas.csv"));
            writer.write("CODIGO"+separador+"FECHA"+separador+"CALORIAS"+separador+"TOTAL\n");
            for(BoletaRestaurante boleta : LBoletaRestaurante) {
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
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
