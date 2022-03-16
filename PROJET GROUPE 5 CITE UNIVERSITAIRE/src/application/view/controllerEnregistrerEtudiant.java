package application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

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
    
    List<Etudiant> Etudiantlist = FXCollections.observableArrayList();
    @FXML
    void RemoveToChamp(MouseEvent event) {

    }
    EtudiantDao etudiantDao = new EtudiantDao();
    

    @FXML
    void SendEtudiantToBd(MouseEvent event) {
    	Etudiant etudiant = new Etudiant();
    	etudiant.setNom(Nom_Etudiant.getText());
    	etudiant.setPrenoms(Prenom_Etudiant.getText());
    	etudiant.setNumeroEtu(Integer.parseInt(Contact_Etudiant.getText()));
    	etudiant.setNomParnt(Nom_Parent.getText());
    	etudiant.setNumeroParnt(Integer.parseInt(ContactParent.getText()));
    	etudiant.setEmailParnt(EmailParent.getText());
    	etudiantDao.saveEtudiant(etudiant);
    	
    	Etudiantlist.add(etudiant);
    	chargementbtn();
    	//System.out.println(Etudiantlist);
    	
    	Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Etudiantlist = etudiantDao.getAllEtudiants();
		idContactEtuTab.setCellValueFactory(new PropertyValueFactory<>("numeroEtu"));
    	idNomEtuTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	idPrenomEtuTab.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
    	//EtudiantDao etudiantDao = new EtudiantDao();
    	Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
    	//System.out.println(Etudiantlist);
    	chargementbtn();
    	//Etudiant etudiant = new Etudiant();
    	/*etudiant = etudiantDao.recupererEtudiantByNom(1);
    	System.out.println(etudiant.getNom());*/
    	
    	
    	
    	
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
