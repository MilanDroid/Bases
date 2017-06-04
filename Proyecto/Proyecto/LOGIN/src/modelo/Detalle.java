package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
/* Java Bean
* Clase: Detalle  */
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Detalle{
	private IntegerProperty codigoDescripcion;
	private Factura factura;
	private Medicamento medicamento;
	private IntegerProperty cantidad;
	private DoubleProperty precio;
	private DoubleProperty total;
	private IntegerProperty codigoFactura;
	private IntegerProperty codigoMedicamento;

	public Detalle(Integer codigoDescripcion, Factura factura, Medicamento medicamento, Integer cantidad, Double precio, Double total){
		this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
		this.factura = factura;
		this.medicamento = medicamento;
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.precio = new SimpleDoubleProperty(precio);
		this.total = new SimpleDoubleProperty(total);
		this.codigoFactura = new SimpleIntegerProperty(factura.getCodigoFactura());
		this.codigoMedicamento = new SimpleIntegerProperty(medicamento.getCodigoMedicamento());
	}
	//construccion sin total
	public Detalle(Integer codigoDescripcion, Factura factura, Medicamento medicamento, Integer cantidad, Double precio){
		this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
		this.factura = factura;
		this.medicamento = medicamento;
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.precio = new SimpleDoubleProperty(precio);
		this.total = new SimpleDoubleProperty(precio*cantidad);
		//this.codigoFactura = new SimpleIntegerProperty(factura.getCodigoFactura());
		this.codigoMedicamento = new SimpleIntegerProperty(medicamento.getCodigoMedicamento());
	}
	//constructor sin precio y total
	public Detalle(Integer codigoDescripcion, Factura factura, Medicamento medicamento, Integer cantidad){
		this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
		this.factura = factura;
		this.medicamento = medicamento;
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.precio = new SimpleDoubleProperty(medicamento.getPrecioMedicamento());
		this.total = new SimpleDoubleProperty((medicamento.getPrecioMedicamento())*cantidad);
		this.codigoFactura = new SimpleIntegerProperty(factura.getCodigoFactura());
		this.codigoMedicamento = new SimpleIntegerProperty(medicamento.getCodigoMedicamento());
	}
	//constructor sin precio y total y factura
		public Detalle(Integer codigoDescripcion, Medicamento medicamento, Integer cantidad){
			this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
			this.factura = factura;
			this.medicamento = medicamento;
			this.cantidad = new SimpleIntegerProperty(cantidad);
			this.precio = new SimpleDoubleProperty(medicamento.getPrecioMedicamento());
			this.total = new SimpleDoubleProperty((medicamento.getPrecioMedicamento())*cantidad);
			//this.codigoFactura = new SimpleIntegerProperty(factura.getCodigoFactura());
			this.codigoMedicamento = new SimpleIntegerProperty(medicamento.getCodigoMedicamento());
		}
	public Detalle(Integer codigoDescripcion, Integer cantidad, Integer precio, Integer total, Integer codigoFactura, Integer codigoMedicamento){
		this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.precio = new SimpleDoubleProperty(precio);
		this.total = new SimpleDoubleProperty(total);
		this.codigoFactura = new SimpleIntegerProperty(codigoFactura);
		this.codigoMedicamento = new SimpleIntegerProperty(codigoMedicamento);

	}

	public Integer getCodigoDescripcion(){
		return codigoDescripcion.get();
	}

	public void setCodigoDescripcion(Integer codigoDescripcion){
		this.codigoDescripcion = new SimpleIntegerProperty(codigoDescripcion);
	}

	public Factura getFactura(){
		return factura;
	}

	public void setFactura(Factura factura){
		this.factura = factura;
		this.codigoFactura =  new SimpleIntegerProperty(factura.getCodigoFactura());
	}

	public Medicamento getMedicamento(){
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento){
		this.medicamento = medicamento;
	}

	public Integer getCantidad(){
		return cantidad.get();
	}

	public void setCantidad(Integer cantidad){
		this.cantidad = new SimpleIntegerProperty(cantidad);
	}

	public Double getPrecio(){
		return precio.get();
	}

	public void setPrecio(Integer precio){
		this.precio = new SimpleDoubleProperty(precio);
	}

	public Double getTotal(){
		return total.get();
	}

	public void setTotal(Integer total){
		this.total = new SimpleDoubleProperty(total);
	}

	public IntegerProperty codigoDescripcionProperty(){
		return codigoDescripcion;
	}

	public IntegerProperty cantidadProperty(){
		return cantidad;
	}

	public DoubleProperty precioProperty(){
		return precio;
	}

	public DoubleProperty totalProperty(){
		return total;
	}


	public Integer getCodigoFactura(){
		return codigoFactura.get();
	}

	public void setCodigoFactura(Integer codigoFactura){
		this.codigoFactura = new SimpleIntegerProperty(codigoFactura);
	}

	public Integer getCodigoMedicamento(){
		return codigoMedicamento.get();
	}

	public void setCodigoMedicamento(Integer codigoMedicamento){
		this.codigoMedicamento = new SimpleIntegerProperty(codigoMedicamento);
	}

	public IntegerProperty codigoFacturaProperty(){
		return codigoFactura;
	}

	public IntegerProperty codigoMedicamentoProperty(){
		return codigoMedicamento;
	}
	public int guardar(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareCall("INSERT INTO "+
																"tbl_descripcion( "+
																    "codigo_factura, "+
																    "codigo_medicamento,"+
																    "cantidad, "+
																    "precio, "+
																    "total )"+
																    "VALUES (?,?,?,?,?)");
			instruccion.setInt(1,codigoFactura.get());
			instruccion.setInt(2,medicamento.getCodigoMedicamento());
			instruccion.setInt(3,cantidad.get());
			instruccion.setDouble(4,medicamento.getPrecioMedicamento());
			instruccion.setDouble(5,total.get());
			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoDescripcion=new SimpleIntegerProperty(resultadoId.getInt("id"));

			}
			return resultado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public int reducirExistencia(Connection conexion){
		int ca = cantidadDisponible(conexion,codigoMedicamento.get());
		try {

			//actualizar datos
			PreparedStatement instruccion=conexion.prepareStatement("UPDATE  existencia "
					+ "SET  "
					+ "cantidad_medicina = ?"
					+ " WHERE codigo_medicamento = ?");
			instruccion.setInt(1,ca-cantidad.get());
			instruccion.setInt(2, codigoMedicamento.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public static int cantidadDisponible(Connection conexion,int codigoMedicamento){
		try {
			PreparedStatement instruccion = conexion.prepareStatement("SELECT cantidad_medicina FROM existencia WHERE codigo_medicamento = ?");
			instruccion.setInt(1, codigoMedicamento);
			ResultSet resultado = instruccion.executeQuery();

			while(resultado.next()){
				resultado.getInt("cantidad_medicina");
			return resultado.getInt("cantidad_medicina");
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
	}
}
