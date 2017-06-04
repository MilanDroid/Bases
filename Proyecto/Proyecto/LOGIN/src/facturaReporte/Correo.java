package facturaReporte;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {
	private String userMail = "facturacion.medunah@gmail.com";
	private String pass = "medunah.1";
	  private final Properties properties = new Properties();
	    private Session session;

	    private void init() {
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.port", 587);
	        properties.put("mail.smtp.mail.sender", userMail);
	        properties.put("mail.smtp.password", pass);
	        properties.put("mail.smtp.user", userMail);
	        properties.put("mail.smtp.auth", "true");
	        session = Session.getDefaultInstance(properties);
	    }

	    public void send(String destino,String asunto, String mensaje) {
	        init();
	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            message.setSubject(asunto);
	            //message.setText(mensaje);
	            message.setContent(mensaje,"application/pdf");
	            Transport t = session.getTransport("smtp");
	            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	        } catch (MessagingException e) {
	            return;
	        }
	    }
	    //adjuntos
	    public void send(String destino,String asunto, String mensaje,String dir) {
	        init();
	        try {
	        	System.out.println("Enviando mail");

	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            message.setSubject(asunto);
	            BodyPart texto = new MimeBodyPart();
	            texto.setText(mensaje);
	            BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(new DataHandler(new FileDataSource(dir)));
	           //adjunto.setFileName(dir);
	            //adjunto.setDataHandler(new DataHandler(new FileDataSource("‪C:/Users/UNKNOW/Pictures/02D.jpg")));
	            adjunto.setFileName(dir.substring(8).toUpperCase());
	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);
	            message.setContent(multiParte);
	            //message.setContent(mensaje,"application/pdf");
	            Transport t = session.getTransport("smtp");
	            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	            System.out.println("Se ha enviado el mail");
	        } catch (MessagingException e) {
	        	System.out.println(e.getCause());
	            return;
	        }
	    }

}
