package application.view;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.AlerteDao;
import com.G5.dao.EtudiantDao;
import com.G5.dao.PermissionDao;
import com.G5.model.Alerte;
import com.G5.model.Etudiant;
import com.G5.model.Permission;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;

import application.AlerteBox;
import application.PermissionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * @author Ezekiel
 *
 */
public class controllerHistoriqueSorties implements Initializable {

    @FXML
    private TableColumn<PermissionTest, Date> idHR;

    @FXML
    private TableColumn<PermissionTest, Date> idHS;

    @FXML
    private TableColumn<PermissionTest, String> idLieu;

    @FXML
    private TableColumn<PermissionTest, String> idNom;

    @FXML
    private TableView<PermissionTest> idTab;

    @FXML
    private ComboBox<String> nomPre;

    @FXML
    private JFXButton valider;
    
    @FXML
    private JFXButton impr;
    
    AlerteDao alerteDao = new AlerteDao();
    Etudiant etudiant = new Etudiant();
    EtudiantDao etudiantDao = new EtudiantDao();
    List<PermissionTest> per = new ArrayList<PermissionTest>();
    PermissionDao permissionDao = new PermissionDao();
    String nomPrenom = "";
    
    @FXML
    void getIDetu(ActionEvent event) {
    	nomPrenom = nomPre.getValue();
    	//String t = nomPrenom.toString();
    	List<String> listNP = Arrays.asList(nomPrenom.split(" "));
    	//System.out.println(listNP);
    	String NomE = listNP.get(0);
    	String PrenomE = "";
    	for(int i=1;i<listNP.size();i++) {
    		if (PrenomE == "") {
    			PrenomE = listNP.get(i);
    		}else {
    		PrenomE = PrenomE + " " + listNP.get(i);
    		}
    	}
    	etudiant = etudiantDao.getEtudiant(NomE, PrenomE);
    }
    
    @FXML
    void ImpressionPdf(ActionEvent event) throws DocumentException, IOException, IllegalArgumentException, RuntimeException {
    	
    	if(nomPrenom.equals("") || per.isEmpty()) {
    		Alert alerte = new Alert(AlertType.ERROR);
    		alerte.setHeaderText(null);
    		alerte.setContentText("Choississez un étudiant");
    		alerte.showAndWait();
    	}else {
    		Document docu = new Document();
        	PdfWriter.getInstance(docu, new FileOutputStream(etudiant.getNom()+etudiant.getPrenoms()+".pdf"));
        	docu.open();
        	docu.add(new Paragraph("Historique de sorti de l'étudiant "+ etudiant.getNom()+" "+etudiant.getPrenoms()));
        	docu.add(new Paragraph("\n\n"));
        	for(PermissionTest pt: per) {
        		docu.add(new Paragraph(
            			"\nDate de sorti: "+ pt.getDateSorti()
            			+"\nMotif de sorti: "+ pt.getMotifSorti()
            			+"\nDate de retour: "+ pt.getHeureArrive()
            			));
        		docu.add(new Paragraph("======================================="));
        	}
        	
        	docu.close();
        	Desktop.getDesktop().open(new File(etudiant.getNom()+etudiant.getPrenoms()+".pdf"));
        	String sujet = "Historique de sorti de votre enfant :"+etudiant.getNom()+" "+etudiant.getPrenoms();
        	File file = new File(etudiant.getNom()+etudiant.getPrenoms()+".pdf");
        	String chemin = file.getAbsolutePath();
        	try {
        		AlerteBox.envoieMail(etudiant.getEmailParnt(), chemin, sujet);
            	//@SuppressWarnings("unused")
    			Alerte alerte = new Alerte();
            	alerte.setEtudiant(etudiant);
            	alerteDao.saveAlerte(alerte);
        	}catch(Exception e) {
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setHeaderText(null);
    			alert.setContentText("Echec, Envoyez le document au :"+ etudiant.getEmailParnt());
    			alert.showAndWait();
        	}
        	
        	for(Permission p: permissionDao.getAllHistoPer()){
        		permissionDao.deletePermission(p.getIdPermission());
        	}
    	}
    	
    }

    @FXML
    void validerTableHistory(ActionEvent event){
    	
    	if(nomPrenom.equals("")) {
    		Alert alerte = new Alert(AlertType.ERROR);
    		alerte.setHeaderText(null);
    		alerte.setContentText("Choississez un étudiant");
    		alerte.showAndWait();
    	}else {
    		try {
        		idNom.setCellValueFactory(new PropertyValueFactory<>("etudiant"));
            	idLieu.setCellValueFactory(new PropertyValueFactory<>("motifSorti"));
            	idHS.setCellValueFactory(new PropertyValueFactory<>("dateSorti"));
            	idHR.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
            	per.clear();
            	
            	for(Permission p: permissionDao.getAllHistoPer()) {
            		if(p.getEtudiant().getIdEtu() == etudiant.getIdEtu()) {
            			PermissionTest perT = new PermissionTest();
            			perT.setIdPermission(p.getIdPermission());
            			perT.setEtudiant(p.getEtudiant().getNom()+" "+p.getEtudiant().getPrenoms());
            			perT.setDateSorti(p.getDateSorti());
            			perT.setMotifSorti(p.getMotifSorti());
            			perT.setHeureArrive(p.getHeureArrive());
            			per.add(perT);
            		}
            	}
            	idTab.getItems().setAll(per);
            	throw new IllegalArgumentException("erreur");
        	}catch(ClassCastException e) {
        		
        	}catch(IllegalArgumentException f) {
        		
        	}catch(Exception e) {
        		
        	}
    	}
    	
    	
    	
    	
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		EtudiantDao etudiantDao = new EtudiantDao();
		for(Etudiant e: etudiantDao.getAllEtudiants()) {
			nomPre.getItems().addAll(e.getNom()+" "+e.getPrenoms());
		}
	}

}
