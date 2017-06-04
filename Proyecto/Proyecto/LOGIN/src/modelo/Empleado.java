package modelo;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Empleado extends Persona{
	protected Date fechaIngreso;
	protected StringProperty estadoCivil;
	protected StringProperty apellidos;
	protected StringProperty contrasena;
//constructor sin contraseña
	public Empleado(String nombres,
			String apellidos, String genero, Date fechaNacimiento,
			Date fechaIngreso, String identidad, String estadoCivil,
			String direccion, String telefono, String correo) {
		super( nombres, identidad, fechaNacimiento, genero, telefono, direccion,correo);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.fechaIngreso = fechaIngreso;
		this.estadoCivil = new SimpleStringProperty(estadoCivil);
	}
	//contructor contraseña
	public Empleado(String nombres,
			String apellidos, String genero, Date fechaNacimiento,
			Date fechaIngreso, String identidad, String estadoCivil,
			String direccion, String telefono, String correo,String contrasena) {
		super( nombres, identidad, fechaNacimiento, genero, telefono, direccion,correo);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.fechaIngreso = fechaIngreso;
		this.estadoCivil = new SimpleStringProperty(estadoCivil);
		this.contrasena = new SimpleStringProperty(contrasena);
	}
	public String getContrasena(){
		//return contrasena.get();
		return identidad.get();
	}

	public void setContrasena(String contrasena){
		this.contrasena = new SimpleStringProperty(contrasena);
	}

	public StringProperty contrasenaProperty(){
		return contrasena;
	}

	public Empleado(String identidad){
		super(identidad);
	}
	public Empleado(){
		super();
	}
	public String getEstadoCivil() {
		return estadoCivil.get();
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = new SimpleStringProperty(estadoCivil);
	}

	public StringProperty EstadoCivilProperty() {
		return estadoCivil;
	}

	// Metodos atributo: fechaIngreso
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	// Metodos atributo: apellidos
		public String getApellidos() {
			return apellidos.get();
		}

		public void setApellidos(String apellidos) {
			this.apellidos = new SimpleStringProperty(apellidos);
		}

		public StringProperty ApellidosProperty() {
			return apellidos;
		}
}
