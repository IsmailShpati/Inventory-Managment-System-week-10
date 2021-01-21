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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Product;
import resources.Color_Scheme;
import view_models.ProductView;

public class GetProductView extends Application {

	public final static double WIDTH_SCALE = 0.35, HEIGHT_SCALE = 0.7; 

	private BorderPane root = new BorderPane();	
	private ProductView body = new ProductView();
	private Stage mainStage;
	
	@Override
	public void start(Stage stage) throws Exception {
		//Initialize the root node 
		root.setPrefSize(InventoryView.SCREEN_WIDTH*WIDTH_SCALE, InventoryView.SCREEN_HEIGHT*HEIGHT_SCALE);
		root.setStyle("-fx-background-color: linear-gradient(to bottom right, #485461, #28313B)");
		//Initialize mainStage which is used by methods for closing the stage
		mainStage = stage;
		
		initHeader();
		
		root.setCenter(body);
		
		initFooter();
		
		stage.setScene(new Scene(root));
		stage.setTitle("Get product");
		stage.show();
	}

	@Override
	public void stop() {
		ProductControler.save();
	}
	
	//=========== TOP =================
	private void initHeader() {
		VBox header = new VBox();
		header.setPadding(new Insets(10, 30, 30, 30));
		header.setAlignment(Pos.CENTER);
		
		
		GridPane getProduct = new GridPane();
		initGetProduct(getProduct);
		
		header.getChildren().addAll(getTitle(), getProduct);
		root.setPadding(new Insets(20, 30, 20, 30));
		root.setTop(header);
        
	}
	private HBox getTitle() {
		HBox titleContainer = new HBox();
		
		Text title = new Text("GET PRODUCT");
		title.setFont(Font.font("", FontWeight.BOLD, 50));
		title.setFill(Color_Scheme.textColor);

		titleContainer.setAlignment(Pos.CENTER);
		titleContainer.getChildren().add(title);

		return titleContainer;
	}
	
	private void initGetProduct(GridPane parent) {
		parent.setPadding(new Insets(30, 30, 30, 10));
		ComboBox<String> searchBy = new ComboBox<>();
		searchBy.setStyle("-fx-font-size: 18px");
		searchBy.setValue("Name");
		searchBy.getItems().addAll("ID","Name");
		parent.setAlignment(Pos.CENTER);
		parent.setHgap(30);
		parent.setVgap(10);
		parent.add(searchBy, 1, 0);
		parent.setStyle("-fx-border-color: transparent transparent" +Color_Scheme.borderColor_string +"transparent");
		
		TextField searchField = new TextField();
		parent.add(searchField, 3, 0);
        searchField.setStyle("-fx-font-size: 18px");
		Button search = new Button("GET PRODUCT");
		parent.add(search, 3, 1);
		search.setStyle(Color_Scheme.buttonStyle);
		search.setFont(Font.font(18));
		search.setPrefSize(root.getPrefWidth()*0.25, root.getPrefWidth()*0.07);
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(searchField.getText().length() < 1) {
					(new Alert(AlertType.ERROR, "Dont leave the field empty!")).show();
				    return;
			    }
				String message;
				Product p;
				
				if(searchBy.getValue().equals("ID")) {
					p = ProductControler.getProductByID(searchField.getText());
				    message = "id could not be found.";
				}
				else {
					p = ProductControler.getProductByName(searchField.getText());
					message = "name could not be found";
				}
				
				if(p == null) {
					body.initView("\""+ searchField.getText()+"\" "  + message);
					mainStage.sizeToScene();
				}
				else {
					body.initView(p);
					mainStage.sizeToScene();
				}
			}
		});
	}
	
	//================= BOTTOM =======================
	private void initFooter() {
		Button backBtn = new Button("<- BACK");
		backBtn.setStyle(Color_Scheme.buttonStyle);
		backBtn.setPrefSize(root.getPrefWidth()*0.15, root.getPrefWidth()*0.07);
        backBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					new InventoryView().start(new Stage());
					mainStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
        backBtn.setFont(Font.font(18));
		BorderPane.setAlignment(backBtn, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(backBtn, new Insets(30));
		root.setBottom(backBtn);
	}

	
  
	
}
