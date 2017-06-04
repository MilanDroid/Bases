package facturaReporte;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.ObservableList;
import modelo.Detalle;
import modelo.Factura;
import modelo.Farmacia;
public class Imprimir {
	private static final String iTextExampleImage = "res/MedUNAH-Logo2.jpg";
	private static void addMetaData(Document document) {
	    document.addTitle("Factura");
	    document.addKeywords("Factura, PDF, Registro de compra");
	    document.addAuthor("MedUNAH");
	    document.addCreator("MedUNAH");
	  }
	public static void imprimirFactura(ObservableList<Detalle> lista,Factura factura) throws FileNotFoundException, DocumentException {
			System.out.println("Se esta generanmdo la factura");
		      FileOutputStream archivo = new FileOutputStream("factura/factura-"+factura.getCodigoFactura()+".pdf");
		      Document documento = new Document();
		      PdfWriter.getInstance(documento, archivo);
		      documento.open();
		      addMetaData(documento);
		      Image image;
	            try {
	                image = Image.getInstance(iTextExampleImage);
	                image.setAlignment(1);

	                documento.add(image);
	            } catch (BadElementException ex) {
	                System.out.println("Image BadElementException" +  ex);
	            } catch (IOException ex) {
	                System.out.println("Image IOException " +  ex);
	            }
		      addContent(documento,factura);
		      createTable(documento,lista);
		      documento.add( new Paragraph("SubTotal     "+factura.getTotal()));
		      documento.add( new Paragraph("ISV               "+factura.getIsv()));
		      documento.add( new Paragraph("---------------------------"));
		      documento.add( new Paragraph("Total          "+factura.getTotalPagar()));
		      documento.close();

	}
	private static void addContent(Document document,Factura f) throws DocumentException {
		document.add( new Paragraph("MedUNAH"));
		 document.add( new Paragraph(" "));
		document.add( new Paragraph("Factura Nº: "+f.getCodigoFactura()));
	    document.add( new Paragraph(" "));
	    document.add( new Paragraph("Fecha: "+f.getFecha()));
	    document.add( new Paragraph(" "));
	    document.add( new Paragraph("Atendido por: "+f.getVendedor()));
		document.add( new Paragraph(" "));
	    document.add( new Paragraph("Cliente: "+f.getCliente()));
	    document.add( new Paragraph(" "));


	  }

	 private static void createTable(Document document,ObservableList<Detalle> lista) throws DocumentException {
		    PdfPTable table = new PdfPTable(5);

		    PdfPCell c1 = new PdfPCell(new Phrase("Codigo"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Producto"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Cantidad"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Precio"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("total"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);

		    table.setHeaderRows(1);
		    for(int i=0; i<lista.size(); i++)
		    {
		    table.addCell(lista.get(i).getCodigoMedicamento().toString());
		    table.addCell(lista.get(i).getMedicamento().getNombreMedicamento());
		    table.addCell(lista.get(i).getCantidad().toString());
		    table.addCell(lista.get(i).getPrecio().toString());
		    table.addCell(lista.get(i).getTotal().toString());
		    }
		    document.add(table);

		  }



}
