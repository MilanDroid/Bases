package application;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import modelo.Cliente;
import modelo.Detalle;
import modelo.Factura;
import modelo.Medicamento;
import modelo.RegistroSupervisorVentas;
import modelo.Vendedor;
import utilidades.GestorConexiones;

public class ControladorFactura implements Initializable {
	private Main main;
	private static Calendar fecha = new GregorianCalendar();
	private GestorConexiones gestorConexiones;
	//private ObservableList<Cliente> listaClientes;
	private ObservableList<String> formaPago;
	private ObservableList<Detalle> listaDetalle;
	@FXML private ComboBox<String> cboFormaPago;
	@FXML private  TextField txtNumIdentidad;
	@FXML private TextField txtNombre;
	@FXML private TableView<Detalle> tlvDetalle;
	@FXML private TextField txtTot;
	@FXML private TextField txtIsv;
	@FXML private TextField txtTotPagar;
	@FXML private DatePicker dtpFecha;
	@FXML private TextField txtdescuento;
	private double subtot = 0;
	private double isv;
	private double total;
	private double descuento = 0;
	private double subdes;
	private RegistroSupervisorVentas superVisor;
	private Vendedor vendedor;
	private String tipo;
	private Cliente cliente;
	 @FXML private Button txtEliminar;
	//tablecolum
	@FXML private TableColumn<Detalle,Number> clmnCodigoMedicamento;
	@FXML private TableColumn<Detalle,Medicamento> clmnMedicamento;
	@FXML private TableColumn<Detalle,Number> clmnCantidad;
	@FXML private TableColumn<Detalle,Number> clmnPrecio;
	@FXML private TableColumn<Detalle,Number> clmnTotal;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gestorConexiones = new GestorConexiones();
		// TODO Auto-generated method stub
		try {
			llenarComboBox();
		} catch (Exception e) {
			System.out.println(e);
		}
		cambiosTlv();
		asociarColumnas();

	}
	public Main getMain() {
		return main;
	}


	public void setMain(Main main) {
		this.main = main;

	}
	@FXML
	public void cobrar(){

		if((cboFormaPago.getSelectionModel().getSelectedItem() != null)&&(!txtNombre.getText().equals("")) && (!listaDetalle.isEmpty())){
			if(cboFormaPago.getSelectionModel().getSelectedItem().equals("Efectivo")){
				if(tipo.equals("SuperVen")){
					main.mostrarEfectivo(total,
							new Factura(0,null,null,cliente,
									Date.valueOf(dtpFecha.getValue()),subtot,
									isv,total,"Efectivo" , new Vendedor(superVisor)),listaDetalle);


				}
				else if(tipo.equals("Vendedor")){
					main.mostrarEfectivo(total,
							new Factura(0,null,null,cliente,
									Date.valueOf(dtpFecha.getValue()),subtot,
									isv,total, "Efectivo" , vendedor),listaDetalle);
				}
			}
			if(cboFormaPago.getSelectionModel().getSelectedItem().equals("Tarjeta")&&(!txtNombre.getText().equals("")) && (!listaDetalle.isEmpty())){
				if(tipo.equals("SuperVen")){
					main.mostrarTarjeta(total,
							new Factura(0,null,null,cliente,
									Date.valueOf(dtpFecha.getValue()),subtot,
									isv,total, "Tarjeta", new Vendedor(superVisor)),listaDetalle);


				}
				else if(tipo.equals("Vendedor")){
					main.mostrarTarjeta(total,
							new Factura(0,null,null,cliente,
									Date.valueOf(dtpFecha.getValue()),subtot,
									isv,total, "Tarjeta", vendedor),listaDetalle);
				}

			}
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setContentText("llene todos los campos");
	        alert.showAndWait();
		}


	}
	public void llenarComboBox(){
			formaPago = FXCollections.observableArrayList();
			//listaClientes = FXCollections.observableArrayList();
			cboFormaPago.setItems(formaPago);
			formaPago.add("Efectivo");
			formaPago.add("Tarjeta");
			listaDetalle = FXCollections.observableArrayList();
			tlvDetalle.setItems(listaDetalle);
			//listaClientes = FXCollections.observableArrayList();

			//TODO: prueba de lista
			/*listaDetalle.add(new Detalle(0, new Factura(),
								new Medicamento(2,"panadol", Date.valueOf("2015-02-13"),
										16.00, null, null, null),
								2, 23.2));*/
			dtpFecha.setValue((new Date(fecha.getTimeInMillis())).toLocalDate());
	}

	public void nueva(){
		cboFormaPago.setValue(null);
		txtNumIdentidad.setText(null);
		txtNombre.setText(null);
		//tlvDetalle;
		txtTot.setText(null);
		txtIsv.setText(null);
		txtTotPagar.setText(null);
		dtpFecha.setValue(null);
		listaDetalle.clear();
		dtpFecha.setValue((new Date(fecha.getTimeInMillis())).toLocalDate());
	}

	@FXML
	public void mostrarClientes(){
	main.agregarCliente();
	}

	@FXML
	public void cancelarFactura(){
		System.out.println("A presionado Factura");
		if(tipo.equals("SuperVen")){
			cancelar();
		}
		else if(tipo.equals("Vendedor")){
			System.out.println("Inicie secion como superuser");
			try {
				main.mostrarLoginVendedoresFac(2);
				//main.decuento();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al cargar el descuento: "+e);
			}

		}
	}

	@FXML
	public void aplicarDescuento(){

		System.out.println("A presionado descuento");
		if(tipo.equals("SuperVen")){
			main.decuento(false);
		}
		else if(tipo.equals("Vendedor")){
			System.out.println("Inicie secion como superuser");
			try {
				main.mostrarLoginVendedoresFac(1);
				//main.decuento();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al cargar el descuento: "+e);
			}

		}

	}

	@FXML
	public void buscarProducto(){
		//TODO: prueba de lista
		/*listaDetalle.add(new Detalle(0, new Factura(),
							new Medicamento(15,"paracetamol", Date.valueOf("2012-02-13"),
									16.00, null, null, null),
							2, 233.2));*/
		main.busquedaMedicamento(listaDetalle);
	}
	@FXML public void BusquedaCliente(){
		//Validacion identidad
		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{5}");
		Matcher matcher = pattern.matcher(txtNumIdentidad.getText());
		if(matcher.matches()){
			System.out.println("Buscando cliente");
			gestorConexiones.establecerConexion();
			try {
				cliente = Cliente.BusquedaCliente(gestorConexiones.getConexion(), txtNumIdentidad.getText());
				txtNombre.setText(cliente.getNombre());
			} catch (Exception e) {
				// TODO: handle exception
				txtNombre.setText("");
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Factura");
		        alert.setContentText("No se ha encontrado el cliente");
		        alert.showAndWait();
				System.out.println("Error en la busqueda de clientes: "+e);
			}
			gestorConexiones.cerrarConexion();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Factura");
	        alert.setContentText("Ingrese un numero de identidad valido");
	        alert.showAndWait();
		}

	}
	public void cambiosTlv(){
		listaDetalle.addListener(new ListChangeListener<Detalle>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Detalle> c) {
				// TODO Auto-generated method stub
				calcular();
			}

		});
		tlvDetalle.
		getSelectionModel().
		selectedItemProperty().
		addListener(new ChangeListener<Detalle>() {
			@Override
			public void changed(
					ObservableValue<? extends Detalle> observable,
					Detalle oldValue, //valor seleccionado previamente
					Detalle newValue //valor seleccionado actualmente
			) {
				//calcular();
				if (newValue!=null){
					txtEliminar.setDisable(false);
				}
			}

		});
	}
	public void asociarColumnas(){

clmnCodigoMedicamento.setCellValueFactory(new PropertyValueFactory<Detalle,Number>("codigoMedicamento"));
clmnMedicamento.setCellValueFactory(new PropertyValueFactory<Detalle,Medicamento>("medicamento"));
clmnCantidad.setCellValueFactory(new PropertyValueFactory<Detalle,Number>("cantidad"));
clmnPrecio.setCellValueFactory(new PropertyValueFactory<Detalle,Number>("precio"));
clmnTotal.setCellValueFactory(new PropertyValueFactory<Detalle,Number>("total"));

	}
	//Cargar tipo factura y cargar las opciones
	public void cargarOpcionesEspeciales(RegistroSupervisorVentas usuario,String tipo){
		this.tipo = tipo;
		try {
			System.out.println("inicia la carga de Super");
			gestorConexiones.establecerConexion();
			this.superVisor = Factura.cargarDatosSuper(usuario.getCodigoSupervisorVentas(),gestorConexiones.getConexion());
			gestorConexiones.cerrarConexion();
			System.out.println("Se ha logueado en facturas el Supervisor: "+superVisor.toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error cargar info Supervisor: "+e);
		}
	}
	public void cargarOpcionesEspeciales(Vendedor usuario,String tipo){
		this.tipo = tipo;
		try {
			System.out.println("inicia la carga de vendedor");
			gestorConexiones.establecerConexion();
			this.vendedor = Factura.cargarDatosEmpleado(usuario.getCodigoVendedor(),gestorConexiones.getConexion());
			gestorConexiones.cerrarConexion();
			System.out.println("Se ha logueado en facturas el vendedor: "+vendedor.toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error cargar info vendedor: "+e);
		}
	}
	public void calcular(){
		subtot = 0;
		for(int i=0; i<listaDetalle.size(); i++){
			subtot += listaDetalle.get(i).getTotal();
		}
		subdes = subtot*(descuento/100);
		subtot -= subdes;
		isv = subtot * 0.15;
		total = subtot + isv;
		txtTot.setText(String.valueOf(subtot));
		txtIsv.setText(String.valueOf(isv));
		txtTotPagar.setText(String.valueOf(total));
		txtdescuento.setText(String.valueOf(subdes));

	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
		calcular();
		System.out.println("Se ha ingrersado un descuento de: "+descuento);
	}
	public void cancelar(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion");
		alert.setHeaderText("Desea Cancelar Factura");
		alert.setContentText("Esta seguro de cancelar?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    nueva();
		} else {
			System.out.println("Se cancelo la operacion");
		}
	}
	/*public void validar(){

	}*/
	@FXML
	public void Eliminar(){
			listaDetalle.remove(tlvDetalle.getSelectionModel().getSelectedIndex());

	}
}
