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

public class ControladorloginVendedores implements Initializable {

	public Main main;
	@FXML private RadioButton rbtSupervisor;
	@FXML private RadioButton rbtVendedor;
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
		main.volverMenu2();
	}
	@FXML public void seleccionArea(){
		if (rbtSupervisor.isSelected()) {
				mostrarFactura();
			}
		if (rbtVendedor.isSelected()) {

				mostrarFactura2();
			}
		}


		public void mostrarFactura(){

			String errores = validarCampos();
			if (!errores.equals("")){
				Alert mensaje = new Alert(AlertType.ERROR);
				mensaje.setTitle("Error al guardar");
				mensaje.setHeaderText("Se encontraron los siguientes errrores");
				mensaje.setContentText(errores);
				mensaje.show();
				return;
			}
			
			RegistroSupervisorVentas usuario = new RegistroSupervisorVentas(Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
			gestorConexiones.establecerConexion();
			if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
				Alert mensaje = new Alert(AlertType.ERROR);
				mensaje.setContentText("Usuario/Contrasena invalidos");
				mensaje.show();
			}else{
				main.mostrarFactura(usuario,"SuperVen");
			}
			gestorConexiones.cerrarConexion();
			//
		}

		public void mostrarFactura2(){

			
			String errores = validarCampos();
			if (!errores.equals("")){
				Alert mensaje = new Alert(AlertType.ERROR);
				mensaje.setTitle("Error al guardar");
				mensaje.setHeaderText("Se encontraron los siguientes errrores");
				mensaje.setContentText(errores);
				mensaje.show();
				return;
			}
			Vendedor usuario = new Vendedor(Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
			gestorConexiones.establecerConexion();
			if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
				Alert mensaje = new Alert(AlertType.ERROR);
				mensaje.setContentText("Usuario/Contrasena invalidos");
				mensaje.show();
			}else{
				main.mostrarFactura(usuario,"Vendedor");
			}
			gestorConexiones.cerrarConexion();
			//
		}
		
		public String validarCampos(){
			String errores = "";
			if (txtUsuario.getText().equals(""))
				errores += "Debe ingresar el nombre del Usuario\n";
			if (txtPassword.getText().equals(""))
				errores += "Debe ingresar el Password\n";
			return errores;
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

}
