package modelo;

import java.io.Serializable;

public class pagoTarjeta implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String emisor;
	private String numTarjeta;
	private Double totPagar;
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public Double getTotPagar() {
		return totPagar;
	}
	public void setTotPagar(Double totPagar) {
		this.totPagar = totPagar;
	}
	public pagoTarjeta(String emisor, String numTarjeta, Double totPagar) {
		super();
		this.emisor = emisor;
		this.numTarjeta = numTarjeta;
		this.totPagar = totPagar;
	}


}
