package application;

/**
 * Main class that starts the whole program by showing the main stage.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		    Scene scene = new Scene(root);	    
		    scene.getStylesheets().add("application/Main.css");
		    primaryStage.setTitle("Medication Reminder");
			 primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/library/icon.png")));

		    primaryStage.setScene(scene);
		    primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
