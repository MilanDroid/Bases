package modelo;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Persona {
	protected StringProperty nombre;
	protected StringProperty identidad;
	protected Date fechaNacimiento;
	protected StringProperty genero;
	protected StringProperty telefono;
	protected StringProperty direccion;
	protected StringProperty correoElectronico;

	public Persona(String nombre, String identidad, Date fechaNacimiento, String genero, String telefono, String direccion, String correoElectronico){
		this.nombre = new SimpleStringProperty(nombre);
		this.identidad = new SimpleStringProperty(identidad);
		this.fechaNacimiento = fechaNacimiento;
		this.genero = new SimpleStringProperty(genero);
		this.telefono = new SimpleStringProperty(telefono);
		this.direccion = new SimpleStringProperty(direccion);
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
	}

	public Persona(String identidad){
		this.identidad = new SimpleStringProperty(identidad);
	}
	public Persona(){

	}
	public String getNombre(){
		return nombre.get();
	}

	public void setNombre(String nombre){
		this.nombre = new SimpleStringProperty(nombre);
	}

	public String getIdentidad(){
		return identidad.get();
	}

	public void setIdentidad(String identidad){
		this.identidad = new SimpleStringProperty(identidad);
	}

	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero(){
		return genero.get();
	}

	public void setGenero(String genero){
		this.genero = new SimpleStringProperty(genero);
	}

	public String getTelefono(){
		return telefono.get();
	}

	public void setTelefono(String telefono){
		this.telefono = new SimpleStringProperty(telefono);
	}

	public String getDireccion(){
		return direccion.get();
	}

	public void setDireccion(String direccion){
		this.direccion = new SimpleStringProperty(direccion);
	}

	public String getCorreoElectronico(){
		return correoElectronico.get();
	}

	public void setCorreoElectronico(String correoElectronico){
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
	}

	public StringProperty nombreProperty(){
		return nombre;
	}

	public StringProperty identidadProperty(){
		return identidad;
	}

	public StringProperty generoProperty(){
		return genero;
	}

	public StringProperty telefonoProperty(){
		return telefono;
	}

	public StringProperty direccionProperty(){
		return direccion;
	}

	public StringProperty correoElectronicoProperty(){
		return correoElectronico;
	}

}
