package view_models;
/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import controlers.ProductControler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.ColorItem;
import models.Product;
import models.WeightClassification;
import resources.Color_Scheme;
import views.AddProductView;
import views.InventoryView;

public 
class BodyPane extends GridPane{
	
	private final double FONT_SIZE = 25; 
	private Color TEXT_COLOR = Color.ANTIQUEWHITE;
	//private final double FIELD_WIDTH = InventoryView.SCREEN_WIDTH*AddProductView.WIDTH_SCALE*0.4;
	private final double PREF_WIDTH = InventoryView.SCREEN_WIDTH* AddProductView.WIDTH_SCALE,
			       PREF_HEIGHT = InventoryView.SCREEN_HEIGHT*AddProductView.HEIGHT_SCALE;
	
	//Attributes needed to pass the information to product
	private TextField nameField = new TextField(), descriptionField = new TextField(), idField = new TextField();
	private CheckBox avaible, hasCatalogue;
	private ComboBox<ColorItem> avaibleColors;
	private ComboBox<String> weightPicker;
	
	private int offset = 4;
	
	private Label[] labels = {
			new Label("Name"),
			new Label("Description"),
			new Label("Auto Generate ID"),
			new Label("Has catalogue inside"),
			new Label("Color"),
			new Label("Weight")
	};
	
	private TextField[] fields = {
			nameField,
			descriptionField
		};
	
	public BodyPane() {
		 
		initPane();
			
		//GENERATING FIRST LABELS AND TEXT FIELDS
		putNodes( labels, 0, 0);
		putNodes( fields, 0, 1);
		     
		//GENERATING AUTO GENERATE ID CHECKBOX AND IT'S EVEN HANDLER
		addAutoGenerateID();
		
		//SECOND CHECKBOX
		addHasCatalogue();
		
		//COMBO BOX WITH COLORS 
		addColorPicker();
		
		//COMBO BOX FOR WEIGHT PICKER
		addWeightPicker();
		
		//BUTTON FOR GOING BACK
		addProductButton();
	}
	
	//================ METHODS FOR GENERATING PARTS OF THE VIEW ==============
	//Init pane style
	private void initPane() {
		 setAlignment(Pos.CENTER);
		 setHgap(20);
		 setStyle("-fx-border-color: " +Color_Scheme.borderColor_string +
				 "transparent"+ Color_Scheme.borderColor_string+ "transparent;");
	     setVgap(20);
	     setPadding(new Insets(50, 20, 20, 20));
	}
	//CheckBox
	private void addAutoGenerateID() {
		HBox autoGenerateID = new HBox(5); //Container for the checkbox and other components of it
		Label enterID = new Label("Enter id: ");
		styleLabel(enterID);		
		avaible = new CheckBox();
		avaible.setSelected(true);
		autoGenerateID.getChildren().addAll(avaible);
		avaible.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(avaible.isSelected()) {
					autoGenerateID.getChildren().removeAll(enterID, idField);
				}
				else {
					autoGenerateID.getChildren().addAll(enterID, idField);
				}
			}
		});
		
		add(autoGenerateID,  1, labels.length-offset--);
	}

    //CheckBox
	private void addHasCatalogue() {
		hasCatalogue = new CheckBox();
		add(hasCatalogue, 1, labels.length-offset--);
	}
	
	//ComboBox
	private void addColorPicker() {
		HBox colorPicker = new HBox(10);
		colorPicker.setAlignment(Pos.CENTER_LEFT);
	    avaibleColors = new ComboBox<>(); 
		initColors(avaibleColors);
		
		Rectangle showColor = new Rectangle(20, 20, ColorItem.COLORS[0].getColor());
		showColor.setStroke(Color.AQUA);
        avaibleColors.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showColor.setFill(avaibleColors.getValue().getColor());
			}
		});
		colorPicker.getChildren().addAll(avaibleColors, showColor);
        add(colorPicker, 1, labels.length - offset--);
	}
	
	//ComboBox
	private void addWeightPicker() {
		HBox container = new HBox(5);
		container.setAlignment(Pos.CENTER_LEFT);
	    weightPicker = new ComboBox<>();
		for(String s : WeightClassification.WEIGHTS)
			weightPicker.getItems().add(s);
		weightPicker.setValue(WeightClassification.WEIGHTS[0]);
		
		Label weightDescription = new Label(WeightClassification.DESCRIPTION);
		weightDescription.setFont(new Font(15));
		weightDescription.setTextFill(Color.WHITE);
		
		container.getChildren().addAll(weightPicker, weightDescription);
		add(container, 1, labels.length - offset--);
	
	}

	//Button
	private void addProductButton() {
		HBox btnContainer = new HBox();
		btnContainer.setAlignment(Pos.CENTER_RIGHT);
		btnContainer.setPadding(new Insets(20, 0, 0 ,0));
		
		Button addProd = new Button("+ ADD PRODUCT");
		addProd.setStyle(Color_Scheme.buttonStyle);
		addProd.setPrefSize(PREF_WIDTH*0.2, PREF_HEIGHT*0.05);
		addProd.setFont(Font.font(16));
		addProd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String msg = ProductControler.addProduct(getProductInfo());
				if(msg != null) {
					(new Alert(AlertType.ERROR, msg, ButtonType.OK)).show();
					
					}
				else {
					clearFields();
					(new Alert(AlertType.CONFIRMATION, "Product added succesfully", ButtonType.OK)).show();
				
				}
			}
		});
		
		btnContainer.getChildren().add(addProd);
		add(btnContainer, 1, labels.length - offset--);
	}
	
	
	//=============== OTHER HELPER METHODS FOR INITIALIZING AND STYLING NODES =============
	private void putNodes(Node[] nodes, int startingRow, int column) {
		for(int i = 0; i < nodes.length; i++) {
			add(nodes[i], column, i);
		}
		styleNodes(nodes);
	}
    
	private void styleNodes(Node[] nodes) {
	      if(nodes[0] instanceof Label) {
	    	  for(Node n : nodes)
	    		  styleLabel((Label)n);
          }
	      else if(nodes[0] instanceof TextField) {
	    	  for(Node n : nodes)
	    		  styleField((TextField)n);
	      }		
	}
	
	private void styleLabel(Label l) {
		l.setFont(new Font(FONT_SIZE));
        l.setTextFill(TEXT_COLOR);
	}

	private void styleField(TextField f) {
		//f.setPrefWidth();
	}

	private  void initColors(ComboBox<ColorItem> dropdown) {
		for(ColorItem i : ColorItem.COLORS)
			dropdown.getItems().add(i);
		//Set the default value
		dropdown.setValue(ColorItem.COLORS[0]);
	}
	
	//================== METHOD FOR PASSING THE INFO TO ProductControler ============= 
	
	public Product getProductInfo() {
		Product p = null;
		String name = nameField.getText();
		String description = descriptionField.getText();
		boolean isAutoGenerated = avaible.isSelected();
		boolean hasCatalogue = this.hasCatalogue.isSelected();
		ColorItem productColor = this.avaibleColors.getValue();
		String weight = this.weightPicker.getValue();
		p = new Product(name, description, isAutoGenerated, 
				hasCatalogue, productColor, weight);
		if(!isAutoGenerated)
		     p.setID(idField.getText()); 	
		return p;
	}

    //================= CLEARING TEXT FIELDS AFTER ADDING A PRODUCT ==============
	public void clearFields() {
		nameField.clear();
		descriptionField.clear();
		if(!avaible.isSelected())
			idField.clear();
		hasCatalogue.setSelected(false);
		avaibleColors.setValue(ColorItem.COLORS[0]);
		weightPicker.setValue(WeightClassification.WEIGHTS[0]);
	}
}

