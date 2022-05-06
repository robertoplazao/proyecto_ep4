/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.antonio.proyecto;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Proyecto {
	
	public static void main(String[] args) {
		try {
			
			/* Datos Iniciales en mapa de JCF */

            // Cliente 1
			String rut1 = "12345678-9";
			Date fecha1 = new SimpleDateFormat("dd/MM/yyyy").parse("23/03/1990");
            ClienteRestaurante cliente1 = new ClienteRestaurante("Pedro Perez", rut1, fecha1);
			ClienteRestaurante.Guardar(cliente1, -1);

            // Boletas Cliente 1
			Date fechaBoleta1 = new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2022");
            BoletaRestaurante boleta1 = new BoletaRestaurante(123,fechaBoleta1,cliente1,1200,23500.0);
            cliente1.GuardarBoleta(boleta1, -1);
            BoletaRestaurante boleta2 = new BoletaRestaurante(432,fechaBoleta1,cliente1,1800,30000.0);
            cliente1.GuardarBoleta(boleta2, -1);
			
            // Cliente 2
			String rut2 = "5243450-7";
			Date fecha2 = new SimpleDateFormat("dd/MM/yyyy").parse("19/12/1985");
			ClienteRestaurante.Guardar(new ClienteRestaurante("Juana Ramirez", rut2, fecha2), -1);
			
            // Cliente 3
			String rut3 = "2341234-3";
			Date fecha3 = new SimpleDateFormat("dd/MM/yyyy").parse("07/08/1993");
            ClienteRestaurante cliente3 = new ClienteRestaurante("Jose Hernandez", rut3, fecha3);
			ClienteRestaurante.Guardar(cliente3, -1);

            // Boletas Cliente 3
			Date fechaBoleta3 = new SimpleDateFormat("dd/MM/yyyy").parse("23/03/2022");
            BoletaRestaurante boleta3 = new BoletaRestaurante(800,fechaBoleta3,cliente3,800,15300.0);
            cliente3.GuardarBoleta(boleta3, -1);
			Date fechaBoleta4 = new SimpleDateFormat("dd/MM/yyyy").parse("17/04/2022");
            BoletaRestaurante boleta4 = new BoletaRestaurante(999,fechaBoleta4,cliente3,1100,17000.0);
            cliente3.GuardarBoleta(boleta4, -1);
			
			OperacionesRestaurante.Menu();
		} catch (ParseException ex) {
			Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
