package application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import modelo.Tarjeta;
import modelo.pagoTarjeta;
import utilidades.GestorConexiones;


public class ControladorBancos implements Initializable{
	private boolean finalizo = false;
	private ServerSocket servidor;
	private GestorConexiones gestorConexiones;
	@FXML private TextArea txtMensaje;
	@FXML private TextArea txtResultado;
	@FXML private ListView<Socket> lstClientes;
	private ObservableList<Socket> socketsClientes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gestorConexiones = new GestorConexiones();

		socketsClientes = FXCollections.observableArrayList();
		lstClientes.setItems(socketsClientes);
		Task<String> tareaEscucharPeticiones = new Task<String>(){
			public String call(){
				try {
					servidor = new ServerSocket(60666);
					while(!finalizo){
						System.out.println("Esperando cliente");
						Socket cliente= servidor.accept();
						System.out.println("Cliente conectado: " + cliente);
						Platform.runLater(new Runnable(){
							public void run(){
								socketsClientes.add(cliente);
							}
						});
						procesarMensajesCliente(cliente);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "Tarea finalizada";
			}
		};
		Thread hilo = new Thread(tareaEscucharPeticiones);
		hilo.setDaemon(true);
		hilo.start();
	}

	public void procesarMensajesCliente(Socket cliente){

		Task<String> tareaLecturaMensajes = new Task<String>(){

			@Override
			protected String call() throws Exception {

				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
				while(!finalizo){
					System.out.println("Esperando a que el cliente escriba algo");
					try {
					pagoTarjeta mensaje = (pagoTarjeta) entrada.readObject();
					System.out.println("se recibio algo");
					gestorConexiones.establecerConexion();
					int aut = Tarjeta.efectuarPago(mensaje,
											gestorConexiones.getConexion()
											);
					System.out.println("aut: "+aut);
					gestorConexiones.cerrarConexion();
					if(aut == 0){
						enviarMensaje("No se ha podido realizar el pago",cliente);
					}
					else if(aut == 1){
						enviarMensaje("Se ha realizado el pago correctamente",cliente);
					}
					Platform.runLater(new Runnable(){
						@Override
						public void run() {

								txtResultado.appendText("Se ha recibido una solicitud de pago de "+ cliente+"\n");
						}

					});
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
				}
				servidor.close();
				cliente.close();
				return "Finalizo la tarea";
			}

		};
		Thread hilo = new Thread(tareaLecturaMensajes);
		hilo.setDaemon(true); //Para que el hilo de la aplicacion
		//finalicen cuando se cierra la aplicacion
		hilo.start();//Inicia el hilo, es decir llama al metodo call()
	}

	@FXML
	public void enviarMensaje(){
		 try {
			Socket clienteSeleccionado =
					lstClientes.getSelectionModel().getSelectedItem();
			DataOutputStream salida = new DataOutputStream(clienteSeleccionado.getOutputStream());
			salida.writeUTF("[SERVIDOR]: " + txtMensaje.getText());
			System.out.println("[SERVIDOR]: " + txtMensaje.getText());
			txtResultado.appendText("[SERVIDOR]: " + txtMensaje.getText()+"\n");
			txtMensaje.setText(null);
			////salida.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void enviarMensaje(String m,Socket c){
		System.out.println("notificando");
		 try {
			Socket clienteSeleccionado = c;
			DataOutputStream salida = new DataOutputStream(clienteSeleccionado.getOutputStream());
			salida.writeUTF(m);
			System.out.println(m);
			txtResultado.appendText("cliente: "+c+" [SERVIDOR]: " + m+"\n");
			txtMensaje.setText(null);
			//salida.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error env(m)"+e);
			//enviarMensaje();
		}
	}
}