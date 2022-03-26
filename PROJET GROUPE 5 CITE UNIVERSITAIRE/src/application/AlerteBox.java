package application;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlerteBox {
	public static boolean VerifierNomP(String nom) {
		String NomSansEspace = nom.replaceAll("\\s","");
		char [] tab = NomSansEspace.toCharArray();
		char [] inte = {'0','1','2','3','4','5','6','7','8','9','@','-','_','?',',','&','=','/','*','+','.','<','>',';','!','$','^','€','²','(',')','{',']','[','}'};
		for(int i=0;i<tab.length;i++) {
			for(int j = 0;j<inte.length;j++) {
				if(tab[i] == inte[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean VeriferMail(String mail) {
		if(mail == null || mail.isEmpty()) {
			return false;
		}
		
		String emailRegex = "^[a-zA-Z0-9+&*-]+(?:\\."+
						"[A-zA-Z0-9_+ &*-]+)*@"+
						"(?:[A-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		Pattern pattern = Pattern.compile(emailRegex);
		if(pattern.matcher(mail).matches()) {
			return true;
		}
		return false;
	}
	
	public static boolean VerifierNum(String num) {
		if(num == null || num.isEmpty()) {
			return false;
		}
		String regex = "^[0-9]{10}$";
		Pattern pattern = Pattern.compile(regex);
		
		if(pattern.matcher(num).matches()) {
			return true;
		}
		return false;
	}
	
	public static void envoieMail(String recipient,String chemin,String sujet) throws ConnectException {
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		//pro.put("mail.smtp.password","Amonf@2002");
		pro.put("mail.smtp.host", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");
		
		String myAccountEmail = "bingervilleupb@gmail.com";
		String password = "Amonf@2002";
		
		Session session = Session.getInstance(pro, new Authenticator() {
			
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAuthentification() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
			
		});
		Message message = prepareMessage(session, myAccountEmail, recipient,chemin, sujet);
		try {
			Transport.send(message, myAccountEmail, password);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Email bien envoyé");
			alert.showAndWait();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Veillez vérifier votre connexion\n si cela persite relancer le logiciel");
			alert.showAndWait();
			e.printStackTrace();
		}
		throw new ConnectException("");
	} 
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String chemin,String sujet) {
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(sujet);
			
			Multipart emailcontent = new MimeMultipart();
			
			MimeBodyPart pdfAttachement = new MimeBodyPart();
			pdfAttachement.attachFile(chemin);
			
			emailcontent.addBodyPart(pdfAttachement);
			message.setContent(emailcontent);
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
