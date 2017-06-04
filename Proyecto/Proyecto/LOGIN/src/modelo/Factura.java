/* Java Bean

* Clase: Factura  */
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

public class Factura{
	private IntegerProperty codigoFactura;
	private Farmacia farmacia;
	private Bodega bodega;
	private Cliente cliente;
	private Date fecha;
	private DoubleProperty total;
	private DoubleProperty isv;
	private DoubleProperty totalPagar;
	private StringProperty tipoPago;
	private Vendedor vendedor;

	public Factura(Integer codigoFactura, Farmacia farmacia,
						Bodega bodega, Cliente cliente, Date fecha,
						Double total, Double isv, Double totalPagar,
						String tipoPago, Vendedor vendedor){
		this.codigoFactura = new SimpleIntegerProperty(codigoFactura);
		this.farmacia = farmacia;
		this.bodega = bodega;
		this.cliente = cliente;
		this.fecha = fecha;
		this.total = new SimpleDoubleProperty(total);
		this.isv = new SimpleDoubleProperty(isv);
		this.totalPagar = new SimpleDoubleProperty(totalPagar);
		this.tipoPago = new SimpleStringProperty(tipoPago);
		this.vendedor = vendedor;
	}
	public Factura(){

	}

	public Integer getCodigoFactura(){
		return codigoFactura.get();
	}

	public void setCodigoFactura(Integer codigoFactura){
		this.codigoFactura = new SimpleIntegerProperty(codigoFactura);
	}

	public Farmacia getFarmacia(){
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia){
		this.farmacia = farmacia;
	}

	public Bodega getBodega(){
		return bodega;
	}

	public void setBodega(Bodega bodega){
		this.bodega = bodega;
	}

	public Cliente getCliente(){
		return cliente;
	}

	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}

	public Date getFecha(){
		return fecha;
	}

	public void setFecha(Date fecha){
		this.fecha = fecha;
	}

	public Double getTotal(){
		return total.get();
	}

	public void setTotal(Double total){
		this.total = new SimpleDoubleProperty(total);
	}

	public Double getIsv(){
		return isv.get();
	}

	public void setIsv(Double isv){
		this.isv = new SimpleDoubleProperty(isv);
	}

	public Double getTotalPagar(){
		return totalPagar.get();
	}

	public void setTotalPagar(Double totalPagar){
		this.totalPagar = new SimpleDoubleProperty(totalPagar);
	}

	public String getTipoPago(){
		return tipoPago.get();
	}

	public void setTipoPago(String tipoPago){
		this.tipoPago = new SimpleStringProperty(tipoPago);
	}

	public Vendedor getVendedor(){
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor){
		this.vendedor = vendedor;
	}

	public IntegerProperty codigoFacturaProperty(){
		return codigoFactura;
	}

	public DoubleProperty totalProperty(){
		return total;
	}

	public DoubleProperty isvProperty(){
		return isv;
	}

	public DoubleProperty totalPagarProperty(){
		return totalPagar;
	}

	public StringProperty tipoPagoProperty(){
		return tipoPago;
	}
	public static Vendedor cargarDatosEmpleado(int id,Connection conexion){
		return new  Vendedor(conexion,id);
	}
	public static RegistroSupervisorVentas cargarDatosSuper(int id,Connection conexion){
		return new RegistroSupervisorVentas(conexion,id);
	}
	public int guardar(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareCall("INSERT INTO "+
					 "tbl_factura("+
							   // "tbl_Factura, "+
							    "codigo_farmacia, "+
							    "codigo_bodega, "+
							    "codigo_cliente, "+
							    "fecha, "+
							    "total, "+
							    "isv, "+
							    "total_pagar, "+
							    "tipo_pago, "+
							    "id_vendedor "+
							  ")" +
					"VALUES (?,?,?,?,?,?,?,?,?)");
			//instruccion.setInt(1,farmacia.getCodigoFarmacia());
			instruccion.setInt(1,1);//provicionalmente es 1
			//instruccion.setInt(2,bodega.getCodigoBodega());
			instruccion.setInt(2,1);//provicionalmente es 1
			instruccion.setInt(3,cliente.getCodigoCliente());
			instruccion.setDate(4,fecha);
			instruccion.setDouble(5,total.get());
			instruccion.setDouble(6,isv.get());
			instruccion.setDouble(7,totalPagar.get());
			instruccion.setString(8,tipoPago.get());
			instruccion.setInt(9,vendedor.getCodigoVendedor());
			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoFactura=new SimpleIntegerProperty(resultadoId.getInt("id"));
				System.out.println("codigo de factura obtenido: "+codigoFactura.get());

			}
			return resultado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}