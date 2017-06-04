package application;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Distribuidora;
import modelo.Fabricante;
import modelo.Medicamento;
import modelo.TipoMedicamento;
import utilidades.GestorConexiones;

public class ControladorFormulario1 implements Initializable{
	private Main main;

	private GestorConexiones gestorConexiones;

	@FXML Button btnActualizar;
	@FXML Button btnEliminar;
	@FXML Button btnGuardar;
	@FXML Button btnNuevo;

	@FXML private ComboBox<TipoMedicamento> cboTipoMedicamentos;
	@FXML private ComboBox<Fabricante> cboFabricantes;
	@FXML private ComboBox<Distribuidora> cboDistribuidoras;
	@FXML private TableView<Medicamento> tblViewMedicamento;

	@FXML private TextField txtProducto;
	@FXML private TextField txtPrecio;
	@FXML private DatePicker dpckFechaVencimiento;

	@FXML private TableColumn<Medicamento, TipoMedicamento> tblClmnTipoMedicamento;
	@FXML private TableColumn<Medicamento, String> tblClmnProducto;
	@FXML private TableColumn<Medicamento, Fabricante> tblClmnFabricante;
	@FXML private TableColumn<Medicamento, Date> tblClmnFechaVencimiento;
	@FXML private TableColumn<Medicamento, Distribuidora> tblClmnFechaDistribuidora;
	@FXML private TableColumn<Medicamento, Double> tblClmnPrecio;


	private ObservableList<Fabricante> fabricantes;
	private ObservableList<Distribuidora> distribuidoras;
	private ObservableList<TipoMedicamento> tipoMedicamentos;
	private ObservableList<Medicamento> medicamentos;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		fabricantes = FXCollections.observableArrayList();
		distribuidoras = FXCollections.observableArrayList();
		tipoMedicamentos = FXCollections.observableArrayList();
		medicamentos = FXCollections.observableArrayList();
		cboDistribuidoras.setItems(distribuidoras);
		cboFabricantes.setItems(fabricantes);
		cboTipoMedicamentos.setItems(tipoMedicamentos);
		tblViewMedicamento.setItems(medicamentos);
		inicializarInformacion();
		asociarColumnas();
		gestorConexiones.cerrarConexion();

		tblViewMedicamento.
				getSelectionModel().
				selectedItemProperty().
				addListener(new ChangeListener<Medicamento>() {
					@Override
					public void changed(
							ObservableValue<? extends Medicamento> observable,
							Medicamento oldValue, //valor seleccionado previamente
							Medicamento newValue //valor seleccionado actualmente
					) {

						if (newValue!=null){
							llenarComponentes(newValue);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
							btnGuardar.setDisable(true);
						}
					}

				});

	}

	public void llenarComponentes(Medicamento a){
		txtProducto.setText(a.getNombreMedicamento());
		cboFabricantes.getSelectionModel().select(a.getFabricanteMedicamento());
		cboDistribuidoras.getSelectionModel().select(a.getDistribuidoraMedicamento());
		cboTipoMedicamentos.getSelectionModel().select(a.getTipoMedicamento());
		txtProducto.setText(a.getNombreMedicamento());
		dpckFechaVencimiento.setValue(a.getFechaVencimiento().toLocalDate());

	}

	public void inicializarInformacion(){
		Fabricante.llenarListaFabricantes(
				fabricantes, gestorConexiones.getConexion()
		);
		Distribuidora.llenarListaDistribuidoras(
			distribuidoras, gestorConexiones.getConexion()
		);
		TipoMedicamento.llenarListaCategorias(
			tipoMedicamentos, gestorConexiones.getConexion()
		);
		Medicamento.llenarListaMedicamento(
				medicamentos, gestorConexiones.getConexion()
		);
	}

	@FXML
	public void almacenarRegistro(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}


		//Crear un nuevo objeto del tipo aplicacion con los valores
		//que estan actualmente en los componentes
		Medicamento a = new Medicamento(
				0,
				txtProducto.getText(),
				Date.valueOf(dpckFechaVencimiento.getValue()),
				Double.valueOf(txtPrecio.getText()),
				cboDistribuidoras.getSelectionModel().getSelectedItem(),
				cboFabricantes.getSelectionModel().getSelectedItem(),
				cboTipoMedicamentos.getSelectionModel().getSelectedItem()
		);
		//Llamar al metodo almacenarRegistro()
		gestorConexiones.establecerConexion();
		int resultado = a.almacenarRegistro(gestorConexiones.getConexion());
		gestorConexiones.cerrarConexion();

		if (resultado == 1)
			medicamentos.add(a);
	}

	@FXML
	public void actualizarRegistro(){
		Medicamento a = tblViewMedicamento.getSelectionModel().getSelectedItem();
		a.setDistribuidoraMedicamento(cboDistribuidoras.getSelectionModel().getSelectedItem());
		a.setFabricanteMedicamento(cboFabricantes.getSelectionModel().getSelectedItem());
		a.setFechaVencimiento(Date.valueOf(dpckFechaVencimiento.getValue()));
		a.setTipoMedicamento(cboTipoMedicamentos.getSelectionModel().getSelectedItem());
		a.setNombreMedicamento(txtProducto.getText());
		a.setPrecioMedicamento(Double.valueOf(txtPrecio.getText()));
		gestorConexiones.establecerConexion();
		int resultado = a.actualizarRegistro(gestorConexiones.getConexion());
		gestorConexiones.cerrarConexion();

		if (resultado==1){
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setContentText("Registro con codigo " + a.getCodigoMedicamento()+ " ha sido actualizado con exito.");
			mensaje.show();
			medicamentos.set(
				tblViewMedicamento.getSelectionModel().getSelectedIndex(),
				a
			);
		}

	}

	@FXML
	public void eliminarRegistro(){

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar registro");
		alert.setHeaderText("Eliminar registro");
		alert.setContentText("¿Esta seguro de que desea eliminar este registro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Medicamento a = tblViewMedicamento.getSelectionModel().getSelectedItem();
			gestorConexiones.establecerConexion();
			int resultado = a.eliminarRegistro(gestorConexiones.getConexion());
			gestorConexiones.cerrarConexion();
			if (resultado == 1){
				medicamentos.remove(a);
			}
			limpiarComponentes();
		}
	}

	public void asociarColumnas(){
		tblClmnTipoMedicamento.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, TipoMedicamento>("tipoMedicamento")
		);
		tblClmnProducto.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, String>("nombreMedicamento")
		);
		tblClmnFabricante.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Fabricante>("fabricanteMedicamento")
		);
		tblClmnFechaVencimiento.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Date>("fechaVencimiento")
		);
		tblClmnFechaDistribuidora.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Distribuidora>("distribuidoraMedicamento")
		);
		tblClmnPrecio.setCellValueFactory(
				 new PropertyValueFactory<Medicamento, Double>("precioMedicamento")
		);
	}

	@FXML
	public void limpiarComponentes(){
		cboFabricantes.getSelectionModel().select(null);
		cboDistribuidoras.getSelectionModel().select(null);
		cboTipoMedicamentos.getSelectionModel().select(null);
		//tblViewAplicaciones;
		txtProducto.setText(null);
		txtPrecio.setText(null);
		dpckFechaVencimiento.setValue(null);

		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
		btnGuardar.setDisable(false);
	}

	public String validarCampos(){
		String errores = "";
		if (txtProducto.getText().equals(""))
			errores += "Debe ingresar el nombre del producto\n";
		if (txtPrecio.getText().equals(""))
			errores += "Debe ingresar el precio\n";
		if (cboFabricantes.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar una fabricante\n";
		if (cboDistribuidoras.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar una distribuidora\n";
		if (cboTipoMedicamentos.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar un tipo de medicamento\n";
		if (dpckFechaVencimiento.getValue()==null)
			errores += "Debe seleccionar una fecha\n";


		/*Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{5}");
		Matcher matcher = pattern.matcher(txtProducto.getText());
		if (!matcher.matches())
			errores += "Nombre aplicacion no coincide con el patron de la identidad\n";
*/
		return errores;
	}



	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
