package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class ControladorloginSuperUsuarioAreaVentas implements Initializable{
	@FXML private Button btnSalir;
	@FXML private Hyperlink hplregistro;
	@FXML private Hyperlink hplregistroSupervisores;
	@FXML private Hyperlink hpltablaVendedores;
	private Main main;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	@FXML public void mostrarlistaVendedores(){
		main.mostrarlistaVendedores();
	}
	@FXML public void regresaralMenu(){
		main.volverMenu4();
	}
	@FXML public void entrarRegistro(){
		main.mostrarVendedores();
	}
	
	@FXML public void entrarRegistroSupervisores(){
		main.mostrarRegistroSupervisoresVentas();
	}

}
