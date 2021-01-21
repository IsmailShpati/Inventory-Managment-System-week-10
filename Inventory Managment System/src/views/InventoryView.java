package views;

/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import controlers.ProductControler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import resources.Color_Scheme;

//THE MAIN VIEW 
public class InventoryView extends Application{
	
	public final static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
	public final static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
	public final static double WIDTH_SCALE = 0.6, HEIGHT_SCALE = 0.45;
	private Stage stage;
	private HBox root = new HBox();
	
	public static void main(String[] args) {
		launch();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		root.setStyle(Color_Scheme.backgroundStyle);
		root.setPrefSize(SCREEN_WIDTH*WIDTH_SCALE, SCREEN_HEIGHT*HEIGHT_SCALE);
		//root.setPadding(new Insets(0, 30, 0, 30));
		setLeftSide();
		setRightSide();
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
	    primaryStage.show();	
	}
	
	@Override
	public void stop() {
		ProductControler.save();
	}
	
	//================ LEFT SIDE =======================
	private void setLeftSide() { //title, picture, author
		VBox leftSide = new VBox(SCREEN_HEIGHT*HEIGHT_SCALE*0.1);
		leftSide.setPrefWidth(SCREEN_WIDTH*WIDTH_SCALE/2);
		leftSide.setPadding(new Insets(30));
		leftSide.setStyle("-fx-border-color: transparent" + Color_Scheme.borderColor_string + "transparent transparent;");
		leftSide.getChildren().addAll(getTitle(), getImage(), getAuthor());
		leftSide.setAlignment(Pos.CENTER);
		root.getChildren().add(leftSide);
	}
	private HBox getTitle() {
		HBox container = new HBox();
		//Center title to the middle of the box
		container.setPadding(new Insets(0, 0, 30, 0));
		container.setAlignment(Pos.CENTER);
		container.setStyle("-fx-border-color: transparent transparent" + Color_Scheme.borderColor_string + "transparent;");
       
		Text title = new Text("INVENTORY\nMANAGMENT SYSTEM");
        styleText(title, 44);
        container.getChildren().add(title);
		return container;
	}
	
	private HBox getImage() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);
		ImageView image = new ImageView(new Image("/resources/icon-pages-warehouse-light2.png"));
		image.setFitWidth(root.getPrefWidth()/3.5);
		//Original picture has same dimensions on both width and height
		image.setFitHeight(root.getPrefWidth()/3.5);
		container.getChildren().add(image);
		return container;
	}
	
	private HBox getAuthor() {
		HBox container = new HBox();
		container.setPadding(new Insets(0, 0, 10, 0));
		container.setAlignment(Pos.BOTTOM_RIGHT);
		Text author = new Text("Ismail Shpati\nBINF D");
		styleText(author, 34);
		container.getChildren().add(author);
		return container;
	}
	
	private void styleText(Text text, double fontSize) {
		 text.setTextAlignment(TextAlignment.CENTER);
	     text.setFill(Color_Scheme.textColor);
	     text.setFont(Font.font("", FontWeight.BOLD, fontSize));
	}
	//=============== RIGHT SIDE ==========================
	public void setRightSide() {
		VBox rightSide = new VBox(root.getPrefHeight()*0.1);
		rightSide.setPrefWidth(root.getPrefWidth()*0.5);
		rightSide.setAlignment(Pos.CENTER);
		rightSide.setPadding(new Insets(50));
		
		addButton(rightSide, "ADD PRODUCT", new AddProductView());
		
		addButton(rightSide, "GET PRODUCT", new GetProductView());
		
		addButton(rightSide, "SHOW PRODUCTS", new ShowProductsView());
	
        root.getChildren().add(rightSide);	
	}
 	private void addButton(VBox rightSide, String text, Application a) {
		Button btn = new Button(text);
		btn.setPrefSize(root.getPrefWidth()*0.4, root.getPrefHeight()*0.2);
		btn.setFont(Font.font("", FontWeight.MEDIUM, 35));
		btn.setStyle(Color_Scheme.buttonStyle);
		btn.setOnAction((event) -> {
			try { new AddProductView().start(new Stage()); stage.close();
			}catch(Exception e) {} });
		rightSide.getChildren().add(btn);
	}

	
}