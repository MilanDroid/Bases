/* Java Bean
* Clase: Cliente  */
package modelo;
import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente{
	private IntegerProperty codigoCliente;
	private StringProperty nombreCliente;
	private StringProperty direccion;
	private StringProperty identidad;
	private Date fechaNac;
	private StringProperty sexo;
	private StringProperty correoElectronico;

	public Cliente(Integer codigoCliente, String nombreCliente, String direccion, String identidad, Date fechaNac, String sexo, String correoElectronico){
		this.codigoCliente = new SimpleIntegerProperty(codigoCliente);
		this.nombreCliente = new SimpleStringProperty(nombreCliente);
		this.direccion = new SimpleStringProperty(direccion);
		this.identidad = new SimpleStringProperty(identidad);
		this.fechaNac = fechaNac;
		this.sexo = new SimpleStringProperty(sexo);
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
	}

	public Integer getCodigoCliente(){
		return codigoCliente.get();
	}

	public void setCodigoCliente(Integer codigoCliente){
		this.codigoCliente = new SimpleIntegerProperty(codigoCliente);
	}

	public String getNombreCliente(){
		return nombreCliente.get();
	}

	public void setNombreCliente(String nombreCliente){
		this.nombreCliente = new SimpleStringProperty(nombreCliente);
	}

	public String getDireccion(){
		return direccion.get();
	}

	public void setDireccion(String direccion){
		this.direccion = new SimpleStringProperty(direccion);
	}

	public String getIdentidad(){
		return identidad.get();
	}

	public void setIdentidad(String identidad){
		this.identidad = new SimpleStringProperty(identidad);
	}

	public Date getFechaNac(){
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac){
		this.fechaNac = fechaNac;
	}

	public String getSexo(){
		return sexo.get();
	}

	public void setSexo(String sexo){
		this.sexo = new SimpleStringProperty(sexo);
	}

	public String getCorreoElectronico(){
		return correoElectronico.get();
	}

	public void setCorreoElectronico(String correoElectronico){
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
	}

	public IntegerProperty codigoClienteProperty(){
		return codigoCliente;
	}

	public StringProperty nombreClienteProperty(){
		return nombreCliente;
	}

	public StringProperty direccionProperty(){
		return direccion;
	}

	public StringProperty identidadProperty(){
		return identidad;
	}

	public StringProperty sexoProperty(){
		return sexo;
	}

	public StringProperty correoElectronicoProperty(){
		return correoElectronico;
	}


}