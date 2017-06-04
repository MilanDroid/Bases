package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Bodeguero;
import modelo.RegistroSupervisorInventario;
import utilidades.GestorConexiones;

public class ControladorloginAreaDeBodega implements Initializable {
	public Main main;
	@FXML
	private Button btnSalir;
	@FXML
	private Button btnIngresar;
	@FXML
	private RadioButton rbtSupervisor;
	@FXML
	private RadioButton rbtBodeguero;
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassword;
	private GestorConexiones gestorConexiones;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		gestorConexiones.cerrarConexion();
	}

	@FXML
	public void seccionInventario() {

		if (rbtSupervisor.isSelected() == false
				&& rbtBodeguero.isSelected() == false) {

			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("ERROR");
			mensaje.setHeaderText("Seleccione un Area");
			mensaje.show();
		}

		if (rbtSupervisor.isSelected()) {
			irformularioPrincipal();
		}
		if (rbtBodeguero.isSelected()) {

			irformularioPrincipal2();
		}
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void irformularioPrincipal() {

		String errores = validarCampos();
		if (!errores.equals("")) {
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}

		RegistroSupervisorInventario supervisor = new RegistroSupervisorInventario(
				Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
		gestorConexiones.establecerConexion();
		if (supervisor.verificarUsuario(gestorConexiones.getConexion()) == -1) {
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setContentText("Usuario/Contrasena invalidos");
			mensaje.show();
		} else {
			txtUsuario.clear();
			txtPassword.clear();
			main.irInventario2();
		}
		gestorConexiones.cerrarConexion();

	}

	public void irformularioPrincipal2() {

		String errores = validarCampos();
		if (!errores.equals("")) {
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}

		Bodeguero usuario = new Bodeguero(
				Integer.valueOf(txtUsuario.getText()), txtPassword.getText());
		gestorConexiones.establecerConexion();
		if (usuario.verificarUsuario(gestorConexiones.getConexion()) == -1) {
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setContentText("Usuario/Contrasena invalidos");
			mensaje.show();
		} else {
			txtUsuario.clear();
			txtPassword.clear();
			main.irInventario2();
		}
		gestorConexiones.cerrarConexion();
	}

	public String validarCampos() {
		String errores = "";
		if (txtUsuario.getText().equals(""))
			errores += "Debe ingresar el nombre del Usuario\n";
		if (txtPassword.getText().equals(""))
			errores += "Debe ingresar el Password\n";
		return errores;
	}

	@FXML
	public void volverMenuPrincipal3() {
		main.volverMenu3();
	}

}
