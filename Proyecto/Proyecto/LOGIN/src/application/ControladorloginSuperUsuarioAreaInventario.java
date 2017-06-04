package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class ControladorloginSuperUsuarioAreaInventario implements Initializable {
	private Main main;
	@FXML private Button btnvolver;
	@FXML private Hyperlink hpllista;
	@FXML private Hyperlink hplregistroSupervisor;
	@FXML private Hyperlink hplregistroVendedor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	@FXML public void mostrarlistaBodegueros(){
		main.mostrarlistaEcncargadosdeBodega();
	}

	@FXML public void atras(){
		main.volverMenu7();
	}
	@FXML public void mostrarRegistroSupervisorInventarios(){
		main.mostrarRegistroSupervisorInventarios();
	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	@FXML public void entrarRegistro(){
		main.mostrarBodeguero();
	}


}
