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

public class Cliente extends Persona{
	private IntegerProperty codigoCliente;

	public Cliente(int codigoCliente,String nombre, String identidad,
			Date fechaNacimiento,String genero,String telefono, String direccion,
			  String correoElectronico) {
		super(nombre,identidad, fechaNacimiento,  genero,
				 telefono, direccion,correoElectronico) ;
		this.codigoCliente = new SimpleIntegerProperty(codigoCliente);
	}

	//Metodos atributo: codigoCliente
	public int getCodigoCliente() {
		return codigoCliente.get();
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = new SimpleIntegerProperty(codigoCliente);
	}
	public IntegerProperty CodigoClienteProperty() {
		return codigoCliente;
	}
	public int guardarCliente(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareCall("INSERT INTO tbl_cliente( "+
					"codigo_cliente, "+
					"nombre_cliente, "+
					"telefono_cliente, "+
					"direccion, "+
					"identidad, "+
					"fecha_nacimiento, "+
					"sexo, "+
					"correo_electronico "+
					") "+
					"VALUES (?,?,?,?,?,?,?,?)");
			instruccion.setInt(1,codigoCliente.get());
			instruccion.setString(2,nombre.get());
			instruccion.setString(3, telefono.get());
			instruccion.setString(4, direccion.get());
			instruccion.setString(5, identidad.get());
			instruccion.setDate(6, fechaNacimiento);
			instruccion.setString(7, genero.get());
			instruccion.setString(8, correoElectronico.get());
			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoCliente=new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public int actualizarCliente(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("UPDATE tbl_cliente "+
					"SET nombre_cliente = ?, "+
					"telefono_cliente = ?, "+
					"direccion = ?, "+
					"identidad = ?, "+
					"fecha_nacimiento = ?, "+
					"sexo = ?, "+
					"correo_electronico = ? "+
					"WHERE codigo_cliente = ?");
			instruccion.setString(1,nombre.get());
			instruccion.setString(2, telefono.get());
			instruccion.setString(3, direccion.get());
			instruccion.setString(4, identidad.get());
			instruccion.setDate(5, fechaNacimiento);
			instruccion.setString(6, genero.get());
			instruccion.setString(7, correoElectronico.get());
			instruccion.setInt(8, codigoCliente.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public int eliminarCliente(Connection conexion){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("DELETE FROM tbl_cliente "+
																	"WHERE codigo_cliente = ?");
			instruccion.setInt(1,codigoCliente.get());
			return instruccion.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static void llenarTablero(Connection conexion,ObservableList<Cliente> c){
		try {
		Statement instruccion=conexion.createStatement();
			ResultSet resultado;
				resultado = instruccion.executeQuery("SELECT codigo_cliente, "+
				"nombre_cliente, "+
				"telefono_cliente, "+
				"direccion, "+
				"identidad, "+
				"fecha_nacimiento, "+
				"sexo, "+
				"correo_electronico "+
				"FROM tbl_cliente");
				while(resultado.next()){
					c.add(new Cliente(resultado.getInt("codigo_cliente"),
							resultado.getString("nombre_cliente"),
							resultado.getString("identidad"),
							resultado.getDate("fecha_nacimiento"),
							resultado.getString("sexo"),
							resultado.getString("telefono_cliente"),
							resultado.getString("direccion"),
							resultado.getString("correo_electronico")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void BusquedaCliente(Connection conexion,ObservableList<Cliente> c, String id){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("SELECT codigo_cliente, "+
					"nombre_cliente, "+
					"telefono_cliente, "+
					"direccion, "+
					"identidad, "+
					"fecha_nacimiento, "+
					"sexo, "+
					"correo_electronico "+
					"FROM tbl_cliente "+
					"WHERE identidad = ?");
			instruccion.setString(1, id);
			ResultSet resultado = instruccion.executeQuery();
			while(resultado.next()){
				c.add(new Cliente(resultado.getInt("codigo_cliente"),
						resultado.getString("nombre_cliente"),
						resultado.getString("identidad"),
						resultado.getDate("fecha_nacimiento"),
						resultado.getString("sexo"),
						resultado.getString("telefono_cliente"),
						resultado.getString("direccion"),
						resultado.getString("correo_electronico")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//carga la info en facturads
	public static Cliente BusquedaCliente(Connection conexion, String id){
		try {
			PreparedStatement instruccion=conexion.prepareStatement("SELECT codigo_cliente, "+
					"nombre_cliente, "+
					"telefono_cliente, "+
					"direccion, "+
					"identidad, "+
					"fecha_nacimiento, "+
					"sexo, "+
					"correo_electronico "+
					"FROM tbl_cliente "+
					"WHERE identidad = ?");
			instruccion.setString(1, id);
			ResultSet resultado = instruccion.executeQuery();
			while(resultado.next()){
				return  new Cliente(resultado.getInt("codigo_cliente"),
						resultado.getString("nombre_cliente"),
						resultado.getString("identidad"),
						resultado.getDate("fecha_nacimiento"),
						resultado.getString("sexo"),
						resultado.getString("direccion"),
						resultado.getString("telefono_cliente"),
						resultado.getString("correo_electronico"));

			}
			//System.out.println("Se encontro un cliente: "+c.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return nombre.get();
	}



}
