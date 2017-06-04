package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Bodeguero;
import modelo.Detalle;
import modelo.Distribuidora;
import modelo.Fabricante;
import modelo.Medicamento;
import modelo.TipoMedicamento;
import utilidades.GestorConexiones;

public class ControladorBusquedaMedicamento implements Initializable{
	private Main main;
	private GestorConexiones gestorConexiones;

	@FXML private ComboBox<TipoMedicamento> cboCategoria;

	@FXML private TableView<Medicamento> tblMedicamento;

	@FXML private TableColumn<Medicamento, String> tblClmnNombreMedicamento;
	@FXML private TableColumn<Medicamento, TipoMedicamento> tblClmnCategoria;
	@FXML private TableColumn<Medicamento, Fabricante> tblClmnFabricante;
	@FXML private TableColumn<Medicamento, Distribuidora> tblClmnDistribuidora;
	@FXML private TableColumn<Medicamento, Date> tblClmnFechaVencimiento;
	@FXML private TableColumn<Medicamento, Double> tblClmnPrecio;

	@FXML private TextField txtMedicamento;
	@FXML private TextField txtCantidad;
	private ObservableList<TipoMedicamento> categoria;
	private ObservableList<Medicamento> medicamento;
	private ObservableList<Detalle> listaDetalle;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		categoria =  FXCollections.observableArrayList();
		medicamento = FXCollections.observableArrayList();

		cboCategoria.setItems(categoria);
		tblMedicamento.setItems(medicamento);

		inicializarComponentes();
		asociarColumnas();
		//inicializarComponentesdeBusqueda();
	}

	@FXML public void atras(){
	//	main.volverMenu6();
	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void inicializarComponentes(){

		TipoMedicamento.llenarListaCategorias(categoria, gestorConexiones.getConexion());
		Medicamento.llenarListaMedicamento2(medicamento, gestorConexiones.getConexion());
	}

	public void asociarColumnas(){

		tblClmnNombreMedicamento.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, String>("nombreMedicamento")
		);
		tblClmnCategoria.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, TipoMedicamento>("tipoMedicamento")
		);
		tblClmnFabricante.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Fabricante>("fabricanteMedicamento")
		);
		tblClmnDistribuidora.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Distribuidora>("distribuidoraMedicamento")
		);
		tblClmnFechaVencimiento.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Date>("fechaVencimiento")
		);
		tblClmnPrecio.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Double>("precioMedicamento")
		);


	}

	@FXML
	public void buscarMedicamento()
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
		medicamento.clear();
		int idmedicamento = cboCategoria.getSelectionModel().getSelectedItem().getIdTipoMedicamento();
		String nombre_medicamento = txtMedicamento.getText();
		Medicamento.llenarListaMedicamentosdeBusqueda2(medicamento, gestorConexiones.getConexion(), idmedicamento, nombre_medicamento);
	}

	@FXML
	public void restaurarMedicamentos(){
		medicamento.clear();
		Medicamento.llenarListaMedicamento2(medicamento, gestorConexiones.getConexion());
	}

	public String validarCampos(){

		String errores = "";

		if (txtMedicamento.getText().equals("")){
			errores+="Porfavor ingrese un medicamento\n";
		}
		if (cboCategoria.getSelectionModel().getSelectedItem() == null){
			errores+="Porfavor ingrese una categoria";

		}

		return errores;
	}

	public ObservableList<Detalle> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(ObservableList<Detalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	@FXML
	public void agregarProducDetalle(){
		try {

			if((tblMedicamento.getSelectionModel().getSelectedItem() != null) &&(!txtCantidad.getText().equals(""))){
				int c = Detalle.cantidadDisponible(gestorConexiones.getConexion(),tblMedicamento.getSelectionModel().getSelectedItem().getCodigoMedicamento());
				if(Integer.valueOf(txtCantidad.getText())<= c){
					listaDetalle.add(new Detalle(0,  tblMedicamento.getSelectionModel().getSelectedItem(), Integer.parseInt(txtCantidad.getText())));
					restaurarMedicamentos();
					txtCantidad.setText(null);
					}
				else{
					Alert mensaje = new Alert(AlertType.ERROR);
					mensaje.setTitle("Error");
					mensaje.setHeaderText("Agregar");
					mensaje.setContentText("Actualmente no tenemos "+txtCantidad.getText()+" de "+
					tblMedicamento.getSelectionModel().getSelectedItem().getNombreMedicamento()+"\n"+
							"Cantidad disponible: "+c);
					mensaje.show();
				}
				}
				else {
					Alert mensaje = new Alert(AlertType.ERROR);
					mensaje.setTitle("Error al buscar");
					mensaje.setHeaderText("Identificados los siguientes errrores");
					mensaje.setContentText("Porfavor seleccione un medicamento e ingrese una cantidad");
					mensaje.show();
				}

		} catch (Exception e) {
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al buscar");
			mensaje.setHeaderText("Identificados los siguientes errrores");
			mensaje.setContentText("Porfavor seleccione un medicamento e ingrese una cantidad");
			mensaje.show();
		}

	}
}

