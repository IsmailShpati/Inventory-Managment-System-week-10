package views;

/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import controlers.ProductControler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Color_Scheme;
import view_models.BodyPane;

public class AddProductView extends Application {

	
	public final static double WIDTH_SCALE = 0.4, HEIGHT_SCALE = 0.7;
	
	private BorderPane root = new BorderPane();
	@Override
	public void start(Stage stage) throws Exception {
		
		root.setPadding(new Insets(0, 30, 0, 30));
		root.setStyle(Color_Scheme.backgroundStyle);
        root.setPrefWidth(InventoryView.SCREEN_WIDTH*0.4);
        root.setPrefHeight(InventoryView.SCREEN_HEIGHT*0.7);

		//============= ADD THE TOP BORDER ===============
		root.setTop(getHeader());

		//============= ADD THE CENTER BORDER ====================
        root.setCenter(new BodyPane());
      
        //============ ADD THE BOTTOM BORDER ===================
        root.setBottom(getBottomContainer(stage)); 
       
        stage.setScene(new Scene(root));
        stage.setTitle("Add product view");
	 	stage.show();
	}
	
	@Override
	public void stop() {
		ProductControler.save();
	}
	
	private StackPane getHeader() {
		StackPane container = new StackPane();
		container.setPadding(new Insets(30, 0, 30, 0));
		Text header = new Text("ADD A PRODUCT");
		header.setFont(Font.font("Default" ,FontWeight.BOLD, 50));
		header.setFill(Color_Scheme.textColor);
		container.getChildren().add(header);
		return container;
	}
   
	private HBox getBottomContainer(Stage stage) {
		HBox container = new HBox(30);
	    container.setPadding(new Insets(30));
	    container.setAlignment(Pos.CENTER_RIGHT);
		addButton(container, "<- BACK", getGoBackHandler(stage));
		return container;
	}
	
	private void addButton(HBox parent, String btnText, EventHandler<ActionEvent> handler) {
		Button button = new Button(btnText);
		button.setStyle(Color_Scheme.buttonStyle);
		button.setOnAction(handler);
		button.setPrefWidth(root.getPrefWidth()*0.2);
		button.setPrefHeight(root.getPrefHeight()*0.05);
		parent.getChildren().add(button);
	}

	
	private EventHandler<ActionEvent> getGoBackHandler(Stage stage){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new InventoryView().start(new Stage());
					stage.close();
				} catch (Exception e) {
					(new Alert(AlertType.ERROR, "[AddProductView(line 62)]")).show();
				}
			}
		};
	}
    


}






