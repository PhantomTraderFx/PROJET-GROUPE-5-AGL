package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class controllerEnregistrerPermission {

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
    private ChoiceBox<?> NameEtudiant;

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

}
