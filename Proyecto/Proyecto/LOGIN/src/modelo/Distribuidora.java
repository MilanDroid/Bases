package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Distribuidora {


	private int codigoDistribuidora;
	private String nombreDistribuidora;
	private String direccionDistribuidora;
	private String correoDistribuidora;
	private String telefonoDistribuidora;

	public Distribuidora(int codigoDistribuidora, String nombreDistribuidora, String direccionDistribuidora,
String correoDistribuidora, String telefonoDistribuidora) {
		this.codigoDistribuidora = codigoDistribuidora;
		this.nombreDistribuidora = nombreDistribuidora;
		this.direccionDistribuidora = direccionDistribuidora;
		this.correoDistribuidora = correoDistribuidora;
		this.telefonoDistribuidora = telefonoDistribuidora;
	}

	//Metodos atributo: codigoDistribuidora
	public int getCodigoDistribuidora() {
		return codigoDistribuidora;
	}
	public void setCodigoDistribuidora(int codigoDistribuidora) {
		this.codigoDistribuidora = codigoDistribuidora;
	}
	//Metodos atributo: nombreDistribuidora
	public String getNombreDistribuidora() {
		return nombreDistribuidora;
	}
	public void setNombreDistribuidora(String nombreDistribuidora) {
		this.nombreDistribuidora = nombreDistribuidora;
	}
	//Metodos atributo: direccionDistribuidora
	public String getDireccionDistribuidora() {
		return direccionDistribuidora;
	}
	public void setDireccionDistribuidora(String direccionDistribuidora) {
		this.direccionDistribuidora = direccionDistribuidora;
	}
	//Metodos atributo: correoDistribuidora
	public String getCorreoDistribuidora() {
		return correoDistribuidora;
	}
	public void setCorreoDistribuidora(String correoDistribuidora) {
		this.correoDistribuidora = correoDistribuidora;
	}
	//Metodos atributo: telefonoDistribuidora
	public String getTelefonoDistribuidora() {
		return telefonoDistribuidora;
	}
	public void setTelefonoDistribuidora(String telefonoDistribuidora) {
		this.telefonoDistribuidora = telefonoDistribuidora;
	}

	@Override
	public String toString() {
		return nombreDistribuidora;
	}

	public static void llenarListaDistribuidoras(
			ObservableList<Distribuidora> lista,
			Connection conexion
	){
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado =
					instruccion.executeQuery("SELECT codigo_distribuidora, nombre_distribuidora, direccion_distribuidora, correo_distribuidora, telefono_distribuidora FROM tbl_distribuidora");
			while(resultado.next()){
				lista.add(
						new Distribuidora(
							resultado.getInt("codigo_distribuidora"),
							resultado.getString("nombre_distribuidora"),
							resultado.getString("direccion_distribuidora"),
							resultado.getString("correo_distribuidora"),
							resultado.getString("telefono_distribuidora")
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
