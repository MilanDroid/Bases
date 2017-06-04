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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Farmacia;
import modelo.RegistroSupervisorVentas;
import modelo.Vendedor;
import utilidades.GestorConexiones;

public class ControladorRegistroSupervisorVentas implements Initializable {
	private Main main;
	@FXML Button btnatras;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;
	@FXML private Button btnNuevo;
	@FXML private Button btnRestaurar;
	@FXML private Button btnEliminar;
	@FXML private Button btnBuscar;
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
	@FXML private TableView<RegistroSupervisorVentas> tblRegistroSupervisorVentas;
	@FXML private DatePicker dtpkrFechaNacimiento;
	@FXML private DatePicker dtpkrFechaIngreso;
	@FXML private ComboBox<String> cboEstadoCivil;
	@FXML private ComboBox<Farmacia> cboFarmacia;

	private ObservableList<String> estadosCiviles;
	private GestorConexiones conexion;
	private ObservableList<RegistroSupervisorVentas> supervisoresVentas;
	private ObservableList<Farmacia> farmacias;

	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnNombre;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnApellido;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnIdentidad;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnTelefono;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmndireccion;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnCorreoElectronico;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnGenero;
	@FXML private TableColumn<RegistroSupervisorVentas,Date> clmnFechaNacimiento;
	@FXML private TableColumn<RegistroSupervisorVentas,Date> clmnFechaIngreso;
	@FXML private TableColumn<RegistroSupervisorVentas,String> clmnEstadoCivil;
	@FXML private TableColumn<RegistroSupervisorVentas,Farmacia> clmnFarmacia;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion=new GestorConexiones();
		conexion.establecerConexion();
		supervisoresVentas=FXCollections.observableArrayList();
		tblRegistroSupervisorVentas.setItems(supervisoresVentas);
		estadosCiviles=FXCollections.observableArrayList();
		cboEstadoCivil.setItems(estadosCiviles);
		farmacias=FXCollections.observableArrayList();
		RegistroSupervisorVentas.llenarTablero(conexion.getConexion(), supervisoresVentas);
		cboFarmacia.setItems(farmacias);
		Farmacia.llenarFarmacia(conexion.getConexion(), farmacias);
		llenarTablero();
		conexion.establecerConexion();
	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	@FXML public void salirRegistroVentas(){
		main.salirRegistroSupervisorVentas();
		limpiarSupervisorVentas();
	}
	public void llenarTablero(){
		clmnNombre.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("nombre"));
		clmnApellido.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("apellidos"));
		clmnGenero.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("genero"));
		clmnFechaNacimiento.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,Date>("fechaNacimiento"));
		clmnFechaIngreso.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,Date>("fechaIngreso"));
		clmnIdentidad.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("identidad"));
		clmnEstadoCivil.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("estadoCivil"));
		clmndireccion.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("direccion"));
		clmnTelefono.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("telefono"));
		clmnCorreoElectronico.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,String>("correoElectronico"));
		/*clmnCorreoElectronico.setCellValueFactory(
				 new PropertyValueFactory<RegistroSupervisorVentas,Farmacia>("farmacia"));*/
		tblRegistroSupervisorVentas.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<RegistroSupervisorVentas>() {
					@Override
					public void changed(
							ObservableValue<? extends RegistroSupervisorVentas> arg0,
							RegistroSupervisorVentas valorAnterior,
							RegistroSupervisorVentas valorNuevo) {
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
	public void llenarComponentes(RegistroSupervisorVentas c){
		txtNombre.setText(c.getNombre());
		txtApellido.setText(c.getApellidos());
		txtTelefono.setText(c.getTelefono());
		txtdireccion.setText(c.getDireccion());
		txtCorreoElectronico.setText(c.getCorreoElectronico());
		txtIdentidad.setText(c.getIdentidad());
		dtpkrFechaNacimiento.setValue(c.getFechaNacimiento().toLocalDate());
		dtpkrFechaIngreso.setValue(c.getFechaIngreso().toLocalDate());
		cboEstadoCivil.getSelectionModel().select(c.getEstadoCivil());
		cboFarmacia.getSelectionModel().select(c.getFarmacia());
		btnGuardar.setDisable(true);
		btnEliminar.setDisable(false);
		btnActualizar.setDisable(false);

		if (c.getGenero().equals("F")){
			rbtnFemenino.setSelected(true);
		}else{
			rbtnMasculino.setSelected(true);
		}
	}
	public String validarCampos(){
		String errores = "";
		if (txtNombre.getText().equals(""))
			errores += "Debe ingresar el nombre del Vendedor\n";
		if (cboEstadoCivil.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar un Estado Civil\n";
		if (txtApellido.getText().equals(""))
			errores += "Debe ingresar el apellido del Vendedor\n";
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
	@FXML public void guardarSupervisorVentas(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}

		RegistroSupervisorVentas vendedor=new RegistroSupervisorVentas(0, txtNombre.getText(), txtApellido.getText(),
				rbtnMasculino.isSelected()?"M":"F", Date.valueOf(dtpkrFechaNacimiento.getValue()),
				Date.valueOf(dtpkrFechaIngreso.getValue()), txtIdentidad.getText(),
				cboEstadoCivil.getSelectionModel().getSelectedItem(),
				txtdireccion.getText(),txtTelefono.getText(),txtCorreoElectronico.getText(),
				cboFarmacia.getSelectionModel().getSelectedItem(),txtIdentidad.getText());
		conexion.establecerConexion();
		int resultado=vendedor.guardarSupervisorVentas(conexion.getConexion());
		conexion.cerrarConexion();
		if (resultado==1){
			supervisoresVentas.add(vendedor);
		}
		limpiarSupervisorVentas();
	}

	@FXML public void actualizarSupervisorVentas(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al actualizar el Registro");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
		RegistroSupervisorVentas c = tblRegistroSupervisorVentas.getSelectionModel().getSelectedItem();
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

		conexion.establecerConexion();
		int resultado = c.actualizarSupervisorVentas(conexion.getConexion());
		if (resultado==1){
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setContentText("Registro con codigo " + c.getCodigoSupervisorVentas()+ " ha sido actualizado con exito.");
			mensaje.show();
			supervisoresVentas.set(tblRegistroSupervisorVentas.getSelectionModel().getSelectedIndex(), c);
		}
		conexion.cerrarConexion();
	}

	@FXML public void EliminarSupervisorVentas(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar registro");
		alert.setHeaderText("Eliminar registro");
		alert.setContentText("¿Esta seguro de que desea eliminar este registro?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			RegistroSupervisorVentas a = tblRegistroSupervisorVentas.getSelectionModel().getSelectedItem();
			conexion.establecerConexion();
			int resultado = a.eliminarVendedor(conexion.getConexion());
			conexion.cerrarConexion();
			if (resultado == 1){
				supervisoresVentas.remove(a);
			}
			btnGuardar.setDisable(false);
			btnEliminar.setDisable(true);
			btnActualizar.setDisable(true);
		}
		limpiarSupervisorVentas();
	}
	@FXML public void limpiarSupervisorVentas(){
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtIdentidad.setText(null);
		txtdireccion.setText(null);
		txtTelefono.setText(null);
		txtCorreoElectronico.setText(null);
		cboEstadoCivil.getSelectionModel().clearSelection();
		dtpkrFechaNacimiento.setValue(null);
		dtpkrFechaIngreso.setValue(null);
		rbtnMasculino.setSelected(false);
		rbtnFemenino.setSelected(false);
		btnGuardar.setDisable(false);
		btnEliminar.setDisable(true);
		btnActualizar.setDisable(true);
		cboFarmacia.getSelectionModel().clearSelection();
		tblRegistroSupervisorVentas.getSelectionModel().clearSelection();
	}
	@FXML public void BusquedaSupervisorVentas(){
		supervisoresVentas.clear();
		conexion.establecerConexion();
		RegistroSupervisorVentas.BusquedaSupervisorVentas(conexion.getConexion(), supervisoresVentas, txtBuscar.getText());
		if(supervisoresVentas.isEmpty()){
			RegistroSupervisorVentas.llenarTablero(conexion.getConexion(), supervisoresVentas);
		}
		conexion.cerrarConexion();
	}
	@FXML public void restaurarTablero(){
		conexion.establecerConexion();
		RegistroSupervisorVentas.llenarTablero(conexion.getConexion(), supervisoresVentas);
		conexion.cerrarConexion();
	}
}
