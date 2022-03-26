package application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;

import application.AlerteBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;



/**
 * @author Ezekiel
 *
 */
public class controllerEnregistrerEtudiant implements Initializable {

    @FXML
    private TextField ContactParent;

    @FXML
    private TextField Contact_Etudiant;

    @FXML
    private TextField EmailParent;

    @FXML
    private TextField Nom_Etudiant;

    @FXML
    private TextField Nom_Parent;

    @FXML
    private TextField Prenom_Etudiant;

    @FXML
    private TableView<Etudiant> Tabetu;

    @FXML
    private TableColumn<Etudiant, String> idContactEtuTab;
    
    @FXML
    private TableColumn <Etudiant, String> idAction;

    @FXML
    private TableColumn<Etudiant, String> idNomEtuTab;

    @FXML
    private TableColumn<Etudiant, String> idPrenomEtuTab;
    
//    @FXML
//    private TextField searchBox;
    
    //ObservableList<Etudiant> l = FXCollections.observableArrayList();
    List<Etudiant> Etudiantlist = FXCollections.observableArrayList();
    Etudiant etudiantEdit = new Etudiant();
    Etudiant e = new Etudiant();
    boolean btnedit = false;
    @FXML
    void RemoveToChamp(MouseEvent event) {

		Nom_Etudiant.setText("");
    	Prenom_Etudiant.setText("");
    	Contact_Etudiant.setText("");
    	ContactParent.setText("");
    	Nom_Parent.setText("");
    	EmailParent.setText("");
    }
    EtudiantDao etudiantDao = new EtudiantDao();
    

    @FXML
    void SendEtudiantToBd(MouseEvent event) {
    	if(Nom_Etudiant.getText().isEmpty() || Prenom_Etudiant.getText().isEmpty() || Contact_Etudiant.getText().isEmpty() || ContactParent.getText().isEmpty() || Nom_Parent.getText().isEmpty() || ContactParent.getText().isEmpty() || EmailParent.getText().isEmpty()) {
    		Alert alerte = new Alert(AlertType.WARNING);
    		alerte.setHeaderText(null);
    		alerte.setContentText("Rentrez toutes vos informations");
    		alerte.showAndWait();
    	}else {
    		if(btnedit) {
        		btnedit = false;
        		        	
        		if(AlerteBox.VerifierNomP(Nom_Etudiant.getText()) && AlerteBox.VerifierNomP(Prenom_Etudiant.getText()) && AlerteBox.VerifierNomP(Nom_Parent.getText()) && AlerteBox.VeriferMail(EmailParent.getText()) && AlerteBox.VerifierNum(Contact_Etudiant.getText()) && AlerteBox.VerifierNum(ContactParent.getText())) {
        			Etudiant etu = new Etudiant();
            		etu.setIdEtu(e.getIdEtu());
            		etu.setNom(Nom_Etudiant.getText().toLowerCase());
            		etu.setPrenoms(Prenom_Etudiant.getText().toLowerCase());
            		etu.setNumeroEtu(Contact_Etudiant.getText());
            		etu.setNumeroParnt(ContactParent.getText());
            		etu.setNomParnt(Nom_Parent.getText().toLowerCase());
            		etu.setEmailParnt(EmailParent.getText().toLowerCase());
        			etudiantDao.updateEtudiant(etu);
        			Etudiantlist = etudiantDao.getAllEtudiants();
        			Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
        			Alert alert = new Alert(AlertType.CONFIRMATION);
        			alert.setHeaderText(null);
        			alert.setContentText("Modification réussite");
        			alert.showAndWait();
        		}else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setHeaderText(null);
        			alert.setContentText("Vérifiez vos données");
        			alert.showAndWait();
        		}

        	}
        	else {
        		boolean exiter = false;
        		Etudiant etudiant = new Etudiant();  
        		for(Etudiant e: etudiantDao.getAllEtudiants()) {
        			if(e.getNom().equals(Nom_Etudiant.getText()) && e.getPrenoms().equals(Prenom_Etudiant.getText())) {
            			exiter = true;
        				break;
            		}
        		}
        		
        		if(exiter) {
        			Alert alert = new Alert(AlertType.ERROR);
            		alert.setHeaderText(null);
            		alert.setContentText("l'étudiant existe déjà");
            		alert.showAndWait();
        		}else {
        			if(AlerteBox.VerifierNomP(Nom_Etudiant.getText()) && AlerteBox.VerifierNomP(Prenom_Etudiant.getText()) && AlerteBox.VerifierNomP(Nom_Parent.getText()) && AlerteBox.VerifierNum(Contact_Etudiant.getText()) && AlerteBox.VerifierNum(ContactParent.getText()) && AlerteBox.VeriferMail(EmailParent.getText())) {
                		etudiant.setNom(Nom_Etudiant.getText().toLowerCase());
            			etudiant.setPrenoms(Prenom_Etudiant.getText().toLowerCase());
            			etudiant.setNomParnt(Nom_Parent.getText().toLowerCase());
            			etudiant.setNumeroEtu(Contact_Etudiant.getText());
                    	etudiant.setNumeroParnt(ContactParent.getText());
                    	etudiant.setEmailParnt(EmailParent.getText().toLowerCase());
                		etudiantDao.saveEtudiant(etudiant);
                		Etudiantlist.add(etudiant);
                		Tabetu.getItems().setAll(Etudiantlist);
                		Alert alerte = new Alert(AlertType.CONFIRMATION);
                		alerte.setHeaderText(null);
                		alerte.setContentText("Etudiant Enregistré");
                		alerte.showAndWait();
                	}else {
                		Alert alert = new Alert(AlertType.ERROR);
                		alert.setHeaderText(null);
                		alert.setContentText("Vérifier vos données");
                		alert.showAndWait();
                	}
        		}
        		
            	
            	
        	}
        	
        	
    		Nom_Etudiant.setText("");
        	Prenom_Etudiant.setText("");
        	Contact_Etudiant.setText("");
        	ContactParent.setText("");
        	Nom_Parent.setText("");
        	EmailParent.setText("");
        	chargementbtn();
    	}
    	
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Etudiantlist = etudiantDao.getAllEtudiants();
		idContactEtuTab.setCellValueFactory(new PropertyValueFactory<>("numeroEtu"));
    	idNomEtuTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	idPrenomEtuTab.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
    	chargementbtn();
    	}
	
	
	public void chargementbtn() {
		Callback<TableColumn<Etudiant, String>, TableCell<Etudiant, String>> cellFoctory = (TableColumn<Etudiant, String> param) -> {
            // make cell containing buttons
            final TableCell<Etudiant, String> cell = new TableCell<Etudiant, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Text deleteIcon = new Text("supprimer");
                        Text editIcon = new Text("modifier");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Etudiant etudiant = Tabetu.getSelectionModel().getSelectedItem();
                            etudiantDao.deleteEtudiant(etudiant.getIdEtu());
                            Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
                            
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                        	btnedit = true;
//                            etudiantEdit = Tabetu.getSelectionModel().getSelectedItem();
//                            etudiantEdit.setNom(Nom_Etudiant.getText());
//                            etudiantEdit.setPrenoms(Prenom_Etudiant.getText());
//                            etudiantEdit.setNumeroEtu(Integer.parseInt(Contact_Etudiant.getText()));
//                            etudiantEdit.setNumeroParnt(Integer.parseInt(ContactParent.getText()));
//                            etudiantEdit.setNomParnt(Nom_Parent.getText());
//                            etudiantEdit.setEmailParnt(EmailParent.getText());
//                            etudiantDao.updateEtudiant(etudiantEdit);
//                            Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
                            e = Tabetu.getSelectionModel().getSelectedItem();
                            Nom_Etudiant.setText(e.getNom());
                            Prenom_Etudiant.setText(e.getPrenoms());
                            Contact_Etudiant.setText(String.valueOf(e.getNumeroEtu()));
                            Nom_Parent.setText(e.getNomParnt());
                            ContactParent.setText(String.valueOf(e.getNumeroParnt()));
                            EmailParent.setText(String.valueOf(e.getEmailParnt()));
                            
                        	
                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         idAction.setCellFactory(cellFoctory);
         Tabetu.getItems().setAll(Etudiantlist);

	}
	
	
}
