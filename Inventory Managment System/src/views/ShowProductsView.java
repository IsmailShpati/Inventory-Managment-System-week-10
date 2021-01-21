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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Color_Scheme;
import view_models.ProductsTable;

public class ShowProductsView extends Application {
	
	private final double WIDTH_SCALE = 0.5, HEIGHT_SCALE = 0.55;
	private VBox root = new VBox(40);

	@Override
	public void start(Stage primaryStage) throws Exception {
       
		root.setStyle(Color_Scheme.backgroundStyle);
        root.setPadding(new Insets(0, 30, 0, 30));
        root.setPrefSize(InventoryView.SCREEN_WIDTH*WIDTH_SCALE, InventoryView.SCREEN_HEIGHT*HEIGHT_SCALE);
		root.getChildren().addAll(getHeader(), new ProductsTable(), getFooter(primaryStage));
		primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Table view");
        primaryStage.show();
        
	}
	
	@Override
	public void stop() {
		ProductControler.save();
	}
	

	private HBox getHeader() {
		HBox container = new HBox();
		Text title = new Text("ALL PRODUCTS");
		title.setFont(Font.font("", FontWeight.BOLD, 50));
		container.setAlignment(Pos.CENTER);
		title.setFill(Color_Scheme.textColor);
		container.setPadding(new Insets(30, 60, 30, 60));
		container.setStyle("-fx-border-color: transparent transparent" + Color_Scheme.borderColor_string + "transparent;");
		container.getChildren().add(title);
		return container;
	}
	
	private VBox getFooter( Stage primaryStage) {
		VBox footer = new VBox();
		footer.setPadding(new Insets(0, 0, 30, 0));
		footer.setAlignment(Pos.CENTER_RIGHT);
		Button back = new Button("<- BACK");
		back.setStyle(Color_Scheme.buttonStyle);
		back.setPrefWidth(root.getPrefWidth()*0.13);
		back.setFont(Font.font(18));
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				try {
					new InventoryView().start(new Stage());
					primaryStage.close();
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			
				
			}
		});
		footer.getChildren().add(back);
		return footer;
	}
}

 
