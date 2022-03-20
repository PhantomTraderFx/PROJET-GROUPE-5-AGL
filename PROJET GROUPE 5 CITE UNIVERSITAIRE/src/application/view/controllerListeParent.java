package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * @author Ezekiel
 *
 */
public class controllerListeParent implements Initializable {
	@FXML
	private TableView<Etudiant> idTab;
	
	@FXML
    private TextField searchBox;

    @FXML
    private TableColumn<Etudiant,String> id_MailParent;

    @FXML
    private TableColumn<Etudiant, String> id_NomEtudiant;

    @FXML
    private TableColumn<Etudiant,String> id_NomParent;

    @FXML
    private TableColumn<Etudiant, String> id_NumeroParent;

    @FXML
    private TableColumn<Etudiant, String> id_PrenomParent;
    
    @FXML
    private TableColumn <?, ?> IdAction;
    
    ObservableList<Etudiant> l = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		id_NumeroParent.setCellValueFactory(new PropertyValueFactory<>("numeroParnt"));
		id_NomEtudiant.setCellValueFactory(new PropertyValueFactory<>("nom"));
		id_MailParent.setCellValueFactory(new PropertyValueFactory<>("emailParnt"));
		id_NomParent.setCellValueFactory(new PropertyValueFactory<>("nomParnt"));
		EtudiantDao etudiantDao = new EtudiantDao();
		idTab.getItems().setAll(etudiantDao.getAllEtudiants());
		
		for(Etudiant e: etudiantDao.getAllEtudiants()) {
			l.add(e);
			
		}
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Etudiant> filteredData = new FilteredList<>(l, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (person.getPrenoms().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Etudiant> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(idTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		idTab.setItems(sortedData);
		
	}

}
