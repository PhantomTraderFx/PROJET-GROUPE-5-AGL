package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class controllerListeParent implements Initializable {
	@FXML
	private TableView<Etudiant> idTab;

    @FXML
    private TableColumn<Etudiant,String> id_MailParent;

    @FXML
    private TableColumn<?, ?> id_NomEtudiant;

    @FXML
    private TableColumn<Etudiant,String> id_NomParent;

    @FXML
    private TableColumn<Etudiant, String> id_NumeroParent;

    @FXML
    private TableColumn<Etudiant, String> id_PrenomParent;
    
    @FXML
    private TableColumn <?, ?> IdAction;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		id_MailParent.setCellValueFactory(new PropertyValueFactory<>("emailParnt"));
		id_NomParent.setCellValueFactory(new PropertyValueFactory<>("nomParnt"));
		EtudiantDao etudiantDao = new EtudiantDao();
		idTab.getItems().setAll(etudiantDao.getAllEtudiants());
		
	}

}
