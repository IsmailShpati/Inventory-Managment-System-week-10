package view_models;
/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.Product;
import resources.Color_Scheme;

public class ProductView extends GridPane{

   public ProductView() {
	   setAlignment(Pos.CENTER);
	   setPadding(new Insets(100, 30, 100, 30));
	   setHgap(30);
	   setVgap(10);
	   setStyle(Color_Scheme.buttonStyle);
	   initView("Product information will be shown here");
   }
   public void initView(String message) {
	   clear();
	   Label label = new Label(message);
	   styleLabel(label);
	   add(label, 0, 0);
   }

   public void initView(Product product) {
	   clear();
	   Label[] labels = {
			   new Label("Product name"),
			   new Label("Product description"),
			   new Label("Product ID"),
			   new Label("Has a catalogue"),
			   new Label("Color"),
			   new Label("Weight classification")
	   };
	   
	   initNodes(labels, 0, 0);
	   Node[] productInfo = {
			   new Label(product.getName()),
			   new Label(product.getDescription()),
			   new Label(product.getID()),
			   new Label(String.valueOf(product.getHasCatalogue())),
			   new Rectangle(20, 20, product.getColor().getColor()),
			   new Label(product.getWeightClassification())
	   };
	   initNodes(productInfo, 0, 1);
   }
   
   private void initNodes(Node[] nodes, int startingRow, int column) {
	
		   for(int i = 0; i < nodes.length ; i++) {
			   if(nodes[i] instanceof Label) 
				      styleLabel((Label)nodes[i]);
			   add(nodes[i], column, startingRow++);
		   }
	   
   }
   
   private void styleLabel(Label l) {
	   l.setFont(Font.font(18));
	  l.setTextFill(Color_Scheme.textColor);
   }

   private void clear() {
	   if(!getChildren().isEmpty())
		   getChildren().remove(0, getChildren().size());
   }
}
