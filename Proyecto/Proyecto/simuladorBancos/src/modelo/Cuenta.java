/* Java Bean
* Clase: Cuenta  */
package modelo;
import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cuenta{
	private IntegerProperty codigoCuenta;
	private StringProperty tipoCuenta;
	private Date fechaCreacion;
	private DoubleProperty saldo;
	private StringProperty moneda;
	private Cliente cliente;

	public Cuenta(Integer codigoCuenta, String tipoCuenta, Date fechaCreacion, Double saldo, String moneda, Cliente cliente){
		this.codigoCuenta = new SimpleIntegerProperty(codigoCuenta);
		this.tipoCuenta = new SimpleStringProperty(tipoCuenta);
		this.fechaCreacion = fechaCreacion;
		this.saldo = new SimpleDoubleProperty(saldo);
		this.moneda = new SimpleStringProperty(moneda);
		this.cliente = cliente;
	}

	public Integer getCodigoCuenta(){
		return codigoCuenta.get();
	}

	public void setCodigoCuenta(Integer codigoCuenta){
		this.codigoCuenta = new SimpleIntegerProperty(codigoCuenta);
	}

	public String getTipoCuenta(){
		return tipoCuenta.get();
	}

	public void setTipoCuenta(String tipoCuenta){
		this.tipoCuenta = new SimpleStringProperty(tipoCuenta);
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}

	public Double getSaldo(){
		return saldo.get();
	}

	public void setSaldo(Double saldo){
		this.saldo = new SimpleDoubleProperty(saldo);
	}

	public String getMoneda(){
		return moneda.get();
	}

	public void setMoneda(String moneda){
		this.moneda = new SimpleStringProperty(moneda);
	}

	public Cliente getCliente(){
		return cliente;
	}

	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}

	public IntegerProperty codigoCuentaProperty(){
		return codigoCuenta;
	}

	public StringProperty tipoCuentaProperty(){
		return tipoCuenta;
	}

	public DoubleProperty saldoProperty(){
		return saldo;
	}

	public StringProperty monedaProperty(){
		return moneda;
	}


}