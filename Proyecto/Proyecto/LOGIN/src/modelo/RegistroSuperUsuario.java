package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistroSuperUsuario extends Empleado{
	private IntegerProperty codigoSuperUsuario;

	public RegistroSuperUsuario(int codigoSuperUsuario, String nombres, String apellidos, String genero, int edad,
			Date fechaNacimiento, Date fechaIngreso, String numeroIdentidad, String estadoCivil, String direccion,
			String telefono, String correo) {
		super(nombres, apellidos, genero,  fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil,
				direccion, telefono, correo);
		this.codigoSuperUsuario = new SimpleIntegerProperty(codigoSuperUsuario);
	}

	public RegistroSuperUsuario(int usuario, String contrasena) {
		super(contrasena);
		this.codigoSuperUsuario = new SimpleIntegerProperty(usuario);

	}

	// Metodos atributo: codigoSuperUsuario
	public int getCodigoSuperUsuario() {
		return codigoSuperUsuario.get();
	}

	public void setCodigoSuperUsuario(int codigoSuperUsuario) {
		this.codigoSuperUsuario = new SimpleIntegerProperty(codigoSuperUsuario);
	}

	public IntegerProperty CodigoSuperUsuarioProperty() {
		return codigoSuperUsuario;
	}



	public int verificarUsuario(Connection conexion){
		try {
		      PreparedStatement instruccion =
		          conexion.prepareStatement(
		          "SELECT id_super_usuario "+
		          "FROM tbl_super_usuario "+
		          "WHERE id_super_usuario = ? "+
		          "AND contrasena = sha1(?) "
		          );
		      instruccion.setString(1, String.valueOf(codigoSuperUsuario.get()));
		      instruccion.setString(2, identidad.get());
		      ResultSet resultado = instruccion.executeQuery();
		      if(resultado.next())
		        return resultado.getInt("id_super_usuario");
		      else
		        return -1;
		    } catch (SQLException e) {

		      e.printStackTrace();
		      return -1;
		    }


	}
}