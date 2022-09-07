package critters;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {
	/* Gets the package name.  The usage assumes that Critter and its
    subclasses are all in the same package. */
	 private static String myPackage; // package of Critter file.
	 
	 List<Button> butts = new ArrayList<Button>();

	
	 /* Critter cannot be in default pkg. */
	 static {
	     myPackage = Critter.class.getPackage().toString().split(" ")[1];
	 }
	 
	public Controller() {
    
//	    System.out.println("working");

	}

    @FXML
    private ChoiceBox<String> critterCB = new ChoiceBox<String>();
    

    @FXML
    private GridPane worldGrid;
    
    @FXML
    private Slider seed_slider;

    @FXML
    private TextField spawnField = new TextField();
    
    @FXML
    private TextArea statsText = new TextArea();
    
    @FXML
    private TextFlow stats_pop;
    
    @FXML
    private TextField newCrit;
    

    @FXML
    private TextField stepsField;


    
    @FXML
    public void initialize() {
    	
    	butts.add(clear);
    	butts.add(seed);
    	butts.add(add);
    	butts.add(quit);
    	butts.add(show);
    	butts.add(spawn);
    	butts.add(st1);
    	butts.add(st10);
    	butts.add(st100);
    	butts.add(st1000);
    	butts.add(st5);
    	butts.add(st50);
    	butts.add(stats);
    	
    	showB(null);
    }
    
    @FXML
    void exitProgram(ActionEvent event) {
    	System.exit(0);
    }
    
    

    
    
    void addText(String text) {
    	
    	statsText.setText(statsText.getText() + text + "\n");
    }
    void clearText() {
    	statsText.clear();
    }

    void showWorld() {
    	Critter.displayWorld(worldGrid);
    	
    }
    
    /**	
     * Buttons
     */
    
    @FXML
    private Button add;

    @FXML
    private Button clear;


    @FXML
    private Button quit;

    @FXML
    private Button seed;


    @FXML
    private Button show;

    @FXML
    private Button spawn;


    @FXML
    private Button st1;

    @FXML
    private Button st10;

    @FXML
    private Button st100;

    @FXML
    private Button st1000;

    @FXML
    private Button st5;

    @FXML
    private Button st50;

    @FXML
    private Button stats;


    
    @FXML
    void addB(ActionEvent event) {
    	String newCritter = newCrit.getText();
    	
    	
    	ObservableList<String> list = critterCB.getItems();
    	list.add(newCritter);
    }
    
    @FXML
    void seedB(ActionEvent event) {
    	int seed = (int) seed_slider.getValue();
    	Critter.setSeed(seed);
    	
    	addText("seed");
    }

    @FXML
    void showB(ActionEvent event) {
    	
    	addText("show");
    	worldGrid.getChildren().clear();
    	int size = 500;
    	int cellSize = size / Params.WORLD_WIDTH;
    	
    	for(int i = 0; i < Params.WORLD_WIDTH; i++) {
    		ColumnConstraints col = new ColumnConstraints(cellSize);
    		worldGrid.getColumnConstraints().clear();
    		worldGrid.getColumnConstraints().add(col);
    	}
    	
    	for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
			//test
			RowConstraints row = new RowConstraints(cellSize);
			
			worldGrid.getRowConstraints().clear();
	    	worldGrid.getRowConstraints().add(row);	
		}
    	for(int i = 0; i < Params.WORLD_WIDTH; i++) {
    		for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
    			Rectangle r = new Rectangle();
				
				r.setWidth(cellSize);
				r.setHeight(cellSize);
				r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK);
				
				worldGrid.add(r, i, j);
				GridPane.setValignment(r, VPos.CENTER);
				GridPane.setHalignment(r, HPos.CENTER);
    		}
    	}
    	worldGrid.setHgap(0);
    	worldGrid.setVgap(0);
    	
    	
    	Critter.displayWorld(worldGrid);
    	
    }

    @FXML
    void spawnB(ActionEvent event) {
    	String critter = critterCB.getValue();
    	int n = 1;
    	
    	try {
    		n = Integer.parseInt(spawnField.getText());
    	}
    	catch(NumberFormatException e) {
    		addText("error: wrong spawn #");
    	}
    	
    	
    	for (int i = 0; i < n; i++) {

			try {
				Critter.createCritter(critter);
			} catch (InvalidCritterException e) {
				addText("error: wrong critter");
			}

		}
    	
    	
    	addText("spawn");
    }
    
    @FXML
    void statsB(ActionEvent event) {
    	
		
		try {
			
			
			String uq = critterCB.getValue();
			
			List<Critter> critters = Critter.getInstances(uq);
			
			Class<?> critClass = Class.forName(myPackage +"." + uq);
			
			Method method = critClass.getMethod("runStats", List.class);
			
			
			String statStr = (String) method.invoke(critClass, critters);
		
			
			clearText();
	    	addText(statStr);
			
		}
		catch(Exception e){ //Catch any exception
			System.out.println("error processing: stats");
		}
		
		
    	
    	
    }

    @FXML
    void st1000_B(ActionEvent event) {
    	int steps = 1000;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	addText("1000");
    }

    @FXML
    void st100_B(ActionEvent event) {
    	int steps = 100;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	
    	addText("100");
    }

    @FXML
    void st10_B(ActionEvent event) {
    	int steps = 10;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	addText("10");
    }

    @FXML
    void st1_B(ActionEvent event) {
    	int steps = 1;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	addText("1");
    }

    @FXML
    void st50_B(ActionEvent event) {
    	int steps = 50;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	addText("50");
    }

    @FXML
    void st5_B(ActionEvent event) {
    	int steps = 5;
    	
    	for(int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
    	addText("5");
    }

    @FXML
    void clearB(ActionEvent event) {
    	Critter.clearWorld();
    	
    	addText("clear");
    	
    }
    

    @FXML
    void startAni(ActionEvent event) {
//    	Timeline animationT = new Timeline(new KeyFrame(Duration.seconds(.5), new KeyValue));
    	for(Button b : butts) {
    		b.setDisable(true);
    	}
    	int speed = Integer.valueOf(stepsField.getText());
    	for(int i = 0; i < speed; i++) {
    		Critter.worldTimeStep();
    	}
    	Critter.displayWorld(worldGrid);
    	
    }


    @FXML
    void stopAni(ActionEvent event) {
    	for(Button b : butts) {
    		b.setDisable(false);
    	}
    }
    
   
    

    
}
