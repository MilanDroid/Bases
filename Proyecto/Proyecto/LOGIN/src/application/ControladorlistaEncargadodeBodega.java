package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Bodeguero;
import utilidades.GestorConexiones;

public class ControladorlistaEncargadodeBodega implements Initializable {
	private Main main;
	private GestorConexiones gestorConexiones;

	@FXML private TableView<Bodeguero> tblBodeguero;

	@FXML private TableColumn<Bodeguero, String> tblClmnNombre;
	@FXML private TableColumn<Bodeguero, String> tblClmnApellido;
	@FXML private TableColumn<Bodeguero, String> tblClmnEdad;
	@FXML private TableColumn<Bodeguero, Date> tblClmnFechaIngreso;
	@FXML private TableColumn<Bodeguero, String> tblClmnIdEmpleado;
	@FXML private TableColumn<Bodeguero, String> tblClmnFaltas;

	@FXML private TextField txtIdentidad;

	private ObservableList<Bodeguero> bodegueros;


	@FXML Button btnSalir;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		bodegueros = FXCollections.observableArrayList();

		tblBodeguero.setItems(bodegueros);

		inicializarComponentes();
		asociarColumnas();


	}

	public void inicializarComponentes(){

		Bodeguero.llenarTablero(gestorConexiones.getConexion(), bodegueros);
	}

	public void asociarColumnas(){

		tblClmnNombre.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, String>("nombres")
		);
		tblClmnApellido.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, String>("apellidos")
		);
		tblClmnEdad.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, String>("edad")
		);
		tblClmnFechaIngreso.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, Date>("fechaIngreso")
		);
		tblClmnIdEmpleado.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, String>("codigoBodeguero")
		);
		tblClmnFaltas.setCellValueFactory(
				 new PropertyValueFactory<Bodeguero, String>("faltas")
		);


	}

	@FXML
	public void buscarBodeguero()
	{

		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al buscar");
			mensaje.setHeaderText("Identificados los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		bodegueros.clear();
		String numero_identidad = txtIdentidad.getText();
		Bodeguero.BusquedaBodeguero(gestorConexiones.getConexion(), bodegueros, numero_identidad);
	}

	public String validarCampos(){

		String errores = "";

		if (txtIdentidad.getText().equals("")){
			errores+="Porfavor ingrese un numero de identidad\n";
		}

		return errores;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML private void atrassuper(){
		main.volverMenu8();

	}


}
