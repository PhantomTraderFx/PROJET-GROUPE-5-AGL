package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class controllerCommande {

    @FXML
    private ChoiceBox<?> CommandeNomEtudiant;

    @FXML
    private TableView<?> IdTable;

    @FXML
    private ChoiceBox<?> JourCommande;

    @FXML
    private ChoiceBox<?> PlatMidi;

    @FXML
    private ChoiceBox<?> PlatSoir;

    @FXML
    private Button enregistrerCommande;

    @FXML
    private TableColumn<?, ?> id_Dimanche;

    @FXML
    private TableColumn<?, ?> id_DimancheMidi;

    @FXML
    private TableColumn<?, ?> id_DimancheSoir;

    @FXML
    private TableColumn<?, ?> id_Jeudi;

    @FXML
    private TableColumn<?, ?> id_JeudiMidi;

    @FXML
    private TableColumn<?, ?> id_JeudiSoir;

    @FXML
    private TableColumn<?, ?> id_Lundi;

    @FXML
    private TableColumn<?, ?> id_LundiMidi;

    @FXML
    private TableColumn<?, ?> id_LundiSoir;

    @FXML
    private TableColumn<?, ?> id_Mardi;

    @FXML
    private TableColumn<?, ?> id_MardiMidi;

    @FXML
    private TableColumn<?, ?> id_MardiSoir;

    @FXML
    private TableColumn<?, ?> id_Mercredi;

    @FXML
    private TableColumn<?, ?> id_MercrediMidi;

    @FXML
    private TableColumn<?, ?> id_MercrediSoir;

    @FXML
    private TableColumn<?, ?> id_NomPrenomEtu;

    @FXML
    private TableColumn<?, ?> id_Samedi;

    @FXML
    private TableColumn<?, ?> id_SamediMidi;

    @FXML
    private TableColumn<?, ?> id_SamediSoir;

    @FXML
    private TableColumn<?, ?> id_Vendredi;

    @FXML
    private TableColumn<?, ?> id_VendrediMidi;

    @FXML
    private TableColumn<?, ?> id_VendrediSoir;

    @FXML
    void MethodeTableView(ActionEvent event) {

    }

    @FXML
    void SaveToTable(MouseEvent event) {

    }

}
