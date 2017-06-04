package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Medicamento {


	private IntegerProperty codigoMedicamento;
	private Distribuidora distribuidoraMedicamento;
	private Fabricante fabricanteMedicamento;
	private TipoMedicamento tipoMedicamento;
	private StringProperty nombreMedicamento;
	private Date fechaVencimiento;
	private DoubleProperty precioMedicamento;


	public Medicamento(int codigoMedicamento, String nombreMedicamento, Date fechaVencimiento,
Double precioMedicamento, Distribuidora distribuidoraMedicamento, Fabricante fabricanteMedicamento,
TipoMedicamento tipoMedicamento) {
		this.codigoMedicamento = new SimpleIntegerProperty(codigoMedicamento);
		this.nombreMedicamento = new SimpleStringProperty(nombreMedicamento);
		this.fechaVencimiento = fechaVencimiento;
		this.precioMedicamento = new SimpleDoubleProperty(precioMedicamento);
		this.distribuidoraMedicamento = distribuidoraMedicamento;
		this.fabricanteMedicamento = fabricanteMedicamento;
		this.tipoMedicamento = tipoMedicamento;
	}

	//Metodos atributo: codigoMedicamento
	public int  getCodigoMedicamento() {
		return codigoMedicamento.get();
	}
	public void setCodigoMedicamento(int codigoMedicamento) {
		this.codigoMedicamento = new SimpleIntegerProperty(codigoMedicamento);
	}
	public IntegerProperty getCodigoMedicamentoProperty(){
		return codigoMedicamento;
	}
	//Metodos atributo: nombreMedicamento
	public String getNombreMedicamento() {
		return nombreMedicamento.get();
	}
	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = new SimpleStringProperty(nombreMedicamento);
	}
	public StringProperty geNombreMedicamentoProperty(){
		return nombreMedicamento;
	}
	//Metodos atributo: fechaVencimiento
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	//Metodos atributo: precioMedicamento
	public Double getPrecioMedicamento() {
		return precioMedicamento.get();
	}
	public void setPrecioMedicamento(Double precioMedicamento) {
		this.precioMedicamento = new SimpleDoubleProperty(precioMedicamento);
	}
	public DoubleProperty PrecioMedicamentoProperty() {
		return precioMedicamento;
	}
	//Metodos atributo: distribuidoraMedicamento
	public Distribuidora getDistribuidoraMedicamento() {
		return distribuidoraMedicamento;
	}
	public void setDistribuidoraMedicamento(Distribuidora distribuidoraMedicamento) {
		this.distribuidoraMedicamento = distribuidoraMedicamento;
	}
	//Metodos atributo: fabricanteMedicamento
	public Fabricante getFabricanteMedicamento() {
		return fabricanteMedicamento;
	}
	public void setFabricanteMedicamento(Fabricante fabricanteMedicamento) {
		this.fabricanteMedicamento = fabricanteMedicamento;
	}
	//Metodos atributo: tipoMedicamento
	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}
	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}


	public static void llenarListaMedicamento(ObservableList<Medicamento> lista,Connection conexion){

		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT 	A.codigo_medicamento, "+
							"A.codigo_distribuidora, "+
							"A.codigo_fabricante, "+
							"A.id_tipo_medicamento, "+
							"A.nombre_medicamento, "+
							"A.fecha_vencimiento, "+
							"A.precio, "+
							"B.codigo_distribuidora, "+
							"B.nombre_distribuidora, "+
							"B.direccion_distribuidora, "+
							"B.correo_distribuidora, "+
							"B.telefono_distribuidora, "+
							"C.codigo_fabricante, "+
							"C.direccion_fabricante, "+
							"C.correo_fabricante, "+
							"C.telefono_fabricante, "+
							"C.nombre_fabricante, "+
							"D.id_tipo_medicamento, "+
							"D.nombre_tipo_medicamento "+
					"FROM 	tbl_medicamento A "+
					"INNER JOIN tbl_distribuidora B "+
					"ON (A.codigo_distribuidora = B.codigo_distribuidora) "+
					"INNER JOIN tbl_fabricante C "+
					"ON (A.codigo_fabricante = C.codigo_fabricante) "+
					"INNER JOIN tbl_tipo_medicamento D "+
					"ON (A.id_tipo_medicamento = D.id_tipo_medicamento) ");

			while(resultado.next()){
					lista.add(new Medicamento(resultado.getInt("codigo_medicamento"),
											  resultado.getString("nombre_medicamento"),
											  resultado.getDate("fecha_vencimiento"),
											  resultado.getDouble("precio"),
								new Distribuidora(resultado.getInt("codigo_distribuidora"),
										          resultado.getString("nombre_distribuidora"),
										          resultado.getString("direccion_distribuidora"),
										          resultado.getString("correo_distribuidora"),
										          resultado.getString("telefono_distribuidora")),
								new Fabricante(resultado.getInt("codigo_fabricante"),
											   resultado.getString("direccion_fabricante"),
											   resultado.getString("correo_fabricante"),
											   resultado.getString("telefono_fabricante"),
											   resultado.getString("nombre_fabricante")),
								new TipoMedicamento(resultado.getInt("id_tipo_medicamento"),
													resultado.getString("nombre_tipo_medicamento"))));
			}

			//instruccion.close();
			//resultado.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	public static void llenarListaMedicamentosdeBusqueda(ObservableList<Medicamento> lista1,
														 Connection conexion,
														 int categoria,
														 String nombre){

		try {

			PreparedStatement instruccion = conexion.prepareStatement(
					"SELECT 	A.codigo_medicamento, "+
							"A.codigo_distribuidora, "+
							"A.codigo_fabricante, "+
							"A.id_tipo_medicamento, "+
							"A.nombre_medicamento, "+
							"A.fecha_vencimiento, "+
							"A.precio, "+
							"B.codigo_distribuidora, "+
							"B.nombre_distribuidora, "+
							"B.direccion_distribuidora, "+
							"B.correo_distribuidora, "+
							"B.telefono_distribuidora, "+
							"C.codigo_fabricante, "+
							"C.direccion_fabricante, "+
							"C.correo_fabricante, "+
							"C.telefono_fabricante, "+
							"C.nombre_fabricante, "+
							"D.id_tipo_medicamento, "+
							"D.nombre_tipo_medicamento "+
					"FROM 	tbl_medicamento A "+
					"INNER JOIN tbl_distribuidora B "+
					"ON (A.codigo_distribuidora = B.codigo_distribuidora) "+
					"INNER JOIN tbl_fabricante C "+
					"ON (A.codigo_fabricante = C.codigo_fabricante) "+
					"INNER JOIN tbl_tipo_medicamento D "+
					"ON (A.id_tipo_medicamento = D.id_tipo_medicamento) "+
					"WHERE A.id_tipo_medicamento = ? "+
					"AND A.nombre_medicamento = ? ");


			instruccion.setInt(1, categoria);
			instruccion.setString(2, nombre);

			ResultSet resultado = instruccion.executeQuery();

			while(resultado.next()){
				lista1.add(new Medicamento(resultado.getInt("codigo_medicamento"),
										  resultado.getString("nombre_medicamento"),
										  resultado.getDate("fecha_vencimiento"),
										  resultado.getDouble("precio"),
							new Distribuidora(resultado.getInt("codigo_distribuidora"),
									          resultado.getString("nombre_distribuidora"),
									          resultado.getString("direccion_distribuidora"),
									          resultado.getString("correo_distribuidora"),
									          resultado.getString("telefono_distribuidora")),
							new Fabricante(resultado.getInt("codigo_fabricante"),
										   resultado.getString("direccion_fabricante"),
										   resultado.getString("correo_fabricante"),
										   resultado.getString("telefono_fabricante"),
										   resultado.getString("nombre_fabricante")),
							new TipoMedicamento(resultado.getInt("id_tipo_medicamento"),
												resultado.getString("nombre_tipo_medicamento"))));
		}



		} catch (SQLException e) {
			e.printStackTrace();
		}



	}




	public int almacenarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion = conexion.prepareStatement(
					"INSERT INTO tbl_medicamento ( " +
							"codigo_distribuidora, " +
							"codigo_fabricante, " +
							"id_tipo_medicamento, " +
							"nombre_medicamento, fecha_vencimiento, " +
							"precio " +
					")  " +
					"VALUES (?, ?, ?, ?, ?, ?)"
			);
			instruccion.setInt(1, distribuidoraMedicamento.getCodigoDistribuidora());
			instruccion.setInt(2, fabricanteMedicamento.getCodigoFabricante());
			instruccion.setInt(3, tipoMedicamento.getIdTipoMedicamento());
			instruccion.setString(4, nombreMedicamento.get());
			instruccion.setDate(5, fechaVencimiento);
			instruccion.setDouble(6, precioMedicamento.get());
			int resultado = instruccion.executeUpdate();
			if (resultado==1){

				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoMedicamento = new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int actualizarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion =
					conexion.prepareStatement(
							"UPDATE tbl_medicamento "+
							"SET codigo_distribuidora = ?, "+
								"codigo_fabricante = ?,  "+
								"id_tipo_medicamento = ?,  "+
								"nombre_medicamento = ?,  "+
								"fecha_vencimiento = ?,  "+
								"precio = ?  "+
							"WHERE codigo_medicamento = ?");

			instruccion.setInt(1, distribuidoraMedicamento.getCodigoDistribuidora());
			instruccion.setInt(2, fabricanteMedicamento.getCodigoFabricante());
			instruccion.setInt(3, tipoMedicamento.getIdTipoMedicamento());
			instruccion.setString(4, nombreMedicamento.get());
			instruccion.setDate(5, fechaVencimiento);
			instruccion.setDouble(6, precioMedicamento.get());
			instruccion.setInt(7, codigoMedicamento.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int eliminarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion = conexion.prepareStatement(
					"DELETE FROM tbl_medicamento "+
					"WHERE codigo_medicamento = ?"
			);
			instruccion.setInt(1, codigoMedicamento.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String toString() {
		return nombreMedicamento.get();
	}

//para busqueda de medicamento en la factura
	public static void llenarListaMedicamento2(ObservableList<Medicamento> lista,Connection conexion){

		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT 	A.codigo_medicamento, "+
							"A.codigo_distribuidora, "+
							"A.codigo_fabricante, "+
							"A.id_tipo_medicamento, "+
							"A.nombre_medicamento, "+
							"A.fecha_vencimiento, "+
							"A.precio, "+
							"B.codigo_distribuidora, "+
							"B.nombre_distribuidora, "+
							"B.direccion_distribuidora, "+
							"B.correo_distribuidora, "+
							"B.telefono_distribuidora, "+
							"C.codigo_fabricante, "+
							"C.direccion_fabricante, "+
							"C.correo_fabricante, "+
							"C.telefono_fabricante, "+
							"C.nombre_fabricante, "+
							"D.id_tipo_medicamento, "+
							"D.nombre_tipo_medicamento "+
					"FROM 	tbl_medicamento A "+
					"INNER JOIN tbl_distribuidora B "+
					"ON (A.codigo_distribuidora = B.codigo_distribuidora) "+
					"INNER JOIN tbl_fabricante C "+
					"ON (A.codigo_fabricante = C.codigo_fabricante) "+
					"INNER JOIN tbl_tipo_medicamento D "+
					"ON (A.id_tipo_medicamento = D.id_tipo_medicamento)"
					+ " INNER JOIN existencia E "
					+ "ON ( A.codigo_medicamento = E.codigo_medicamento)"
					+ "where E.cantidad_medicina > 0");

			while(resultado.next()){
					lista.add(new Medicamento(resultado.getInt("codigo_medicamento"),
											  resultado.getString("nombre_medicamento"),
											  resultado.getDate("fecha_vencimiento"),
											  resultado.getDouble("precio"),
								new Distribuidora(resultado.getInt("codigo_distribuidora"),
										          resultado.getString("nombre_distribuidora"),
										          resultado.getString("direccion_distribuidora"),
										          resultado.getString("correo_distribuidora"),
										          resultado.getString("telefono_distribuidora")),
								new Fabricante(resultado.getInt("codigo_fabricante"),
											   resultado.getString("direccion_fabricante"),
											   resultado.getString("correo_fabricante"),
											   resultado.getString("telefono_fabricante"),
											   resultado.getString("nombre_fabricante")),
								new TipoMedicamento(resultado.getInt("id_tipo_medicamento"),
													resultado.getString("nombre_tipo_medicamento"))));
			}

			//instruccion.close();
			//resultado.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void llenarListaMedicamentosdeBusqueda2(ObservableList<Medicamento> lista1,
			 Connection conexion,int categoria, String nombre){
		try {
				PreparedStatement instruccion = conexion.prepareStatement(
						"SELECT 	A.codigo_medicamento, "+
						"A.codigo_distribuidora, "+
						"A.codigo_fabricante, "+
						"A.id_tipo_medicamento, "+
						"A.nombre_medicamento, "+
						"A.fecha_vencimiento, "+
						"A.precio, "+
						"B.codigo_distribuidora, "+
						"B.nombre_distribuidora, "+
						"B.direccion_distribuidora, "+
						"B.correo_distribuidora, "+
						"B.telefono_distribuidora, "+
						"C.codigo_fabricante, "+
						"C.direccion_fabricante, "+
						"C.correo_fabricante, "+
						"C.telefono_fabricante, "+
						"C.nombre_fabricante, "+
						"D.id_tipo_medicamento, "+
						"D.nombre_tipo_medicamento "+
						"FROM 	tbl_medicamento A "+
						"INNER JOIN tbl_distribuidora B "+
						"ON (A.codigo_distribuidora = B.codigo_distribuidora) "+
						"INNER JOIN tbl_fabricante C "+
						"ON (A.codigo_fabricante = C.codigo_fabricante) "+
						"INNER JOIN tbl_tipo_medicamento D "+
						"ON (A.id_tipo_medicamento = D.id_tipo_medicamento)  "
						+ "INNER JOIN existencia E"
						+ "  ON ( A.codigo_medicamento = E.codigo_medicamento)"+
						"WHERE A.id_tipo_medicamento = ? "+
						"AND A.nombre_medicamento = ? "
						+ "AND E.cantidad_medicina > 0");


			instruccion.setInt(1, categoria);
			instruccion.setString(2, nombre);

			ResultSet resultado = instruccion.executeQuery();

			while(resultado.next()){
			lista1.add(new Medicamento(resultado.getInt("codigo_medicamento"),
			resultado.getString("nombre_medicamento"),
			resultado.getDate("fecha_vencimiento"),
			resultado.getDouble("precio"),
			new Distribuidora(resultado.getInt("codigo_distribuidora"),
			 resultado.getString("nombre_distribuidora"),
			 resultado.getString("direccion_distribuidora"),
			 resultado.getString("correo_distribuidora"),
			 resultado.getString("telefono_distribuidora")),
			new Fabricante(resultado.getInt("codigo_fabricante"),
			resultado.getString("direccion_fabricante"),
			resultado.getString("correo_fabricante"),
			resultado.getString("telefono_fabricante"),
			resultado.getString("nombre_fabricante")),
			new TipoMedicamento(resultado.getInt("id_tipo_medicamento"),
				resultado.getString("nombre_tipo_medicamento"))));
			}



			} catch (SQLException e) {
			e.printStackTrace();
			}



}








}
