package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class RegistroSupervisorInventario extends Empleado{
	private IntegerProperty codigoSupervisorInventario;
	private Bodega bodega;

	public RegistroSupervisorInventario(int codigoSupervisorInventario, String nombres, String apellidos, String genero,
			Date fechaNacimiento, Date fechaIngreso, String numeroIdentidad, String estadoCivil, String direccion,
			String telefono, String correo, Bodega bodega) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil,
				direccion, telefono, correo);
		this.codigoSupervisorInventario = new SimpleIntegerProperty(codigoSupervisorInventario);
		this.bodega = bodega;
	}
	
	public RegistroSupervisorInventario(int codigoSupervisorInventario, String nombres, String apellidos, String genero,
			Date fechaNacimiento, Date fechaIngreso, String numeroIdentidad, String estadoCivil, String direccion,
			String telefono, String correo, Bodega bodega,String contraseña) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil,
				direccion, telefono, correo,contraseña);
		this.codigoSupervisorInventario = new SimpleIntegerProperty(codigoSupervisorInventario);
		this.bodega = bodega;
	}

	public RegistroSupervisorInventario(int usuario, String contrasena) {
		super(contrasena);
		this.codigoSupervisorInventario = new SimpleIntegerProperty(usuario);
		//this.nombres = new SimpleStringProperty(nombre);
	}

	//Bodega
		public Bodega getBodega(){
			return bodega;
		}

		public void setBodega(Bodega bodega){
			this.bodega = bodega;
		}
	//Metodos atributo: codigoSupervisorInventario
		public int getCodigoSupervisorInventario() {
			return codigoSupervisorInventario.get();
		}
		public void setCodigoSupervisorInventario(int codigoSupervisorInventario) {
			this.codigoSupervisorInventario = new SimpleIntegerProperty(codigoSupervisorInventario);
		}
		public IntegerProperty CodigoSupervisorInventarioProperty() {
			return codigoSupervisorInventario;
		}



	public int verificarUsuario(Connection conexion){
		try {
			PreparedStatement instruccion =
					conexion.prepareStatement(
					"SELECT id_supervisor_bodega "+
					"FROM tbl_supervisor_bodega "+
					"WHERE id_supervisor_bodega = ? "+
					"AND contrasena = sha1(?) "
					);
			instruccion.setInt(1, codigoSupervisorInventario.get());
			instruccion.setString(2, identidad.get());
			ResultSet resultado = instruccion.executeQuery();
			if(resultado.next())
				return resultado.getInt("id_supervisor_bodega");
			else
				return -1;
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}

	}
	
	
	public int guardarSupervisorBodega(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("INSERT INTO tbl_supervisor_bodega( "+
					"nombres, "+
					"apellidos, "+
					"genero, "+
					"fecha_nacimiento, "+
					"fecha_ingreso, "+
					"numero_identidad, "+
					"estado_civil, "+
					"direccion, "+
					"telefono, "
					+"correo, "+
					"codigo_bodega, "+
					"contrasena )"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,sha1(?))");
			instruccion.setString(1, nombre.get());
			instruccion.setString(2,apellidos.get());
			instruccion.setString(3, genero.get());
			instruccion.setDate(4, fechaNacimiento);
			instruccion.setDate(5, fechaIngreso);
			instruccion.setString(6, identidad.get());
			instruccion.setString(7, estadoCivil.get());
			instruccion.setString(8, direccion.get());
			instruccion.setString(9, telefono.get());
			instruccion.setString(10, correoElectronico.get());
			instruccion.setInt(11,bodega.getCodigoBodega());
			instruccion.setString(12, contrasena.get());

			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoSupervisorInventario=new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static void llenarTablero(Connection conexion, ObservableList<RegistroSupervisorInventario> v){
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT A.id_supervisor_bodega, "+
					"A.nombres, "+
					"A.apellidos, "+
					"A.genero, "+
					"A.fecha_nacimiento, "+
					"A.fecha_ingreso, "+
					"A.numero_identidad, "+
					"A.estado_civil, "+
					"A.direccion, "+
					"A.telefono, "+
					"A.correo, "+
					"A.codigo_bodega, "+
					"C.nombre_bodega, "+
					"C.ubicacion_bodega "+
					"FROM tbl_supervisor_bodega A "+
					"INNER JOIN tbl_bodega C "+
					"ON (A.codigo_bodega = C.codigo_bodega) ");
			while(resultado.next()){
				v.add(new RegistroSupervisorInventario(resultado.getInt("id_supervisor_bodega"),
						resultado.getString("nombres"),
						resultado.getString("apellidos"),
						resultado.getString("genero"),
						resultado.getDate("fecha_nacimiento"),
						resultado.getDate("fecha_ingreso"),
						resultado.getString("numero_identidad"),
						resultado.getString("estado_civil"),
						resultado.getString("direccion"),
						resultado.getString("telefono"),
						resultado.getString("correo"),
								new Bodega(resultado.getInt("codigo_bodega"),
										resultado.getString("nombre_bodega"),
										resultado.getString("ubicacion_bodega"))
										));
			}
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int actualizarSupervisorBodega(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("UPDATE tbl_supervisor_bodega "+
					"SET nombres = ?, "+
					"apellidos = ?, "+
					"genero = ?, "+
					"fecha_nacimiento = ?, "+
					"fecha_ingreso = ?, "+
					"numero_identidad = ?, "+
					"estado_civil = ?, "+
					"direccion = ?, "+
					"telefono = ?, "+
					"correo = ?, "+
					"codigo_bodega= ? "+
					"WHERE id_supervisor_bodega = ?");
			instruccion.setString(1, nombre.get());
			instruccion.setString(2,apellidos.get());
			instruccion.setString(3, genero.get());
			instruccion.setDate(4, fechaNacimiento);
			instruccion.setDate(5, fechaIngreso);
			instruccion.setString(6, identidad.get());
			instruccion.setString(7, estadoCivil.get());
			instruccion.setString(8, direccion.get());
			instruccion.setString(9, telefono.get());
			instruccion.setString(10, correoElectronico.get());
			instruccion.setInt(12,codigoSupervisorInventario.get());
			instruccion.setInt(11,bodega.getCodigoBodega());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
		
		
		public int eliminarSupervisorInventario(Connection conexion){
			try {
				PreparedStatement instruccion;
				instruccion = conexion.prepareStatement("DELETE FROM tbl_supervisor_bodega "+
						"WHERE id_supervisor_bodega = ?");
				instruccion.setInt(1, codigoSupervisorInventario.get());
				return instruccion.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		
		
		
		
		public static void BusquedaSupervisorInventario(Connection conexion,ObservableList<RegistroSupervisorInventario> c, String id){
			try {
				PreparedStatement instruccion;
				instruccion = conexion.prepareStatement("SELECT A.id_supervisor_bodega, "+
						"A.nombres, "+
						"A.apellidos, "+
						"A.genero, "+
						"A.fecha_nacimiento, "+
						"A.fecha_ingreso, "+
						"A.numero_identidad, "+
						"A.estado_civil, "+
						"A.direccion, "+
						"A.telefono, "+
						"A.correo, "+
						"A.codigo_bodega, "+
						"C.nombre_bodega,"+
						"C.ubicacion_bodega "+
						"FROM tbl_supervisor_bodega A "+
						"INNER JOIN tbl_bodega C "+
						"ON (A.codigo_bodega = C.codigo_bodega) "+
						"WHERE A.numero_identidad = ?");
				instruccion.setString(1, id);
				ResultSet resultado = instruccion.executeQuery();
				while(resultado.next()){
					c.add(new RegistroSupervisorInventario(resultado.getInt("id_supervisor_bodega"),
							resultado.getString("nombres"),
							resultado.getString("apellidos"),
							resultado.getString("genero"),
							resultado.getDate("fecha_nacimiento"),
							resultado.getDate("fecha_ingreso"),
							resultado.getString("numero_identidad"),
							resultado.getString("estado_civil"),
							resultado.getString("direccion"),
							resultado.getString("telefono"),
							resultado.getString("correo"),
									new Bodega(resultado.getInt("codigo_bodega"),
											resultado.getString("nombre_bodega"),
											resultado.getString("ubicacion_bodega"))
											));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}




	

}