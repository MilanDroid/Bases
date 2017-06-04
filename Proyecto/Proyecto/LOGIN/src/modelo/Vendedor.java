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

public class Vendedor extends Empleado{
	private IntegerProperty codigoVendedor;
	private Farmacia farmacia;




	public Vendedor(int codigoVendedor,String nombres, String apellidos, String genero, Date fechaNacimiento, Date fechaIngreso,
			String numeroIdentidad, String estadoCivil, String direccion, String telefono, String correo,
		 Farmacia farmacia) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil, direccion,
				telefono, correo);
		this.codigoVendedor = new SimpleIntegerProperty(codigoVendedor);
		this.setFarmacia(farmacia);
	}
	public Vendedor(int codigoVendedor,String nombres, String apellidos, String genero, Date fechaNacimiento, Date fechaIngreso,
			String numeroIdentidad, String estadoCivil, String direccion, String telefono, String correo,
		 Farmacia farmacia, String contrasena) {
		super(nombres, apellidos, genero, fechaNacimiento, fechaIngreso, numeroIdentidad, estadoCivil, direccion,
				telefono, correo, contrasena);
		this.codigoVendedor = new SimpleIntegerProperty(codigoVendedor);
		this.setFarmacia(farmacia);
	}

	public Vendedor(int usuario, String contrasena) {
		super(contrasena);
		this.codigoVendedor = new SimpleIntegerProperty(usuario);
		//this.nombres = new SimpleStringProperty(nombre);
	}


	//Metodos atributo: codigoVendedor
	public int getCodigoVendedor() {
		return codigoVendedor.get();
	}
	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = new SimpleIntegerProperty(codigoVendedor);
	}
	public IntegerProperty CodigoVendedorProperty() {
		return codigoVendedor;
	}


	public int guardarVendedor(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("INSERT INTO tbl_vendedor( "+
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
				codigoVendedor=new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int actualizarVendedor(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("UPDATE tbl_vendedor "+
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
					"WHERE id_vendedor = ?");
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
			instruccion.setInt(12,codigoVendedor.get());
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
			instruccion = conexion.prepareStatement("DELETE FROM tbl_vendedor "+
					"WHERE id_vendedor = ?");
			instruccion.setInt(1, codigoVendedor.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void llenarTablero(Connection conexion, ObservableList<Vendedor> v){
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT A.id_vendedor, "+
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
					"FROM tbl_vendedor A "+
					"INNER JOIN tbl_farmacia B "+
					"ON (A.codigo_farmacia = B.codigo_farmacia) "+
					"INNER JOIN tbl_bodega C "+
					"ON (B.codigo_bodega = C.codigo_bodega) ");
			while(resultado.next()){
				v.add(new Vendedor(resultado.getInt("id_vendedor"),
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

	public static void BusquedaVendedor(Connection conexion,ObservableList<Vendedor> c, String id){
		try {
			PreparedStatement instruccion;
			instruccion = conexion.prepareStatement("SELECT A.id_vendedor, "+
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
					"FROM tbl_vendedor A "+
					"INNER JOIN tbl_farmacia B "+
					"ON (A.codigo_farmacia = B.codigo_farmacia) "+
					"INNER JOIN tbl_bodega C "+
					"ON (B.codigo_bodega = C.codigo_bodega) "+
					"WHERE A.numero_identidad = ?");
			instruccion.setString(1, id);
			ResultSet resultado = instruccion.executeQuery();
			while(resultado.next()){
				c.add(new Vendedor(resultado.getInt("id_vendedor"),
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
		          "SELECT id_vendedor "+
		          "FROM tbl_vendedor "+
		          "WHERE id_vendedor = ? "+
		          "AND contrasena = sha1(?) "
		          );
		      instruccion.setInt(1, codigoVendedor.get());
		      instruccion.setString(2, identidad.get());
		      ResultSet resultado = instruccion.executeQuery();
		      if(resultado.next())
		        return resultado.getInt("id_vendedor");
		      else
		        return -1;
		    } catch (SQLException e) {

		      e.printStackTrace();
		      return -1;
		    }

	}


	@Override
	public String toString() {
		try {
			return nombre.get()+" "+apellidos.get()+"("+codigoVendedor.get()+")";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Errores Vendedor.to "+e);
		}
		return codigoVendedor.toString();
	}
//carga la info relacionada con el empleado al logearse para poder usarse en factura
	public  Vendedor(Connection conexion,int id){
		super();
		try {
			Statement instruccion=conexion.createStatement();
			ResultSet resultado=instruccion.executeQuery("SELECT id_vendedor, "+
					"nombres, "+
					"apellidos, "+
					"genero, "+
					"fecha_nacimiento, "+
					"fecha_ingreso, "+
					"numero_identidad, "+
					"estado_civil, "+
					"direccion, "+
					"telefono, "+
					"correo "+
					"FROM tbl_vendedor");
			while(resultado.next()){
				this.codigoVendedor = new SimpleIntegerProperty(resultado.getInt("id_vendedor"));
				this.nombre = new SimpleStringProperty(resultado.getString("nombres"));
				this.apellidos = new SimpleStringProperty(resultado.getString("apellidos"));
				this.genero = new SimpleStringProperty(resultado.getString("genero"));
				this.fechaNacimiento = resultado.getDate("fecha_nacimiento");
				this.fechaIngreso = resultado.getDate("fecha_ingreso");
				this.identidad = new SimpleStringProperty(resultado.getString("numero_identidad"));
				this.estadoCivil = new SimpleStringProperty(resultado.getString("estado_civil"));
				this.direccion = new SimpleStringProperty(resultado.getString("direccion"));
				this.telefono = new SimpleStringProperty(resultado.getString("telefono"));
				this.correoElectronico = new SimpleStringProperty(resultado.getString("correo"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Vendedor(RegistroSupervisorVentas superVisor) {
		this.codigoVendedor = superVisor.CodigoSupervisorVentasProperty();
		this.nombre = superVisor.nombreProperty();
		this.apellidos = superVisor.ApellidosProperty();
		this.genero = superVisor.ApellidosProperty();
		this.fechaNacimiento = superVisor.getFechaNacimiento();
		this.fechaIngreso = superVisor.getFechaIngreso();
		this.identidad = superVisor.identidadProperty();
		this.estadoCivil = superVisor.EstadoCivilProperty();
		this.direccion = superVisor.direccionProperty();
		this.telefono = superVisor.telefonoProperty();
		this.correoElectronico = superVisor.correoElectronicoProperty();
	}


	public Farmacia getFarmacia() {
		return farmacia;
	}


	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
}
