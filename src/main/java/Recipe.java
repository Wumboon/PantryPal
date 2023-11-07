package src.main.java;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color; 

public class Recipe extends HBox {

	private Label index;
    private String recipeName;
    private Button selectButton;
    private Text text;
    
    private boolean isSelected;

    public Recipe() {
    	this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #266024; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        isSelected = false;

        /*
        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
        this.getChildren().add(index); // add index label to task
        */

        text = new Text(); // create task name text field
        text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        text.setStyle("-fx-background-color: #266024; -fx-border-width: 0;"); // set background color of texfield
        text.setTextAlignment(TextAlignment.CENTER); // set alignment of text field
        text.setFill(Color.WHITE);
        this.getChildren().add(text); // add textlabel to task

        /*
        selectButton = new Button("Done"); // creates a button for marking the task as done
        selectButton.setPrefSize(100, 20);
        selectButton.setPrefHeight(Double.MAX_VALUE);
        selectButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button
        */
        
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
        	toggleSelect();
        	event.consume();
        });

        //this.getChildren().add(selectButton);
        
    	/*
        this.setPrefSize(500, 50); // sets size of recipe
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of recipe

        
        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the recipe
        this.getChildren().add(index); // add index label to recipe
        

        text = new Text(recipeName);
        //recipeName.setPrefSize(380, 20); // set size of text field
        text.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        
        //recipeName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(text); // add textlabel to recipe
        */

    }

    public void setRecipeIndex(int num) {

    }

    public String getRecipeName() {
        return this.recipeName;
    }
    
    public void setRecipeName(String name) {
        recipeName = name;
    }
    
    public void updateText() {
    	text.setText(recipeName);
    }
    
    public Button getSelectButton() {
        return this.selectButton;
    }
    
    public boolean isSelected() {
        return this.isSelected;
    }

    public void toggleSelect() {
        
        if (!this.isSelected) {
            isSelected = true;
            this.setStyle("-fx-background-color: #3AA037; -fx-border-width: 0; -fx-font-weight: bold;");

        } else {
            isSelected = false;
            this.setStyle("-fx-background-color: #266024; -fx-border-width: 0; -fx-font-weight: bold;");     
        }
    }

}


class RecipeList extends VBox {
	
	private ActionsList actionsList;	

    RecipeList() {
    	this.setSpacing(5); // sets spacing between tasks
        this.setPrefSize(300, 560);
        this.setStyle("-fx-background-color: #559952;");
        // String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        
        actionsList = new ActionsList();
        this.getChildren().add(actionsList);
    }
    
    public Button getNewRecipeButton() {
        return actionsList.getNewRecipeButton();
    }
    public Button getEditRecipeButton() {
        return actionsList.getEditRecipeButton();
    }
    
    public void updateRecipeIndices() {
        int index = 1;
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof Recipe) {
                ((Recipe) this.getChildren().get(i)).setRecipeIndex(index);
                index++;
            }
        }
    }
}

class ActionsList extends HBox {
    private Button newRecipeButton;
    private Button editRecipeButton;
    
    ActionsList() {
        this.setPrefSize(300, 50);
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #2B6429;");

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        newRecipeButton = new Button("New Recipe");
        newRecipeButton.setStyle(defaultButtonStyle);
        editRecipeButton = new Button("Edit Recipe");
        editRecipeButton.setStyle(defaultButtonStyle);

        this.getChildren().setAll(newRecipeButton, editRecipeButton);
        this.setAlignment(Pos.CENTER);
    }

    public Button getNewRecipeButton() {
        return newRecipeButton;
    }
    public Button getEditRecipeButton() {
        return editRecipeButton;
    }
}


class RecipeDetails extends HBox {
	
	public RecipeDetails (Optional<String> recipeName) {
        String currDisplay = recipeName.orElse("Default");
        this.setPrefSize(900, 60); // Size of the header
        this.setStyle("-fx-background-color: #BCE29E;");

        if (currDisplay == "Default") {
            Text titleText = new Text("Choose a recipe"); // Text of the Header
            titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
            this.getChildren().add(titleText);
            this.setAlignment(Pos.CENTER); // Align the text to the Center
        } else {
            Text titleText = new Text(currDisplay); // Text of the Header
            titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
            this.getChildren().add(titleText);
            
            Text displayType = new Text(currDisplay);
            displayType.setFont(Font.font("Arial", 14));

            Text displayIngredients = new Text(currDisplay);
            displayIngredients.setFont(Font.font("Arial", 14));

            Text displayDirections = new Text(currDisplay);
            displayDirections.setFont(Font.font("Arial", 14));


            this.getChildren().addAll(displayType, displayIngredients, displayDirections);
            this.setAlignment(Pos.CENTER); // Align the text to the Center
        }
    }
}