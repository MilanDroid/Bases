package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Fabricante {


	private int codigoFabricante;
	private String direccionFabricante;
	private String correoFabricante;
	private String telefonoFabricante;
	private String nombreFabricante;

	public Fabricante(int codigoFabricante, String direccionFabricante, String correoFabricante,
String telefonoFabricante, String nombreFabricante) {
		this.codigoFabricante = codigoFabricante;
		this.direccionFabricante = direccionFabricante;
		this.correoFabricante = correoFabricante;
		this.telefonoFabricante = telefonoFabricante;
		this.nombreFabricante = nombreFabricante;
	}

	//Metodos atributo: codigoFabricante
	public int getCodigoFabricante() {
		return codigoFabricante;
	}
	public void setCodigoFabricante(int codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}
	//Metodos atributo: direccionFabricante
	public String getDireccionFabricante() {
		return direccionFabricante;
	}
	public void setDireccionFabricante(String direccionFabricante) {
		this.direccionFabricante = direccionFabricante;
	}
	//Metodos atributo: correoFabricante
	public String getCorreoFabricante() {
		return correoFabricante;
	}
	public void setCorreoFabricante(String correoFabricante) {
		this.correoFabricante = correoFabricante;
	}
	//Metodos atributo: telefonoFabricante
	public String getTelefonoFabricante() {
		return telefonoFabricante;
	}
	public void setTelefonoFabricante(String telefonoFabricante) {
		this.telefonoFabricante = telefonoFabricante;
	}
	//Metodos atributo: nombreFabricante
	public String getNombreFabricante() {
		return nombreFabricante;
	}
	public void setNombreFabricante(String nombreFabricante) {
		this.nombreFabricante = nombreFabricante;
	}

	@Override
	public String toString() {
		return nombreFabricante;
	}

	public static void llenarListaFabricantes(
			ObservableList<Fabricante> lista,
			Connection conexion
	){
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado =
					instruccion.executeQuery("SELECT codigo_fabricante, direccion_fabricante, correo_fabricante, telefono_fabricante, nombre_fabricante FROM tbl_fabricante");
			while(resultado.next()){
				lista.add(
						new Fabricante(
							resultado.getInt("codigo_fabricante"),
							resultado.getString("direccion_fabricante"),
							resultado.getString("correo_fabricante"),
							resultado.getString("telefono_fabricante"),
							resultado.getString("nombre_fabricante")
						)
				);
			}
			instruccion.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
