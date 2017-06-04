package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Detalle;
import modelo.Factura;
import modelo.Farmacia;
import modelo.RegistroSupervisorVentas;
import modelo.Vendedor;


public class Main extends Application {
	//INVENTARIO
	private Stage formulario1;
	private Stage formularioPrincipal;
	private ControladorPrincipal controladorPrincipal;
	private ControladorFormulario1 controladorFormulario1;
	private ControladorBusquedaMedicamento controladorBusquedaMedicamento;

	// STAGES
	private Stage loginPrincipal;

	//AREA SUPER USUARIO
	private Stage loginSuperUsuario;
	private Stage formularioVendedor;
	private Stage formularioBodeguero;
	//AREA INVENTARIO
	private Stage loginSuperUsuarioAreaInventario;//
	private Stage loginAreaDeBodega;
	private Stage listaBodega;
	private Stage registroSupervisorInventarios;


	private Stage registroSupervisorVentas;
	private ControladorRegistroSupervisorVentas controladorRegistroSupervisorventas;

	//AREA VENTAS
	private Stage loginVendedores;
	private Stage loginVendedoresFac;
	private Stage loginSuperUsuarioAreaVentas;
	private Stage listaVendedores;
	private Stage factura;
	private Stage tarjeta;
	private Stage efectivo;
	private Stage descuento;
	private Stage cancelarFactura;
	private Stage busquedaMedicamento;

	//CONTROLADORES VISTAS
	private ControladorloginPrincipal controladorLoginPrincipal;
	//CONTROLADOR VISTA SUPERUSUARIO
	private ControladorloginSuperUsuario controladorLoginSuperUsuario;

	//CONTROLADOR VISTAS AREA INVENTARIO
	private ControladorloginAreaDeBodega controladorLoginAreaDeBodega;
	private ControladorloginSuperUsuarioAreaInventario controladorLoginSuperUsuarioAreaInventario;
	private ControladorlistaEncargadodeBodega controladorListaBodega;
	private ControladorBodeguero controladorBodeguero;

	//CONTROLADOR VISTAS AREA VENTAS
	private ControladorloginVendedores controladorLoginVendedores;
	private ControladorloginVendedoresFact controladorLoginVendedoresFact;
	private ControladorloginSuperUsuarioAreaVentas controladorLoginSuperUsuarioAreaVentas;
	private ControladorlistaVendedores controladorListaVendedores;
	private ControladorVendedor controladorVendedor;
	private ControladorFactura controladorFactura;
	private ControladorTarjeta controladorTarjeta;
	private ControladorEfectivo controladorEfectivo;
	private ControladorRegistroSupervisorInventarios controladorRegistroSupervisorInventarios;
	private ControladorDescuentos controladorDescuentos;
	//private ControladorCancelarFactura cancelarFactura;

// CLIENTES
	private Stage formularioCliente;
	private Stage formularioFactura;
	private ClientesController clientesController;
	//private int a;

	@Override
	public void start(Stage primaryStage) {
		loginPrincipal = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPrincipal.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.setTitle("LOGIN PRINCIPAL");
			controladorLoginPrincipal = loader.getController();
			controladorLoginPrincipal.setMain(this);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//INGRESAR AL LOGIN DEL SUPERUSUARIO
	public void mostrarLoginSuperUsuario(){
		if(loginSuperUsuario==null){
			loginSuperUsuario = new Stage();
			loginSuperUsuario.initOwner(loginPrincipal);
			loginSuperUsuario.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUsuario.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginSuperUsuario.setScene(scene);
				loginSuperUsuario.setTitle("LOGIN SUPERUSUARIO");
				controladorLoginSuperUsuario = loader.getController();
				controladorLoginSuperUsuario.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginPrincipal.hide();
		loginSuperUsuario.show();
		}

	//INGRESAR AL LOGIN VENDEDOR
	public void mostrarLoginVendedores(){
		if(loginVendedores==null){
			loginVendedores = new Stage();
			loginVendedores.initOwner(loginPrincipal);
			loginVendedores.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginVendedores.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginVendedores.setScene(scene);
				loginVendedores.setTitle("Login Vendedores");
				controladorLoginVendedores = loader.getController();
				controladorLoginVendedores.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginPrincipal.hide();
		loginVendedores.show();
		}

	//INGRESAR AL LOGIN DEL ENCARGADO DE BODEGA
	public void mostrarLoginEncargadoDeBodega(){
		if(loginAreaDeBodega==null){
			loginAreaDeBodega = new Stage();
			loginAreaDeBodega.initOwner(loginPrincipal);
			loginAreaDeBodega.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAreaDeBodega.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginAreaDeBodega.setScene(scene);
				loginAreaDeBodega.setTitle("Encargados de Bodega");
				controladorLoginAreaDeBodega = loader.getController();
				controladorLoginAreaDeBodega.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginPrincipal.hide();
		loginAreaDeBodega.show();
		}



	//INGRESO A LAS OPCIONES DEL SUPERUSUARIO EN EL AREA DE INVENTARIO
	public void mostrarSuperUsuarioAreaInventario(){
		if(loginSuperUsuarioAreaInventario==null){
			loginSuperUsuarioAreaInventario = new Stage();
			loginSuperUsuarioAreaInventario.initOwner(loginSuperUsuario);//
			loginSuperUsuarioAreaInventario.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUsuarioAreaInventario.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginSuperUsuarioAreaInventario.setScene(scene);
				loginSuperUsuarioAreaInventario.setTitle("LOGIN AREA INVENTARIO");
				controladorLoginSuperUsuarioAreaInventario = loader.getController();
				controladorLoginSuperUsuarioAreaInventario.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginSuperUsuario.hide();
		loginSuperUsuarioAreaInventario.show();
		}


	//INGRESO A LAS OPCIONES DEL SUPERUSUARIO EN EL AREA DE VENTAS
	public void mostrarSuperUsuarioAreaVentas(){
		if(loginSuperUsuarioAreaVentas==null){
			loginSuperUsuarioAreaVentas = new Stage();
			loginSuperUsuarioAreaVentas.initOwner(loginSuperUsuario);//
			loginSuperUsuarioAreaVentas.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUsuarioAreaVentas.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginSuperUsuarioAreaVentas.setScene(scene);
				loginSuperUsuarioAreaVentas.setTitle("LOGIN AREA VENTAS");
				controladorLoginSuperUsuarioAreaVentas = loader.getController();
				controladorLoginSuperUsuarioAreaVentas.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginSuperUsuario.hide();
		loginSuperUsuarioAreaVentas.show();
		}


	public void mostrarlistaVendedores(){
		if(listaVendedores==null){
			listaVendedores = new Stage();
			listaVendedores.initOwner(loginSuperUsuario);//
			listaVendedores.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("listaVendedores.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				listaVendedores.setScene(scene);
				listaVendedores.setTitle("LISTA VENDEDORES");
				controladorListaVendedores = loader.getController();
				controladorListaVendedores.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginSuperUsuario.hide();
		listaVendedores.show();
		}

	public void mostrarlistaEcncargadosdeBodega(){
		if(listaBodega==null){
			listaBodega = new Stage();
			listaBodega.initOwner(loginSuperUsuarioAreaInventario);//
			listaBodega.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("listaEncargadodeBodega.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				listaBodega.setScene(scene);
				listaBodega.setTitle("LISTA ");
				controladorListaBodega = loader.getController();
				controladorListaBodega.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginSuperUsuario.hide();
		listaBodega.show();
		}


	//INGRESO AL AREA DE INVENTARIO
	public void irInventario(){
		if(loginSuperUsuarioAreaInventario==null){
			loginSuperUsuarioAreaInventario = new Stage();
			loginSuperUsuarioAreaInventario.initOwner(loginSuperUsuario);//
			loginSuperUsuarioAreaInventario.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUsuarioAreaInventario.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				loginSuperUsuarioAreaInventario.setScene(scene);
				loginSuperUsuarioAreaInventario.setTitle("LOGIN AREA INVENTARIO");
				controladorLoginSuperUsuarioAreaInventario = loader.getController();
				controladorLoginSuperUsuarioAreaInventario.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		loginSuperUsuario.hide();
		loginSuperUsuarioAreaInventario.show();
		}


	//INVENTARIO

		public void irInventario2(){
			if(formularioPrincipal==null){
				formularioPrincipal = new Stage();
				formularioPrincipal.initOwner(loginAreaDeBodega);
				formularioPrincipal.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
					BorderPane root = (BorderPane)loader.load();
					Scene scene = new Scene(root);
					formularioPrincipal.setScene(scene);
					formularioPrincipal.setTitle("INVENTARIO");
					controladorPrincipal = loader.getController();
					controladorPrincipal.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			loginAreaDeBodega.close();
			formularioPrincipal.show();
		}

		public void agregarMedicamento(){
			if(formulario1==null){
				formulario1 = new Stage();
				formulario1.initOwner(formularioPrincipal);
				formulario1.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaFormulario1.fxml"));
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root);
					formulario1.setScene(scene);
					formulario1.setTitle("INVENTARIO");
					controladorFormulario1 = loader.getController();
					controladorFormulario1.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			//formularioPrincipal.close();
			formulario1.show();
		}
		public void mostrarBodeguero(){
			if(formularioBodeguero==null){
				formularioBodeguero = new Stage();
				formularioBodeguero.initOwner(loginSuperUsuarioAreaInventario);
				formularioBodeguero.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaBodeguero.fxml"));
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root);
					formularioBodeguero.setScene(scene);
					formularioBodeguero.setTitle("Encargados de Bodega");
					controladorBodeguero = loader.getController();
					controladorBodeguero.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			loginSuperUsuarioAreaInventario.hide();
			formularioBodeguero.show();
		}
		public void mostrarVendedores(){
			if(formularioVendedor==null){
				formularioVendedor = new Stage();
				formularioVendedor.initOwner(loginSuperUsuarioAreaVentas);
				formularioVendedor.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaVendedor.fxml"));
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root);
					formularioVendedor.setScene(scene);
					formularioVendedor.setTitle("Vendedores");
					controladorVendedor = loader.getController();
					controladorVendedor.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			loginSuperUsuarioAreaVentas.hide();
			formularioVendedor.show();
		}



		public void mostrarRegistroSupervisoresVentas(){
			if(registroSupervisorVentas==null){
				registroSupervisorVentas = new Stage();
				registroSupervisorVentas.initOwner(loginSuperUsuarioAreaVentas);
				registroSupervisorVentas.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaRegistroSupervisorVentas.fxml"));
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root);
					registroSupervisorVentas.setScene(scene);
					registroSupervisorVentas.setTitle("Registro (Supervisores De Ventas)");
					controladorRegistroSupervisorventas = loader.getController();
					controladorRegistroSupervisorventas.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			loginSuperUsuarioAreaVentas.hide();
			registroSupervisorVentas.show();
		}

 //MOSTRARCLIENTES
		public void agregarCliente(){
			if(formularioCliente==null){
				formularioCliente = new Stage();
				formularioCliente.initOwner(loginSuperUsuarioAreaVentas);
				formularioCliente.initModality(Modality.WINDOW_MODAL);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaClientes.fxml"));
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root);
					formularioCliente.setScene(scene);
					formularioCliente.setTitle("Clientes");
					clientesController = loader.getController();
					clientesController.setMain(this);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			formularioCliente.show();
		}

	//CONTROLES BOTON SALIR LOGIN PRINCIPAL
	public void volverMenu1(){
	    loginSuperUsuario.close();
		loginPrincipal.show();
	}

	public void volverMenu2(){
		loginVendedores.close();
		loginPrincipal.show();
	}
	public void cancelarDescuento(){
		loginVendedoresFac.close();

	}
	public void volverMenu3(){
	    loginAreaDeBodega.close();
		loginPrincipal.show();
	}

	public void volverMenu4(){
	    loginSuperUsuarioAreaVentas.close();
		loginSuperUsuario.show();
	}

	public void volverMenu5(){
	    listaVendedores.close();
		loginSuperUsuarioAreaVentas.show();
	}

	public void volverMenuAreaInventario(){
	    listaBodega.close();
		loginSuperUsuarioAreaInventario.show();
	}

	public void volverMenu6(){
	formularioPrincipal.close();
	loginAreaDeBodega.show();
	}

	public void volverMenu7(){
		loginSuperUsuarioAreaInventario.close();
		loginSuperUsuario.show();
		}

	public void volverMenu8(){
		listaBodega.close();
		loginSuperUsuarioAreaInventario.show();
		}
	public void salirRegistroVentas(){
		formularioVendedor.close();
		loginSuperUsuarioAreaVentas.show();
	}
	
	
	public void VolverMenuSuperUsuario2(){
		loginSuperUsuarioAreaInventario.close();
		loginSuperUsuario.show();
	}
	public void salirRegistroBodeguero(){
		formularioBodeguero.close();
		loginSuperUsuarioAreaInventario.show();
	}

	public void salirRegistroSupervisorVentas(){
		registroSupervisorVentas.close();
		loginSuperUsuarioAreaVentas.show();
	}
	//Ventas
		//Abre la factura
	// Registro Supervisor inventarios
	public void mostrarRegistroSupervisorInventarios(){
		if(registroSupervisorInventarios==null){
			registroSupervisorInventarios = new Stage();
			registroSupervisorInventarios.initOwner(loginPrincipal);
			registroSupervisorInventarios.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaRegistroSupervisorInventarios.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				registroSupervisorInventarios.setScene(scene);
				registroSupervisorInventarios.setTitle("Registro (Nuevo Supervisor de Inventarios)");
				controladorRegistroSupervisorInventarios = loader.getController();
				controladorRegistroSupervisorInventarios.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		registroSupervisorInventarios.show();
		}


	//acciones de la factura
	//comienza la factura
	public void busquedaMedicamento(ObservableList<Detalle> listaDetalle){
		if(busquedaMedicamento==null){
			busquedaMedicamento = new Stage();
			busquedaMedicamento.initOwner(loginAreaDeBodega);
			busquedaMedicamento.initModality(Modality.WINDOW_MODAL);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("busquedaMedicamento.fxml"));
				BorderPane root = (BorderPane)loader.load();
				Scene scene = new Scene(root);
				busquedaMedicamento.setScene(scene);
				busquedaMedicamento.setTitle("busquedaMedicamento");
				controladorBusquedaMedicamento = loader.getController();
				controladorBusquedaMedicamento.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		busquedaMedicamento.show();
		controladorBusquedaMedicamento.setListaDetalle(listaDetalle);
		controladorBusquedaMedicamento.restaurarMedicamentos();
	}
//mostrar factura para vendedor
	public void mostrarFactura(Vendedor usuario,String tipo){
				if(factura==null){
					factura = new Stage();
					factura.initOwner(loginVendedores);
					factura.initModality(Modality.WINDOW_MODAL);
					//codigo para cuando una presiona cerrar regrese al login
					factura.setOnCloseRequest(new EventHandler<WindowEvent>() {
					    @Override
					    public void handle(final WindowEvent arg0) {
					    	controladorFactura.nueva();
					    	mostrarLoginVendedores();
					    	controladorLoginVendedores.limpiar();
					    }
					});

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Factura.fxml"));
						AnchorPane root = (AnchorPane)loader.load();
						Scene scene = new Scene(root);
						factura.setScene(scene);
						factura.setTitle("MedUNAH Factura");
						controladorFactura = loader.getController();
						controladorFactura.setMain(this);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				//System.out.println("Se ha logueado como: "+usuario.toString()+" tipo: "+tipo);
				loginVendedores.close();
				factura.show();
				controladorFactura.cargarOpcionesEspeciales(usuario, tipo);
			}
	//mostrar factura para RegistroSupervisorVentas
		public void mostrarFactura(RegistroSupervisorVentas usuario,String tipo){
					if(factura==null){
						factura = new Stage();
						factura.initOwner(loginVendedores);
						factura.initModality(Modality.WINDOW_MODAL);
						//codigo para cuando una presiona cerrar regrese al login
						factura.setOnCloseRequest(new EventHandler<WindowEvent>() {
						    @Override
						    public void handle(final WindowEvent arg0) {
						    	controladorFactura.nueva();
						    	mostrarLoginVendedores();
						    	controladorLoginVendedores.limpiar();
						    }
						});
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("Factura.fxml"));
							AnchorPane root = (AnchorPane)loader.load();
							Scene scene = new Scene(root);
							factura.setScene(scene);
							factura.setTitle("MedUNAH Factura");
							controladorFactura = loader.getController();
							controladorFactura.setMain(this);
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					loginVendedores.close();
					//System.out.println("Se ha logueado como: "+usuario.toString()+" tipo: "+tipo);
					factura.show();
					controladorFactura.cargarOpcionesEspeciales(usuario, tipo);
				}

			//Abre Pago con tarjeta

			public void mostrarTarjeta(double totalPagar,Factura f,ObservableList<Detalle> lista){
				if(tarjeta==null){
					tarjeta = new Stage();
					tarjeta.initOwner(factura);
					tarjeta.initModality(Modality.WINDOW_MODAL);

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Tarjeta.fxml"));
						AnchorPane root = (AnchorPane)loader.load();
						Scene scene = new Scene(root);
						tarjeta.setScene(scene);
						tarjeta.setTitle("Pago con Tarjeta");
						controladorTarjeta = loader.getController();
						controladorTarjeta.setMain(this);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}

				tarjeta.show();
				controladorTarjeta.setTotPagar(totalPagar);
				controladorTarjeta.setDetalle(lista);
				controladorTarjeta.setFactura(f);
				}

			//abrir pago en efectivo

			public void mostrarEfectivo(double totalPagar,Factura f,ObservableList<Detalle> lista){
				if(efectivo==null){
					efectivo = new Stage();
					efectivo.initOwner(factura);
					efectivo.initModality(Modality.WINDOW_MODAL);

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Efectivo.fxml"));
						AnchorPane root = (AnchorPane)loader.load();
						Scene scene = new Scene(root);
						efectivo.setScene(scene);
						efectivo.setTitle("Pago en Efectivo");
						controladorEfectivo = loader.getController();
						controladorEfectivo.setMain(this);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				efectivo.show();
				controladorEfectivo.setTotPagar(totalPagar);
				controladorEfectivo.setDetalle(lista);
				controladorEfectivo.setFactura(f);
				}

			public void decuento(boolean ven){
				if(descuento==null){
					descuento = new Stage();
					descuento.initOwner(factura);
					descuento.initModality(Modality.WINDOW_MODAL);

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Descuentos.fxml"));
						AnchorPane root = (AnchorPane)loader.load();
						Scene scene = new Scene(root);
						descuento.setScene(scene);
						descuento.setTitle("Descuento");
						controladorDescuentos = loader.getController();
						controladorDescuentos.setMain(this);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				descuento.show();
				//modificar
				if(ven){
				loginVendedoresFac.close();
				}
			}
			
			public void salirRegistroSupervisorBodega(){
				registroSupervisorInventarios.close();
				loginSuperUsuarioAreaInventario.show();
			}

			public void cerrarPagoEfec() {
				// TODO Auto-generated method stub
				efectivo.close();
				controladorFactura.nueva();
			}
			public void xancelPagoEfec() {
				// TODO Auto-generated method stub
				efectivo.close();
			}
			public void cerrarPagoTar() {
				// TODO Auto-generated method stub
				tarjeta.close();
				controladorFactura.nueva();
			}
			public void xancelPagoTar() {
				// TODO Auto-generated method stub
				tarjeta.close();

			}
			public void agregarDescuento(int descuento){
				controladorFactura.setDescuento(descuento);
				this.descuento.close();
			}
			public void mostrarLoginVendedoresFac(int opc){
				if(loginVendedoresFac==null){
					loginVendedoresFac = new Stage();
					loginVendedoresFac.initOwner(factura);
					loginVendedoresFac.initModality(Modality.WINDOW_MODAL);

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("loginVendedoresFact.fxml"));
						AnchorPane root = (AnchorPane)loader.load();
						Scene scene = new Scene(root);
						loginVendedoresFac.setScene(scene);
						loginVendedoresFac.setTitle("Login Supervisor de Ventas");
						controladorLoginVendedoresFact = loader.getController();
						controladorLoginVendedoresFact.setMain(this);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				loginVendedoresFac.show();
				controladorLoginVendedoresFact.limpiar();
				controladorLoginVendedoresFact.setOpc(opc);
				}

			public void cancelarFactura(){
				controladorFactura.cancelar();
				loginVendedoresFac.close();
			}

	public static void main(String[] args) {
		launch(args);
	}
}
