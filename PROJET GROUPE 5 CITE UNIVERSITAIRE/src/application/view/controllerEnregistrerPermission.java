package application.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.G5.dao.EtudiantDao;
import com.G5.model.Etudiant;

import antlr.collections.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class controllerEnregistrerPermission implements Initializable {

    @FXML
    private DatePicker DateSortie;

    @FXML
    private TextField HeureRetour;

    @FXML
    private TextField HeureSorti;

    @FXML
    private TableColumn<?, ?> IdAction;

    @FXML
    private TextField LieuEtudiant;

    @FXML
    private ChoiceBox<String> NameEtudiant;

    @FXML
    private TableColumn<?, ?> idLieuEtu;

    @FXML
    private TableView<?> idTablePermi;

    @FXML
    private TableColumn<?, ?> id_Date;

    @FXML
    private TableColumn<?, ?> id_HeureRetour;

    @FXML
    private TableColumn<?, ?> id_HeureSorti;

    @FXML
    private TableColumn<?, ?> id_NomPrenomEtu;

    @FXML
    void MethodPermi(ActionEvent event) {

    }

    @FXML
    void ValiderPermission(MouseEvent event) {

    }

    @FXML
    void select(MouseEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		EtudiantDao etudiantDao = new EtudiantDao();
		//java.util.List<Etudiant> list = new ArrayList<Etudiant>();
		//list = etudiantDao.getAllEtudiants();
		for(Etudiant e: etudiantDao.getAllEtudiants()) {
			NameEtudiant.getItems().addAll(e.getNom());
		};
		
		
	}

}
