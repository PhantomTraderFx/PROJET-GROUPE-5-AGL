package application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;
import javafx.collections.FXCollections;
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
    
//    @FXML
//    private TextField searchBox;
    
    //ObservableList<Etudiant> l = FXCollections.observableArrayList();
    List<Etudiant> Etudiantlist = FXCollections.observableArrayList();
    Etudiant etudiantEdit = new Etudiant();
    Etudiant e = new Etudiant();
    boolean btnedit = false;
    @FXML
    void RemoveToChamp(MouseEvent event) {

    }
    EtudiantDao etudiantDao = new EtudiantDao();
    

    @FXML
    void SendEtudiantToBd(MouseEvent event) {
    	try {
    		if(btnedit) {
        		btnedit = false;
        		Etudiant etu = new Etudiant();
        		etu.setIdEtu(e.getIdEtu());
        		etu.setNom(Nom_Etudiant.getText());
        		etu.setPrenoms(Prenom_Etudiant.getText());
        		etu.setNumeroEtu(Integer.parseInt(Contact_Etudiant.getText()));
        		etu.setNumeroParnt(Integer.parseInt(ContactParent.getText()));
        		etu.setNomParnt(Nom_Parent.getText());
        		etu.setEmailParnt(EmailParent.getText());
        		etudiantDao.updateEtudiant(etu);
        		Etudiantlist = etudiantDao.getAllEtudiants();
        		//chargementbtn();
        		Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
        	}
        	else {
        		Etudiant etudiant = new Etudiant();
            	etudiant.setNom(Nom_Etudiant.getText());
            	etudiant.setPrenoms(Prenom_Etudiant.getText());
            	etudiant.setNumeroEtu(Integer.parseInt(Contact_Etudiant.getText()));
            	etudiant.setNomParnt(Nom_Parent.getText());
            	etudiant.setNumeroParnt(Integer.parseInt(ContactParent.getText()));
            	etudiant.setEmailParnt(EmailParent.getText());
            	etudiantDao.saveEtudiant(etudiant);
            	Etudiantlist.add(etudiant);
            	//chargementbtn();
            	Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
        	}
        	
        	
        	
        	chargementbtn();
    	}catch(Exception e) {
    		System.out.println("Erruer vérifiez bien vos saisis");
    	}
    	
    	//System.out.println(Etudiantlist);
    	
    	
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Etudiantlist = etudiantDao.getAllEtudiants();
		idContactEtuTab.setCellValueFactory(new PropertyValueFactory<>("numeroEtu"));
    	idNomEtuTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	idPrenomEtuTab.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
    	//EtudiantDao etudiantDao = new EtudiantDao();
//    	for(Etudiant e: etudiantDao.getAllEtudiants()) {
//    		l.add(e);
//    	}
    	//Tabetu.getItems().setAll(etudiantDao.getAllEtudiants());
    	//System.out.println(Etudiantlist);
    	chargementbtn();
    	//Etudiant etudiant = new Etudiant();
    	/*etudiant = etudiantDao.recupererEtudiantByNom(1);
    	System.out.println(etudiant.getNom());*/
    	
//    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
//		FilteredList<Etudiant> filteredData = new FilteredList<>(l, p -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(person -> {
//				// If filter text is empty, display all persons.
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches first name.
//				} else if (person.getPrenoms().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//				return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Etudiant> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(Tabetu.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		Tabetu.setItems(sortedData);
    	
    	
    	
    	
    	
    	
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
