
package litebrite;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import litebrite.LiteBrite.Anims;

/**
 *
 * @author Lum Naw
 */
public class LiteBrite extends Application {
	
	final ColorPicker colorPicker = new ColorPicker(); 
	public static boolean checkFill= false;			//check if rectangle is colored

	private Paint color =colorPicker.getValue();   //value of color that user chose
	
	//setter for color
    public void setColor(Paint color){
    	this.color = color;
    }
     
    @Override
    public void start(final Stage stage) throws Exception {        
      	int rows = 50;
       	int columns = 50;

         stage.setTitle("Enjoy your game");

         GridPane grid = new GridPane();
         grid.getStyleClass().add("game-grid");

         for(int i = 0; i < columns; i++) {
             ColumnConstraints column = new ColumnConstraints(10);
             grid.getColumnConstraints().add(column);
         }

         for(int i = 0; i < rows; i++) {
             RowConstraints row = new RowConstraints(10);
             grid.getRowConstraints().add(row);
         }

         for (int i = 0; i < columns; i++) {
             for (int j = 0; j < rows; j++) {
                 Pane pane = new Pane();
                     pane.setOnMouseReleased(e -> {  
                      pane.getChildren().add(Anims.getAtoms(color));                    		 
                  
                 });
                 pane.getStyleClass().add("game-grid-cell");
                 if (i == 0) {
                     pane.getStyleClass().add("first-column");
                 }
                 if (j == 0) {
                     pane.getStyleClass().add("first-row");
                 }
                 grid.add(pane, i, j);
             }
         }
         	
        HBox mainScene = new HBox(); 						//create main scene
     	mainScene.getStyleClass().add("scene-background");
     	
        VBox rightPane = new VBox(10);						//create Vbox for colorpicker and reset
   
        colorPicker.getStyleClass().add("button");			//create button for colorpicker
       
        colorPicker.setOnAction(e ->{
        	this.setColor(colorPicker.getValue());			//get value of color and set it to colorpicker
             	       	
        });
       
        
        rightPane.getStyleClass().add("right-pane");
        
        HBox resetButtonPane = new HBox(5); 				//Hbox that contains reset button
        Button resetButton = new Button("Reset");			//reset button
        

        resetButton.getStyleClass().add("button");
        resetButton.setOnAction(e ->{
        	resetGridPane(grid);
        });
        resetButtonPane.getChildren().add(resetButton);		//add resetButton on resetButtonPane
        resetButtonPane.setAlignment(Pos.CENTER);			//align resetButton at the center 
        
        rightPane.getChildren().addAll(colorPicker, resetButtonPane);//add colorpicker and resetButtonPane on rightPane
        mainScene.getChildren().addAll(grid, rightPane);		// add grid and rightPane to mainScene

        Scene scene = new Scene(mainScene);
        scene.getStylesheets().add(LiteBrite.class.getResource("resources/game.css").toExternalForm());//add the css file
        stage.setScene(scene);
        stage.show();
    }
    
     //reset whole grid
     public GridPane resetGridPane(GridPane grid){
    	 for (int i = 0; i < 50; i++) {
             for (int j = 0; j < 50; j++) {
            	
            	  Pane pane = new Pane();
                  pane.setOnMouseReleased(e -> {
                 	
                      pane.getChildren().add(Anims.getAtoms(color));
                      
                  });
                  
                  pane.getStyleClass().add("game-grid-cell");
                  if (i == 0) {
                      pane.getStyleClass().add("first-column");
                  }
                  if (j == 0) {
                      pane.getStyleClass().add("first-row");
                  }
                  grid.add(pane, i, j);
               
             }
         }
    	 return grid;
     }
     
     public static class Anims {
    	
    	    public static Node getAtoms(Paint color) { 
    	      Rectangle rectangle = new Rectangle(0,0,9,9);
       	    	
    	      
    	      if (checkFill==false) {
    	    	  rectangle.setFill(color);
    	    	  checkFill =true;
    	      }
    	      else {
    	    	  rectangle.setFill(Color.BLACK);
    	    	  checkFill=false;
    	      }
    	    	  
        	    return rectangle;      	        	
    	    }
    	    
    	  
    	}

     public static void main(String[] arguments) {
         Application.launch(arguments);
         
     }
     
}


