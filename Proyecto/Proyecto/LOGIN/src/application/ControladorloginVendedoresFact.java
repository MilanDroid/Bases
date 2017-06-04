package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.RegistroSupervisorVentas;
import modelo.Vendedor;
import utilidades.GestorConexiones;

public class ControladorloginVendedoresFact implements Initializable {
	private int opc;
	public Main main;
	@FXML TextField txtUsuario;
	@FXML PasswordField txtPassword;
	private GestorConexiones gestorConexiones;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		gestorConexiones.cerrarConexion();

	}
	@FXML
	public void volverMenuPrincipal2() {
		main.cancelarDescuento();
	}
		@FXML
		public void mostrarFactura(){
			System.out.println("Llego");

			RegistroSupervisorVentas usuario = new RegistroSupervisorVentas(Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
			gestorConexiones.establecerConexion();
			if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
				Alert mensaje = new Alert(AlertType.ERROR);
				mensaje.setContentText("Usuario/Contrasena invalidos");
				mensaje.show();
			}else{
				if(opc == 1){
					main.decuento(true);
				}
				else if(opc == 2){
					main.cancelarFactura();
				}
			}
			gestorConexiones.cerrarConexion();
			//
		}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	public void limpiar(){
		txtUsuario.clear();
		txtPassword.clear();

	}
	public int getOpc() {
		return opc;
	}
	public void setOpc(int opc) {
		this.opc = opc;
	}


}
