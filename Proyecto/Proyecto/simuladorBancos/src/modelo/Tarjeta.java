/* Java Bean

* Clase: Tarjeta  */
package modelo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Tarjeta{
	private IntegerProperty codigoTarjeta;
	private IntegerProperty cuenta;
	private StringProperty numeroTarjeta;
	private Date fechaCreacion;
	private Date fechaVencimiento;
	private StringProperty nombre;
	private StringProperty emisor;
	private boolean validez;
	private static Calendar fecha = new GregorianCalendar();
	private static Date fechaActual;

	public Tarjeta(Integer codigoTarjeta, Integer cuenta, String numeroTarjeta, Date fechaCreacion, Date fechaVencimiento, String nombre, String emisor, boolean validez){
		this.codigoTarjeta = new SimpleIntegerProperty(codigoTarjeta);
		this.cuenta = new SimpleIntegerProperty(cuenta);
		this.numeroTarjeta = new SimpleStringProperty(numeroTarjeta);
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.nombre = new SimpleStringProperty(nombre);
		this.emisor = new SimpleStringProperty(emisor);
		this.validez = validez;
	}

	public Integer getCodigoTarjeta(){
		return codigoTarjeta.get();
	}

	public void setCodigoTarjeta(Integer codigoTarjeta){
		this.codigoTarjeta = new SimpleIntegerProperty(codigoTarjeta);
	}

	public Integer getCuenta(){
		return cuenta.get();
	}

	public void setCuenta(Integer cuenta){
		this.cuenta = new SimpleIntegerProperty(cuenta);
	}

	public String getNumeroTarjeta(){
		return numeroTarjeta.get();
	}

	public void setNumeroTarjeta(String numeroTarjeta){
		this.numeroTarjeta = new SimpleStringProperty(numeroTarjeta);
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNombre(){
		return nombre.get();
	}

	public void setNombre(String nombre){
		this.nombre = new SimpleStringProperty(nombre);
	}

	public String getEmisor(){
		return emisor.get();
	}

	public void setEmisor(String emisor){
		this.emisor = new SimpleStringProperty(emisor);
	}

	public boolean getValidez(){
		return validez;
	}

	public void setValidez(boolean validez){
		this.validez = validez;
	}

	public IntegerProperty codigoTarjetaProperty(){
		return codigoTarjeta;
	}

	public IntegerProperty cuentaProperty(){
		return cuenta;
	}

	public StringProperty numeroTarjetaProperty(){
		return numeroTarjeta;
	}

	public StringProperty nombreProperty(){
		return nombre;
	}

	public StringProperty emisorProperty(){
		return emisor;
	}
	/*
	public static void llenarListaTarjeta(
			ObservableList<Tarjeta> lista,
			Connection conexion
		){
	//Consultar informacion de categorias
	try {
		Statement instruccion = conexion.createStatement();
		ResultSet resultado = instruccion.executeQuery("SELECT "+
				  "codigo_tarjeta, "+
				  "tlb_cuenta_codigo_cuenta, "+
				  "numero_tarjeta, "+
				  "fecha_creacion, "+
				  "fecha_vencimiento, "+
				  "nombre,"+
				  "emisor,"+
				  "validez "+
				"FROM "+
				  "tlb_tarjeta ");

		while(resultado.next()){
			lista.add(
					new Tarjeta(
							resultado.getInt("codigo_tarjeta"),
							resultado.getInt("tlb_cuenta_codigo_cuenta"),
							resultado.getString("numero_tarjeta"),
							resultado.getDate("fecha_creacion"),
							resultado.getDate("fecha_vencimiento"),
							resultado.getString("nombre"),
							resultado.getString("emisor"),
							resultado.getBoolean("validez")
					)
			);
		}

		instruccion.close();
		resultado.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//Llenar Lista
}*/
	public static int efectuarPago(pagoTarjeta pago,
			Connection conexion
		){
		int devol =0;
		Tarjeta t = null;
		Double saldo = 0.0;
		int codigoCuenta =0;
	//Consulta tarjetas
	try {
		//System.out.println("vrificando "+ id);
		Statement instruccion = conexion.createStatement();
		ResultSet resultado = instruccion.executeQuery("SELECT "+
				  "codigo_tarjeta, "+
				  "tlb_cuenta_codigo_cuenta, "+
				  "numero_tarjeta, "+
				  "tlb_tarjeta.fecha_creacion, "+
				  "fecha_vencimiento, "+
				  "nombre,"+
				  "emisor,"+
				  "validez, "+
				  "tlb_cuenta.saldo, "+
				  "tlb_cuenta.codigo_cuenta "+
				"FROM "+
				  "tlb_tarjeta "+
				  "JOIN " +
				  "tlb_cuenta "+
				  "ON "+
				  "tlb_tarjeta.tlb_cuenta_codigo_cuenta = tlb_cuenta.codigo_cuenta "+
				"WHERE numero_tarjeta = "+pago.getNumTarjeta());

		while(resultado.next()){
				t =	new Tarjeta(
							resultado.getInt("codigo_tarjeta"),
							resultado.getInt("tlb_cuenta_codigo_cuenta"),
							resultado.getString("numero_tarjeta"),
							resultado.getDate("fecha_creacion"),
							resultado.getDate("fecha_vencimiento"),
							resultado.getString("nombre"),
							resultado.getString("emisor"),
							resultado.getBoolean("validez")
							);
				saldo = resultado.getDouble("tlb_cuenta.saldo");
				codigoCuenta = resultado.getInt("tlb_cuenta.codigo_cuenta");
		}

		instruccion.close();
		resultado.close();
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("error en el id");
	}
	if(t != null){
	//System.out.println("vrificando valides de "+ id +"si t"+t.getCuenta());
	  fechaActual = new Date(fecha.getTimeInMillis());
	  System.out.println("vence"+t.getFechaVencimiento());
	  System.out.println("actua"+fechaActual);
		if((saldo >= pago.getTotPagar()) && (t.getValidez()) && (fechaActual.before(t.getFechaVencimiento())) && (t.getEmisor().equals(pago.getEmisor())) ){
			///actualizar datos
			saldo -= pago.getTotPagar();
			try {
				PreparedStatement instruccion1 =
						conexion.prepareStatement(
								"UPDATE tlb_cuenta "+
								"SET saldo = ? "+
								"WHERE codigo_cuenta = ?");

				instruccion1.setDouble(1, saldo);
				instruccion1.setInt(2, codigoCuenta);
				devol= instruccion1.executeUpdate();
			}
		 catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error al deducir");
		}
			catch (Exception e) {
				System.out.println("error al deducir");
			}
	}
		return devol;
		}
	else{

	}
	return 0;
}
	public void obtenerFechaActual(){

	}
}