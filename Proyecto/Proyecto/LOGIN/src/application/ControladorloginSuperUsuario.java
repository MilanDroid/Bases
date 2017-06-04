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
import modelo.RegistroSuperUsuario;
import utilidades.GestorConexiones;

public class ControladorloginSuperUsuario implements Initializable {
	private Main main;
	@FXML
	private RadioButton rbtAreaVentas;
	@FXML
	private RadioButton rbtAreaInventario;
	@FXML
	TextField txtUsuario;
	@FXML
	PasswordField txtPassword;
	private GestorConexiones gestorConexiones;
	private String nombres;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		gestorConexiones.cerrarConexion();
	}

	/*
	 * public int ingresarAreaVentas(int a){ int a1=0; if
	 * (rbtAreaVentas.isSelected()) { a1=1; } return a1;
	 * 
	 * }
	 */
	@FXML public void seleccionArea() {
		
		if (rbtAreaVentas.isSelected()==false && rbtAreaInventario.isSelected()==false){
			
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("ERROR");
			mensaje.setHeaderText("Seleccione un Area");
			mensaje.show();
		}

		if (rbtAreaInventario.isSelected()) {
			irLoginSuperUsuarioAreaInventario();
		}
		if (rbtAreaVentas.isSelected()) {

			irLoginSuperUsuarioAreaVentas();
		}
		
		
	}

	public void irLoginSuperUsuarioAreaInventario() {
		
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		
		
		RegistroSuperUsuario usuario = new RegistroSuperUsuario(
				Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
		gestorConexiones.establecerConexion();
		
		if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setContentText("Usuario/Contrasena invalidos");
			mensaje.show();
		}else{
			txtUsuario.clear();
			txtPassword.clear();
			main.mostrarSuperUsuarioAreaInventario();
		}
		gestorConexiones.cerrarConexion();
	}

	public void irLoginSuperUsuarioAreaVentas() {
		
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		
		RegistroSuperUsuario usuario = new RegistroSuperUsuario(Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
		gestorConexiones.establecerConexion();
		if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setContentText("Usuario/Contrasena invalidos");
			mensaje.show();
		}else{
			txtUsuario.clear();
			txtPassword.clear();
			main.mostrarSuperUsuarioAreaVentas();
		}
		gestorConexiones.cerrarConexion();
	}
		


	
	public String validarCampos(){
		String errores = "";
		if (txtUsuario.getText().equals(""))
			errores += "Debe ingresar el nombre del Usuario\n";
		if (txtPassword.getText().equals(""))
			errores += "Debe ingresar el Password\n";
		return errores;
	}
	
	@FXML
	public void volverMenuPrincipal1() {
		main.volverMenu1();

	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;

	}

}
