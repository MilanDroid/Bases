package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class TipoMedicamento {

	private int idTipoMedicamento;
	private String nombreTipoMedicamento;

	public TipoMedicamento(int idTipoMedicamento, String nombreTipoMedicamento) {
		this.idTipoMedicamento = idTipoMedicamento;
		this.nombreTipoMedicamento = nombreTipoMedicamento;
	}

	//Metodos atributo: idTipoMedicamento
	public int getIdTipoMedicamento() {
		return idTipoMedicamento;
	}
	public void setIdTipoMedicamento(int idTipoMedicamento) {
		this.idTipoMedicamento = idTipoMedicamento;
	}
	//Metodos atributo: nombreTipoMedicamento
	public String getNombreTipoMedicamento() {
		return nombreTipoMedicamento;
	}
	public void setNombreTipoMedicamento(String nombreTipoMedicamento) {
		this.nombreTipoMedicamento = nombreTipoMedicamento;
	}

	@Override
	public String toString() {
		return nombreTipoMedicamento;
	}

	public static void llenarListaCategorias(
			ObservableList<TipoMedicamento> lista,
			Connection conexion
		){
	try {
		Statement instruccion = conexion.createStatement();
		ResultSet resultado = instruccion.executeQuery(
				"SELECT id_tipo_medicamento, "+
						"nombre_tipo_medicamento "+
				"FROM tbl_tipo_medicamento");

		while(resultado.next()){
			lista.add(
					new TipoMedicamento(
						resultado.getInt("id_tipo_medicamento"),
						resultado.getString("nombre_tipo_medicamento"))
					);
		}

		instruccion.close();
		resultado.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//Llenar Lista
}




}
