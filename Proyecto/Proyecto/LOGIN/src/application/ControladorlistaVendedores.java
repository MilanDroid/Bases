package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Vendedor;
import utilidades.GestorConexiones;

public class ControladorlistaVendedores implements Initializable {
	private Main main;
	private GestorConexiones gestorConexiones;

	@FXML private TableView<Vendedor> tblVendedor;

	@FXML private TableColumn<Vendedor, String> tblClmnNombre;
	@FXML private TableColumn<Vendedor, String> tblClmnApellido;
	@FXML private TableColumn<Vendedor, String> tblClmnEdad;
	@FXML private TableColumn<Vendedor, Date> tblClmnFechaIngreso;
	@FXML private TableColumn<Vendedor, String> tblClmnIdEmpleado;
	@FXML private TableColumn<Vendedor, String> tblClmnFaltas;

	@FXML private TextField txtIdentidad;

	private ObservableList<Vendedor> vendedores;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		vendedores = FXCollections.observableArrayList();

		tblVendedor.setItems(vendedores);

		inicializarComponentes();
		asociarColumnas();

	}

	public void inicializarComponentes(){

		Vendedor.llenarTablero(gestorConexiones.getConexion(), vendedores);
	}

	public void asociarColumnas(){

		tblClmnNombre.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, String>("nombre")
		);
		tblClmnApellido.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, String>("apellido")
		);
		tblClmnEdad.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, String>("edad")
		);
		tblClmnFechaIngreso.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, Date>("fechaIngreso")
		);
		tblClmnIdEmpleado.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, String>("idEmpleado")
		);
		tblClmnFaltas.setCellValueFactory(
				 new PropertyValueFactory<Vendedor, String>("faltas")
		);


	}

	@FXML
	public void buscarVendedor()
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
		vendedores.clear();
		String numero_identidad = txtIdentidad.getText();
		Vendedor.BusquedaVendedor(gestorConexiones.getConexion(), vendedores, numero_identidad);
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
	@FXML public void regresarMenu(){
		main.volverMenu5();
	}

}
