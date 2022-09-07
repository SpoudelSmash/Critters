package critters;

import critters.Controller;
import critters.Critter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application{

	
	 
    public static void main (String[] args) {
    	
        launch(args);
        
         
         
    }
    
    

	@Override
	public void start(Stage stage1) throws Exception {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Critters.fxml"));
		
		
		
		stage1.setTitle("Critters");
		
		Scene scene = new Scene(root);
		
		stage1.setScene(scene);
		
		stage1.show();
		
		
		
		
	}
}
