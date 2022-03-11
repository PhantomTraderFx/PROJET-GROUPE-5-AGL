package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene scene;
	@Override
	public void start(Stage primaryStage) throws IOException{
		scene = new Scene(loadFXML("view/Acceuil"),1500,800);
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().setValue(false);
		primaryStage.show();
	}
	
	/*public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}*/

	private static Parent loadFXML(String fxml) throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml+ ".fxml"));
		return fxmlLoader.load();
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
