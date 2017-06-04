package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Farmacia {
	private int codigoFarmacia;
	private Bodega bodega;
	private String nombreFarmacia;
	private String direccionFarmacia;
	private String correoFarmacia;
	private String telefonoFarmacia;
	public Farmacia(int codigoFarmacia, Bodega bodega, String nombreFarmacia, String direccionFarmacia,
			String correoFarmacia, String telefonoFarmacia) {
		this.codigoFarmacia = codigoFarmacia;
		this.bodega = bodega;
		this.nombreFarmacia = nombreFarmacia;
		this.direccionFarmacia = direccionFarmacia;
		this.correoFarmacia = correoFarmacia;
		this.telefonoFarmacia = telefonoFarmacia;
	}
	public int getCodigoFarmacia() {
		return codigoFarmacia;
	}
	public void setCodigoFarmacia(int codigoFarmacia) {
		this.codigoFarmacia = codigoFarmacia;
	}
	public Bodega getBodega() {
		return bodega;
	}
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
	public String getNombreFarmacia() {
		return nombreFarmacia;
	}
	public void setNombreFarmacia(String nombreFarmacia) {
		this.nombreFarmacia = nombreFarmacia;
	}
	public String getDireccionFarmacia() {
		return direccionFarmacia;
	}
	public void setDireccionFarmacia(String direccionFarmacia) {
		this.direccionFarmacia = direccionFarmacia;
	}
	public String getCorreoFarmacia() {
		return correoFarmacia;
	}
	public void setCorreoFarmacia(String correoFarmacia) {
		this.correoFarmacia = correoFarmacia;
	}
	public String getTelefonoFarmacia() {
		return telefonoFarmacia;
	}
	public void setTelefonoFarmacia(String telefonoFarmacia) {
		this.telefonoFarmacia = telefonoFarmacia;
	}
	@Override
	public String toString() {
		return nombreFarmacia ;
	}
	public static void llenarFarmacia(Connection conexion, ObservableList<Farmacia> v){
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT B.codigo_farmacia, "+
					"B.nombre_farmacia, "+
					"B.direccion_farmacia, "+
					"B.correo_farmacia, "+
					"B.telefono_farmacia, "+
					"B.codigo_bodega, "+
					"C.nombre_bodega, "+
					"C.ubicacion_bodega "+
					"FROM tbl_farmacia B "+
					"INNER JOIN tbl_bodega C "+
					"ON (B.codigo_bodega = C.codigo_bodega) ");
			while(resultado.next()){
				v.add(new Farmacia(resultado.getInt("codigo_farmacia"),
						new Bodega(resultado.getInt("codigo_bodega"),
								resultado.getString("nombre_bodega"),
								resultado.getString("ubicacion_bodega")),
								resultado.getString("nombre_farmacia"),
								resultado.getString("direccion_farmacia"),
								resultado.getString("correo_farmacia"),
								resultado.getString("telefono_farmacia")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
