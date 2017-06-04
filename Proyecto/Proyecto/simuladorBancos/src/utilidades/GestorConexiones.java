package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConexiones {
	private final String URL = "jdbc:mysql://localhost/db_banco";
	private final String USUARIO = "root";
	private final String CONTRASENA = "";
	private final String CONTROLADOR = "com.mysql.jdbc.Driver";

	private Connection conexion;

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public void establecerConexion(){
		try {
			Class.forName(CONTROLADOR);//Cargar el driver a la clase actual.
			//Si pasa de la siguiente linea la conexion
			//fue exitosa.
			System.out.println("Intentando establecer conexion...");
			conexion = DriverManager.getConnection(
						URL, USUARIO, CONTRASENA
					);
			System.out.println("Conexion Exitosa!");
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
