package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import facturaReporte.Correo;
import facturaReporte.Imprimir;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Detalle;
import modelo.Factura;
import modelo.pagoTarjeta;
import utilidades.GestorConexiones;
public class ControladorTarjeta implements Initializable{
	private GestorConexiones gestorConexiones;
	private Factura factura;
	private ObservableList<Detalle> detalle;
	private Double totPagar;
	  @FXML private TextField txtNumTarjeta;
	  @FXML private TextField txtTotPagar;
	public Main main;
	private ObservableList<String> emisor;
	@FXML private ComboBox<String> cboEmisor;
	private boolean finalizo = false;
	private Socket cliente;

	private ObjectOutputStream salida;
	private DataInputStream entrada;
	private Correo mail = new Correo();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gestorConexiones = new GestorConexiones();
		Task<String> tarea = new Task<String>(){

			@Override
			protected String call() throws Exception {
				try{
					System.out.println("Intentando conectar");
					cliente = new Socket("localhost",60666);
					System.out.println("Se conecto satisfactoriamente");
					salida = new  ObjectOutputStream(cliente.getOutputStream());
					entrada = new DataInputStream(cliente.getInputStream());
					while(!finalizo){
						String mensaje = entrada.readUTF();

						Platform.runLater(new Runnable(){
							@Override
							public void run() {
									//txtResultado.appendText(mensaje+"\n");
								Alert alert = new Alert(AlertType.INFORMATION);
						        alert.setTitle("Notificacion");
						        alert.setContentText(mensaje);
						        alert.showAndWait();
						        if(mensaje.equals("Se ha realizado el pago correctamente")){
						        	gestorConexiones.establecerConexion();
									int resultado =factura.guardar(gestorConexiones.getConexion());
									if (resultado==1){
										for(int i=0; i<detalle.size(); i++){
											detalle.get(i).setFactura(factura);
											detalle.get(i).guardar(gestorConexiones.getConexion());
										}
										try {
											Imprimir.imprimirFactura(detalle,factura);
											Alert alert2 = new Alert(AlertType.CONFIRMATION);
											alert2.setTitle("Enviar factura");
											alert2.setHeaderText("Enviar factura");
											alert2.setContentText("¿Desea enviar su factura a:\n"+factura.getCliente().getCorreoElectronico()+"?");
											Optional<ButtonType> result = alert2.showAndWait();
											if (result.get() == ButtonType.OK){
												mail.send(factura.getCliente().getCorreoElectronico(),"Factura","Gracias por su compra","factura/factura-"+factura.getCodigoFactura()+".pdf");
											}
										} catch (FileNotFoundException | DocumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									gestorConexiones.cerrarConexion();
						          main.cerrarPagoTar();
						         /* try {
						        	  salida.close();
						        	  entrada.close();
									cliente.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
						         }

							}
						});
					}
					//cliente.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "Finalizo el hilo";
			}

		};
		Thread hilo = new Thread(tarea);
		hilo.setDaemon(true);
		hilo.start();
		llenarComboBox();
	}
	@FXML
	public void aceptar(){
		try {
			/**/
	       // main.cerrarPagoTar();
			salida.writeObject(new pagoTarjeta(
									cboEmisor.getSelectionModel().getSelectedItem(),
									txtNumTarjeta.getText(),
									Double.parseDouble(txtTotPagar.getText())
									));
			System.out.println("[CLIENTE]: " + txtNumTarjeta.getText()+"\n");
			//txtResultado.appendText("[CLIENTE]: " + txtMensaje.getText()+"\n");
			//txtMensaje.setText(null);
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Notificacion");
	        alert.setContentText("El Pago no se ha realizado con exito");
	        alert.showAndWait();
		}
	}

	public Main getMain() {
		return main;
	}


	public void setMain(Main main) {
		this.main = main;

	}
	public void llenarComboBox(){
		emisor = FXCollections.observableArrayList();
		cboEmisor.setItems(emisor);
		emisor.add("Visa");
		emisor.add("MasterCard");
}
	public Double getTotPagar() {
		return totPagar;
	}
	public void setTotPagar(Double totPagar) {
		this.totPagar = totPagar;
		txtTotPagar.setText(totPagar.toString());
	}
	@FXML
	public void cancelar(){
		main.xancelPagoTar();
	}
	public ObservableList<Detalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(ObservableList<Detalle> detalle) {
		this.detalle = detalle;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
