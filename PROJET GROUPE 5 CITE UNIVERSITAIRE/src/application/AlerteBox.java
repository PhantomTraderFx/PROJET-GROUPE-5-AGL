package application;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlerteBox {
	public static void BoxAlerte(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		
		Label label = new Label();
		label.setText(message);
		Button btn = new Button("OK");
		btn.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, btn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
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
	
	public static void envoieMail(String recipient) {
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		//pro.put("mail.smtp.password","Amonf@2002");
		pro.put("mail.smtp.host", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");
		
		String myAccountEmail = "davideamon110@gmail.com";
		String password = "Amonf@2002";
		
		Session session = Session.getInstance(pro, new Authenticator() {
			
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAuthentification() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
			
		});
		Message message = prepareMessage(session, myAccountEmail, recipient);
		try {
			Transport.send(message);
			System.out.println("bien envoyé");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("bisous");
			message.setText("bisous");
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
