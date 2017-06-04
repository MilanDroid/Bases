package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControladorloginPrincipal implements Initializable{
	private Main main;
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		
	}

	@FXML public void abrirLoginSuperUsuario(){
		main.mostrarLoginSuperUsuario();
	}
	
	@FXML public void abrirLoginVendedores(){
		main.mostrarLoginVendedores();
	}
	
	@FXML public void abrirLoginAreaDeBodega(){
		main.mostrarLoginEncargadoDeBodega();
	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
		
	}

	
	
}
