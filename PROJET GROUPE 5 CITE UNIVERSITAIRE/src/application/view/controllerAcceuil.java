package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



/**
 * @author Ezekiel
 *
 */
public class controllerAcceuil {
	private Parent fxml;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    void GoToCommande(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("EnregistrerCommande.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    }

    @FXML
    void GoToHistory(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("HistoriqueSorties.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    	
    }

    @FXML
    void GoToHome(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("img.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    }

    @FXML
    void GoToListParent(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("ListeDesParents.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);

    }

    @FXML
    void GoToParentSave(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("EnregistrerParent.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    }

    @FXML
    void GoToPermission(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("EnregistrerPermission.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    }

    @FXML
    void GoToStudentSave(MouseEvent event) throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("EnregistrerEtudiant.fxml"));
    	root.getChildren().removeAll();
    	root.getChildren().setAll(fxml);
    }

    @FXML
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'Acceuil.fxml'.";

    }

}
