package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class controllerHistoriqueSorties {

    @FXML
    private ChoiceBox<?> CommandeNomEtudiant;

    @FXML
    private DatePicker DateHistorique;

    @FXML
    private TableColumn<?, ?> idHR;

    @FXML
    private TableColumn<?, ?> idHS;

    @FXML
    private TableColumn<?, ?> idLieu;

    @FXML
    private TableColumn<?, ?> idNom;

    @FXML
    void validerTableHistory(MouseEvent event) {

    }

}
