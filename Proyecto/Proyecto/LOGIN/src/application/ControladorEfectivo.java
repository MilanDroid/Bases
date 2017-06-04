package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import facturaReporte.Correo;
import facturaReporte.Imprimir;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import modelo.Detalle;
import modelo.Factura;
import utilidades.GestorConexiones;

public class ControladorEfectivo implements Initializable {
	private GestorConexiones gestorConexiones;
    @FXML
    private TextField txtTotPager;

    @FXML
    private TextField txtCantReci;

    @FXML
    private TextField txtCambio;
	private Main main;
	private Double totPagar;
	private Factura factura;
	private ObservableList<Detalle> detalle;
	private Correo mail = new Correo();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		gestorConexiones = new GestorConexiones();

	}

	@FXML
	public void aceptar() throws FileNotFoundException, DocumentException{
		if(Double.valueOf(txtCantReci.getText())>=totPagar){
				gestorConexiones.establecerConexion();
				int resultado =factura.guardar(gestorConexiones.getConexion());
				if (resultado==1){
					for(int i=0; i<detalle.size(); i++){
						detalle.get(i).setFactura(factura);
						detalle.get(i).guardar(gestorConexiones.getConexion());
						detalle.get(i).reducirExistencia(gestorConexiones.getConexion());
					}
					Imprimir.imprimirFactura(detalle,factura);
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Enviar factura");
					alert.setHeaderText("Enviar factura");
					alert.setContentText("¿Desea enviar su factura a:\n"+factura.getCliente().getCorreoElectronico()+"?");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						Imprimir.imprimirFactura(detalle,factura);
						mail.send(factura.getCliente().getCorreoElectronico(),"Factura","Gracias por su compra","factura/factura-"+factura.getCodigoFactura()+".pdf");
					}
				}
				gestorConexiones.cerrarConexion();
				main.cerrarPagoEfec();
			}
		else if (Double.valueOf(txtCantReci.getText())<totPagar){
			System.out.println("La cantidad recibida es insuficiente");
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Cobro");
			alert2.setHeaderText("Cobro");
			alert2.setContentText("La cantidad recibida es insuficiente");
			alert2.showAndWait();
		}
		}
	@FXML
	public void cancelar(){
		main.xancelPagoEfec();
	}

	public Main getMain() {
		return main;
	}


	public void setMain(Main main) {
		this.main = main;

	}

	public Double getTotPagar() {
		return totPagar;
	}

	public void setTotPagar(Double totPagar) {
		this.totPagar = totPagar;
		txtTotPager.setText(totPagar.toString());

	}
	@FXML
	public void cambio(){
		try {
			if(Double.valueOf(txtCantReci.getText())>=totPagar){
				txtCambio.setText(String.valueOf(Double.valueOf(txtCantReci.getText())-totPagar));
			}
			else if (Double.valueOf(txtCantReci.getText())<totPagar){
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Cobro");
				alert2.setHeaderText("Cobro");
				alert2.setContentText("La cantidad recibida es insuficiente");
				alert2.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

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
