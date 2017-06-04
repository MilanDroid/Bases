package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorDescuentos implements Initializable{
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

	@FXML private TextField txtDescuento;
	@FXML private Button btnAplicar;

	@FXML
	public void aplicar(){
		System.out.println("Ingreso: "+Integer.parseInt(txtDescuento.getText()));
		main.agregarDescuento(Integer.parseInt(txtDescuento.getText()));
	}

}
