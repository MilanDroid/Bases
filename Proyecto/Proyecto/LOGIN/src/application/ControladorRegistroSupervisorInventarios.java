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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Bodega;
import modelo.Bodeguero;
import modelo.RegistroSupervisorInventario;
import utilidades.GestorConexiones;

public class ControladorRegistroSupervisorInventarios implements Initializable {
	private Main main;

	@FXML Button btnatras;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;
	@FXML private Button btnNuevo;
	@FXML private Button btnEliminar;
	@FXML private Button btnBuscar;
	@FXML private Button btnRestaurar;
	
	@FXML private TextField txtBuscar;
	@FXML private TextField txtNombre;
	@FXML private TextField txtApellido;
	@FXML private TextField txtIdentidad;
	@FXML private TextField txtTelefono;
	@FXML private TextField txtdireccion;
	@FXML private TextField txtEstado;
	@FXML private TextField txtCorreoElectronico;
	
	@FXML private RadioButton rbtnMasculino;
	@FXML private RadioButton rbtnFemenino;
	
	@FXML private TableView<RegistroSupervisorInventario> tblSupervisoresInventario;
	
	@FXML private DatePicker dtpkrFechaNacimiento;
	@FXML private DatePicker dtpkrFechaIngreso;
	
	@FXML private ComboBox<String> cboEstadoCivil;
	@FXML private ComboBox<Bodega> cboBodega;
	
	private ObservableList<String> estadosCiviles;
	
	private GestorConexiones conexion;
	private ObservableList<RegistroSupervisorInventario> SupervisoresInventario;
	private ObservableList<Bodega> bodegas;

	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnNombre;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnApellido;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnIdentidad;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnTelefono;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmndireccion;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnCorreoElectronico;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnGenero;
	@FXML private TableColumn<RegistroSupervisorInventario,Date> clmnFechaNacimiento;
	@FXML private TableColumn<RegistroSupervisorInventario,Date> clmnFechaIngreso;
	@FXML private TableColumn<RegistroSupervisorInventario,String> clmnEstadoCivil;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion=new GestorConexiones();
		conexion.establecerConexion();
		SupervisoresInventario=FXCollections.observableArrayList();
		bodegas=FXCollections.observableArrayList();
		tblSupervisoresInventario.setItems(SupervisoresInventario);
		cboBodega.setItems(bodegas);
		Bodega.llenarBodega(conexion.getConexion(), bodegas);
		estadosCiviles=FXCollections.observableArrayList();
		cboEstadoCivil.setItems(estadosCiviles);
		RegistroSupervisorInventario.llenarTablero(conexion.getConexion(), SupervisoresInventario);
		llenarTablero();
		conexion.cerrarConexion();

	}
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	
	public void llenarTablero(){
		clmnNombre.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("nombre"));
		clmnApellido.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("apellidos"));
		clmnIdentidad.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("identidad"));
		clmnTelefono.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("telefono"));
		clmndireccion.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("direccion"));
		clmnCorreoElectronico.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("correoElectronico"));
		clmnGenero.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("genero"));
		clmnFechaNacimiento.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,Date>("fechaNacimiento"));
		clmnFechaIngreso.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,Date>("fechaIngreso"));
		clmnEstadoCivil.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorInventario,String>("estadoCivil"));
		tblSupervisoresInventario.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<RegistroSupervisorInventario>() {
					@Override
					public void changed(
							ObservableValue<? extends RegistroSupervisorInventario> arg0,
							RegistroSupervisorInventario valorAnterior,
							RegistroSupervisorInventario valorNuevo) {
						if(valorNuevo!=null){
							llenarComponentes(valorNuevo);
						}
					}
				}
		);
		estadosCiviles.add("Soltero");
		estadosCiviles.add("Casado");
		estadosCiviles.add("Viudo");
		estadosCiviles.add("Union Libre");

	}
	
	public void llenarComponentes(RegistroSupervisorInventario c){
		txtNombre.setText(c.getNombre());
		txtApellido.setText(c.getApellidos());
		txtTelefono.setText(c.getTelefono());
		txtdireccion.setText(c.getDireccion());
		txtCorreoElectronico.setText(c.getCorreoElectronico());
		txtIdentidad.setText(c.getIdentidad());
		dtpkrFechaNacimiento.setValue(c.getFechaNacimiento().toLocalDate());
		dtpkrFechaIngreso.setValue(c.getFechaIngreso().toLocalDate());
		cboEstadoCivil.getSelectionModel().select(c.getEstadoCivil());
		cboBodega.getSelectionModel().select(c.getBodega());

		if (c.getGenero().equals("F")){
			rbtnFemenino.setSelected(true);
		}else{
			rbtnMasculino.setSelected(true);
		}
		btnGuardar.setDisable(true);
		btnEliminar.setDisable(false);
		btnActualizar.setDisable(false);
	}
	
	public String validarCampos(){
		String errores = "";
		if (txtNombre.getText().equals(""))
			errores += "Debe ingresar el nombre del Bodeguero\n";
		if (cboEstadoCivil.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar un Estado Civil\n";
		if (txtApellido.getText().equals(""))
			errores += "Debe ingresar el apellido del Bodeguero\n";
		if (txtTelefono.getText().equals(""))
			errores += "Debe ingresar un numero telefonico\n";
		if (txtdireccion.getText().equals(""))
			errores += "Debe ingresar la direccion\n";
		if (txtIdentidad.getText().equals(""))
			errores += "Debe ingresar la identidad\n";
		if (dtpkrFechaNacimiento.getValue()==null)
			errores += "Debe seleccionar una fecha de nacimiento\n";
		if (dtpkrFechaIngreso.getValue()==null)
			errores += "Debe seleccionar una fecha de Ingreso\n";
		if (txtCorreoElectronico.getText().equals(""))
			errores += "Debe ingresar un correo electronico\n";
		if ((rbtnMasculino.isSelected()==false) && (rbtnFemenino.isSelected()==false)){
			errores +="Debe Seleccionar un genero";
		}
		if (cboBodega.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar una Bodega\n";
		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{5}");
		Pattern pat = Pattern.compile("[^0123456789]{1,70}");
		Matcher matcherApe=pat.matcher(txtApellido.getText());
		Matcher matcherNom=pat.matcher(txtNombre.getText());
		Matcher matcher = pattern.matcher(txtIdentidad.getText());
		Pattern pattertel=Pattern.compile("[0-9]{8}");
		Matcher matchertel=pattertel.matcher(txtTelefono.getText());
		if (!matchertel.matches())
			errores += "Su Numero telefonico no coincide con el patron\n";
		if (!matcher.matches())
			errores += "Su identidad no coincide con el patron de la identidad\n";
		if (!matcherNom.matches())
			errores += "Ingrese un nuevo nombre sin numeros\n";
		if (!matcherApe.matches())
			errores += "Ingrese un nuevo Apellido sin numeros\n";
		return errores;
	}
	
	@FXML public void guardarSupervisorInventario(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		RegistroSupervisorInventario supervisorInventario=new RegistroSupervisorInventario(0, txtNombre.getText(), txtApellido.getText(),
				rbtnMasculino.isSelected()?"M":"F", Date.valueOf(dtpkrFechaNacimiento.getValue()),
				Date.valueOf(dtpkrFechaIngreso.getValue()), txtIdentidad.getText(),
				cboEstadoCivil.getSelectionModel().getSelectedItem(),
				txtdireccion.getText(),txtTelefono.getText(),txtCorreoElectronico.getText(),
				cboBodega.getSelectionModel().getSelectedItem(),txtIdentidad.getText());
		conexion.establecerConexion();
		int resultado=supervisorInventario.guardarSupervisorBodega(conexion.getConexion());
		conexion.cerrarConexion();
		if (resultado==1){
			SupervisoresInventario.add(supervisorInventario);
		}
	}
	
	
	
	
	@FXML public void actualizarBodeguero(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al actualizar el Registro");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		RegistroSupervisorInventario c = tblSupervisoresInventario.getSelectionModel().getSelectedItem();
		c.setNombre(txtNombre.getText());
		c.setIdentidad(txtIdentidad.getText());
		c.setTelefono(txtTelefono.getText());
		c.setCorreoElectronico(txtCorreoElectronico.getText());
		c.setGenero(rbtnMasculino.isSelected()?"M":"F");
		c.setFechaNacimiento(Date.valueOf(dtpkrFechaNacimiento.getValue()));
		c.setDireccion(txtdireccion.getText());
		c.setFechaIngreso(Date.valueOf(dtpkrFechaIngreso.getValue()));
		c.setEstadoCivil(cboEstadoCivil.getSelectionModel().getSelectedItem());
		c.setApellidos(txtApellido.getText());
		c.setBodega(cboBodega.getSelectionModel().getSelectedItem());
		conexion.establecerConexion();
		int resultado=c.actualizarSupervisorBodega(conexion.getConexion());
		conexion.cerrarConexion();
		if (resultado==1){
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setContentText("Registro con codigo " + c.getCodigoSupervisorInventario()+ " ha sido actualizado con exito.");
			mensaje.show();
			SupervisoresInventario.set(tblSupervisoresInventario.getSelectionModel().getSelectedIndex(), c);
		}
	}
	
	
	@FXML public void EliminarSupervisorInventario(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar registro");
		alert.setHeaderText("Eliminar registro");
		alert.setContentText("¿Esta seguro de que desea eliminar este registro?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			RegistroSupervisorInventario a = tblSupervisoresInventario.getSelectionModel().getSelectedItem();
			conexion.establecerConexion();
			int resultado = a.eliminarSupervisorInventario(conexion.getConexion());
			conexion.cerrarConexion();
			if (resultado == 1){
				SupervisoresInventario.remove(a);
			}
			btnGuardar.setDisable(false);
			btnEliminar.setDisable(true);
			btnActualizar.setDisable(true);
		}
	}
	
	
	@FXML public void limpiarSupervisorInventario(){
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtIdentidad.setText(null);
		txtdireccion.setText(null);
		txtTelefono.setText(null);
		txtCorreoElectronico.setText(null);
		cboEstadoCivil.getSelectionModel().select(null);
		dtpkrFechaNacimiento.setValue(null);
		dtpkrFechaIngreso.setValue(null);
		rbtnMasculino.setSelected(false);
		rbtnFemenino.setSelected(false);
		btnGuardar.setDisable(false);
		btnEliminar.setDisable(true);
		btnActualizar.setDisable(true);
		cboBodega.getSelectionModel().select(null);
		tblSupervisoresInventario.getSelectionModel().clearSelection();
	}
	
	
	@FXML public void restaurarTablero(){
		conexion.establecerConexion();
		limpiarSupervisorInventario();
		SupervisoresInventario.clear();
		RegistroSupervisorInventario.llenarTablero(conexion.getConexion(), SupervisoresInventario);
		conexion.cerrarConexion();
		txtBuscar.clear();
	}
	
	@FXML public void BusquedaBodeguero(){
		conexion.establecerConexion();
		SupervisoresInventario.clear();
		RegistroSupervisorInventario.BusquedaSupervisorInventario(conexion.getConexion(), SupervisoresInventario, txtBuscar.getText());
		conexion.cerrarConexion();
	}

	
	@FXML public void volver(){
		main.salirRegistroSupervisorBodega();
		limpiarSupervisorInventario();
	}
	
}