package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Bodega {

	private int codigoBodega;
	private String nombreBodega;
	private String ubicacionBodega;
	public Bodega(int codigoBodega, String nombreBodega, String ubicacionBodega) {
		this.codigoBodega = codigoBodega;
		this.nombreBodega = nombreBodega;
		this.ubicacionBodega = ubicacionBodega;
	}
	//Bodega
	public Bodega(int codigoBodega) {
		this.codigoBodega = codigoBodega;

	}
	public int getCodigoBodega() {
		return codigoBodega;
	}
	public void setCodigoBodega(int codigoBodega) {
		this.codigoBodega = codigoBodega;
	}
	public String getNombreBodega() {
		return nombreBodega;
	}
	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}
	public String getUbicacionBodega() {
		return ubicacionBodega;
	}
	public void setUbicacionBodega(String ubicacionBodega) {
		this.ubicacionBodega = ubicacionBodega;
	}
	@Override
	public String toString() {
		return nombreBodega ;
	}

	public static void llenarBodega(Connection conexion, ObservableList<Bodega> v){
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT codigo_bodega, "+
					"nombre_bodega, "+
					"ubicacion_bodega "+
					"FROM tbl_bodega  ");
			while(resultado.next()){
				v.add(new Bodega(resultado.getInt("codigo_bodega"),
						resultado.getString("nombre_bodega"),
						resultado.getString("ubicacion_bodega")));
			}

}catch (SQLException e) {
	e.printStackTrace();
}
}


}

