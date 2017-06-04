package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class RegistroSupervisorVentas extends Empleado{
	private IntegerProperty codigoSupervisorVentas;
	private Farmacia farmacia;




	public RegistroSupervisorVentas(int codigoSupervisorVentas,String nombres, String apellidos, String genero, Date fechaNacimiento, Date fechaIngreso,
			String numeroIdentidad, String estadoCivil, String direccion, String telefono, String correo,
		 Farmacia farmacia) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil, direccion,
				telefono, correo);
		this.codigoSupervisorVentas = new SimpleIntegerProperty(codigoSupervisorVentas);
		this.setFarmacia(farmacia);
	}
	public RegistroSupervisorVentas(int codigoSupervisorVentas,String nombres, String apellidos, String genero, Date fechaNacimiento, Date fechaIngreso,
			String numeroIdentidad, String estadoCivil, String direccion, String telefono, String correo,
		 Farmacia farmacia, String contrasena) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil, direccion,
				telefono, correo, contrasena);
		this.codigoSupervisorVentas = new SimpleIntegerProperty(codigoSupervisorVentas);
		this.setFarmacia(farmacia);
	}

	public RegistroSupervisorVentas(int usuario, String contrasena) {
		super(contrasena);
		this.codigoSupervisorVentas = new SimpleIntegerProperty(usuario);
		//this.nombres = new SimpleStringProperty(nombre);
	}


	//Metodos atributo: codigoVendedor
	public int getCodigoSupervisorVentas() {
		return codigoSupervisorVentas.get();
	}
	public void setCodigoSupervisorVentas(int codigoVendedor) {
		this.codigoSupervisorVentas = new SimpleIntegerProperty(codigoVendedor);
	}
	public IntegerProperty CodigoSupervisorVentasProperty() {
		return codigoSupervisorVentas;
	}

	public IntegerProperty codigoSupervisorVentasProperty() {
		return codigoSupervisorVentas;
	}


	public int guardarSupervisorVentas(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("INSERT INTO tbl_supervisor_ventas( "+
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
					"codigo_farmacia, "+
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
			instruccion.setInt(11, farmacia.getCodigoFarmacia());
			instruccion.setString(12, identidad.get());
			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoSupervisorVentas=new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int actualizarSupervisorVentas(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("UPDATE tbl_supervisor_ventas "+
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
					"codigo_farmacia = ? "+
					"WHERE id_supervisor_ventas = ?");
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
			instruccion.setInt(12,codigoSupervisorVentas.get());
			instruccion.setInt(11,farmacia.getCodigoFarmacia());

			return instruccion.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int eliminarVendedor(Connection conexion){
		try {
			PreparedStatement instruccion;
			instruccion = conexion.prepareStatement("DELETE FROM tbl_supervisor_ventas "+
					"WHERE id_supervisor_ventas = ?");
			instruccion.setInt(1, codigoSupervisorVentas.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void llenarTablero(Connection conexion, ObservableList<RegistroSupervisorVentas> v){
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT A.id_supervisor_ventas, "+
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
					"B.codigo_farmacia, "+
					"B.codigo_bodega, "+
					"B.nombre_farmacia, "+
					"B.direccion_farmacia, "+
					"B.correo_farmacia, "+
					"B.telefono_farmacia, "+
					"C.nombre_bodega, "+
					"C.ubicacion_bodega "+
					"FROM tbl_supervisor_ventas A "+
					"INNER JOIN tbl_farmacia B "+
					"ON (A.codigo_farmacia = B.codigo_farmacia) "+
					"INNER JOIN tbl_bodega C "+
					"ON (B.codigo_bodega = C.codigo_bodega) ");
			while(resultado.next()){
				v.add(new RegistroSupervisorVentas(resultado.getInt("id_supervisor_ventas"),
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
						new Farmacia(resultado.getInt("codigo_farmacia"),
								new Bodega(resultado.getInt("codigo_bodega"),
										resultado.getString("nombre_bodega"),
										resultado.getString("ubicacion_bodega")),
										resultado.getString("nombre_farmacia"),
										resultado.getString("direccion_farmacia"),
										resultado.getString("correo_farmacia"),
										resultado.getString("telefono_farmacia"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void BusquedaSupervisorVentas(Connection conexion,ObservableList<RegistroSupervisorVentas> c, String id){
		try {
			PreparedStatement instruccion;
			instruccion = conexion.prepareStatement("SELECT A.id_supervisor_ventas, "+
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
					"B.codigo_farmacia, "+
					"B.codigo_bodega, "+
					"B.nombre_farmacia, "+
					"B.direccion_farmacia, "+
					"B.correo_farmacia, "+
					"B.telefono_farmacia, "+
					"C.nombre_bodega,"+
					"C.ubicacion_bodega "+
					"FROM tbl_supervisor_ventas A "+
					"INNER JOIN tbl_farmacia B "+
					"ON (A.codigo_farmacia = B.codigo_farmacia) "+
					"INNER JOIN tbl_bodega C "+
					"ON (B.codigo_bodega = C.codigo_bodega) "+
					"WHERE A.numero_identidad = ?");
			instruccion.setString(1, id);
			ResultSet resultado = instruccion.executeQuery();
			while(resultado.next()){
				c.add(new RegistroSupervisorVentas(resultado.getInt("id_supervisor_ventas"),
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
						new Farmacia(resultado.getInt("codigo_farmacia"),
								new Bodega(resultado.getInt("codigo_bodega"),
										resultado.getString("nombre_bodega"),
										resultado.getString("ubicacion_bodega")),
										resultado.getString("nombre_farmacia"),
										resultado.getString("direccion_farmacia"),
										resultado.getString("correo_farmacia"),
										resultado.getString("telefono_farmacia"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}




	public int verificarUsuario(Connection conexion){
		try {
		      PreparedStatement instruccion =
		          conexion.prepareStatement(
		          "SELECT id_supervisor_ventas "+
		          "FROM tbl_supervisor_ventas "+
		          "WHERE id_supervisor_ventas = ? "+
		          "AND contrasena = sha1(?) "
		          );
		      instruccion.setInt(1, codigoSupervisorVentas.get());
		      instruccion.setString(2, identidad.get());
		      ResultSet resultado = instruccion.executeQuery();
		      if(resultado.next())
		        return resultado.getInt("id_supervisor_ventas");
		      else
		        return -1;
		    } catch (SQLException e) {

		      e.printStackTrace();
		      return -1;
		    }

	}
	public Farmacia getFarmacia() {
		return farmacia;
	}


	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}




	//carga la info relacionada con el empleado al logearse para poder usarse en factura
			public  RegistroSupervisorVentas(Connection conexion,int id){
				try {
					Statement instruccion=conexion.createStatement();
					ResultSet resultado=instruccion.executeQuery("SELECT id_supervisor_ventas, "+
							"nombres, "+
							"apellidos, "+
							"genero, "+
							"fecha_nacimiento, "+
							"fecha_ingreso, "+
							"numero_identidad, "+
							"estado_civil, "+
							"direccion, "+
							"telefono, "+
							"correo"+
							" FROM tbl_supervisor_ventas");
					while(resultado.next()){
						this.codigoSupervisorVentas = new SimpleIntegerProperty(resultado.getInt("id_supervisor_ventas"));
						this.nombre = new SimpleStringProperty(resultado.getString("nombres"));
						this.apellidos = new SimpleStringProperty(resultado.getString("apellidos"));
						this.genero = new SimpleStringProperty(resultado.getString("genero"));
						this.fechaNacimiento = resultado.getDate("fecha_nacimiento");
						this.fechaIngreso = resultado.getDate("fecha_ingreso");
						this.identidad= new SimpleStringProperty(resultado.getString("numero_identidad"));
						this.estadoCivil = new SimpleStringProperty(resultado.getString("estado_civil"));
						this.direccion = new SimpleStringProperty(resultado.getString("direccion"));
						this.telefono = new SimpleStringProperty(resultado.getString("telefono"));
						this.correoElectronico = new SimpleStringProperty(resultado.getString("correo"));
						//this.codigoFarmacia = new SimpleIntegerProperty();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}


