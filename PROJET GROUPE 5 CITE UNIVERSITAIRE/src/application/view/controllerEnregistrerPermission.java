package application.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.dao.PermissionDao;
import com.G5.model.Etudiant;
import com.G5.model.Permission;

import application.PermissionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Ezekiel And Bakus
 *
 */
public class controllerEnregistrerPermission implements Initializable {


    @FXML
    private TextField HeureRetour;

    @FXML
    private TableColumn<PermissionTest, String> IdAction;

    @FXML
    private TextField LieuEtudiant;

    @FXML
    private ComboBox<String> NOMPRE;

    @FXML
    private TableColumn<PermissionTest, String> idLieuEtu;

    @FXML
    private TableView<PermissionTest> idTablePermi;

    @FXML
    private TableColumn<PermissionTest, Date> id_Date;

    @FXML
    private TableColumn<PermissionTest, Date> id_HeureRetour;

    @FXML
    private TableColumn<PermissionTest, String> id_NomPrenomEtu;
    
    @FXML
    private TextField search;
    
    List<PermissionTest> listP = new ArrayList<PermissionTest>();
    PermissionDao permissionDao = new PermissionDao();
    Permission permission = new Permission();
    Etudiant etudiant = new Etudiant();
    EtudiantDao etudiantDao = new EtudiantDao();
    
    PermissionTest permT = new PermissionTest();
    boolean btnmodif=false;
    String nomPrenom = "";
    
    @FXML
    void test(ActionEvent event) {
    	nomPrenom = NOMPRE.getValue();
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
    void MethodPermi(ActionEvent event) {
    }
    
    void remplirTab() {
    	for(Permission p: permissionDao.getAllPer()) {
			PermissionTest perm = new PermissionTest();
			perm.setIdPermission(p.getIdPermission());
			perm.setEtudiant(p.getEtudiant().getNom()+" "+ p.getEtudiant().getPrenoms());
			perm.setDateSorti(p.getDateSorti());
			perm.setMotifSorti(p.getMotifSorti());
			perm.setHeureArrive(p.getHeureArrive());
			listP.add(perm);
			
		}
    }

    @FXML
    void ValiderPermission(MouseEvent event) {
    	Date thisDate = new Date();
		//SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y hh:mm");
		//System.out.println(dateForm.format(thisDate));
    	if(nomPrenom.isEmpty() && LieuEtudiant.getText().isEmpty()) {
    		Alert alerte = new Alert(AlertType.WARNING);
    		alerte.setHeaderText(null);
    		alerte.setContentText("Remplissez les champs");
    		alerte.showAndWait();
    	}
    	else {
    		if(btnmodif) {
        		btnmodif=false;
        		Permission pr = new Permission();
        		pr.setIdPermission(permT.getIdPermission());
        		pr.setDateSorti(permT.getDateSorti());
        		pr.setEtudiant(etudiant);
        		pr.setMotifSorti(LieuEtudiant.getText());
        		if(HeureRetour.getText().toLowerCase().equals("oui")) {
        			pr.setHeureArrive(thisDate);
        		}
        		
        		permissionDao.updatePermission(pr);
        		listP.clear();
            	remplirTab();
            	chargementbtn();
            	Alert alerte = new Alert(AlertType.CONFIRMATION);
        		alerte.setHeaderText(null);
        		alerte.setContentText("Modification réussite");
        		alerte.showAndWait();
        		
        		
        	}else {
        		permission.setEtudiant(etudiant);
            	permission.setDateSorti(thisDate);
            	permission.setMotifSorti(LieuEtudiant.getText());
            	permissionDao.savePermission(permission);
            	
            	PermissionTest permissionEdit = new PermissionTest();
            	//System.out.println(permission.getIdPermission());
            	permissionEdit.setIdPermission(permission.getIdPermission());
            	permissionEdit.setEtudiant(permission.getEtudiant().getNom()+" "+permission.getEtudiant().getPrenoms());
            	permissionEdit.setMotifSorti(permission.getMotifSorti());
            	permissionEdit.setDateSorti(permission.getDateSorti());
            	permissionEdit.setHeureArrive(permission.getHeureArrive());
            	listP.add(permissionEdit);
            	chargementbtn();
            	Alert alerte = new Alert(AlertType.CONFIRMATION);
        		alerte.setHeaderText(null);
        		alerte.setContentText("Permission enregistrée");
        		alerte.showAndWait();
            	
            	//idTablePermi.getItems().setAll(listP);
            	
        	}
    	}
    	
    	
    	
    	
		
    }
    
    public void chargementbtn() {
		Callback<TableColumn<PermissionTest, String>, TableCell<PermissionTest, String>> cellFoctory = (TableColumn<PermissionTest, String> param) -> {
            // make cell containing buttons
            final TableCell<PermissionTest, String> cell = new TableCell<PermissionTest, String>() {
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
                            PermissionTest Permi = new PermissionTest();
                            Permi = idTablePermi.getSelectionModel().getSelectedItem();
                            permissionDao.deletePermission(Permi.getIdPermission());
                            listP.remove(Permi);
                            idTablePermi.getItems().setAll(listP);
                            //System.out.println(idTablePermi.getSelectionModel().getSelectedItem().getIdPermission());
                        
                            //permissionDao.deletePermission(permi.getIdPermission());
                        	
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                        	btnmodif = true;
                        	
                        	permT = idTablePermi.getSelectionModel().getSelectedItem();
                        	NOMPRE.setValue(permT.getEtudiant());
                        	LieuEtudiant.setText(permT.getMotifSorti());
                    		
//                        	
                        	//idTablePermi.getItems().setAll(listP);
                            
                        	
                           

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
        //IdAction.setCellFactory(cellFoctory);
        IdAction.setCellFactory(cellFoctory);
        idTablePermi.getItems().setAll(listP);

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		EtudiantDao etudiantDao = new EtudiantDao();
		for(Etudiant e: etudiantDao.getAllEtudiants()) {
			NOMPRE.getItems().addAll(e.getNom()+" "+e.getPrenoms());
		}
		id_NomPrenomEtu.setCellValueFactory(new PropertyValueFactory<>("etudiant"));
		idLieuEtu.setCellValueFactory(new PropertyValueFactory<>("motifSorti"));
		id_Date.setCellValueFactory(new PropertyValueFactory<>("dateSorti"));
		id_HeureRetour.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
		
		remplirTab();
		//idTablePermi.getItems().setAll(listP);
		chargementbtn();
	}

}
