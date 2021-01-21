package view_models;
/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import controlers.ProductControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;

public class ProductsTable extends TableView<Product> {
	private ObservableList<Product> listProduct; 
	
	public ProductsTable() {
		
        initList();
        initTable();
		initColumns();
		setStyle("-fx-font-size: 18px;");
	}


	private void initList() {
		listProduct = FXCollections.observableArrayList();
		
		for(Product p : ProductControler.getProducts()) {
			listProduct.add(p);
		}
	}
	
	private void initTable() {
		setItems(listProduct);
		setStyle("-fx-background-color: transparent;");
	
	}

	private void initColumns() {
		addTableColumn("Name", "name");
		addTableColumn("Description", "description");
		addTableColumn("ID", "ID");
		addTableColumn("Catalogue", "hasCatalogue");
		addTableColumn("Weight", "weightClassification");
		addTableColumn("Color", "color");
	}
	
	private void addTableColumn(String columnName, String attributeName){
		TableColumn<Product, String> column = new TableColumn<>(columnName);
		column.setCellValueFactory(
				new PropertyValueFactory<>(attributeName));
		getColumns().add(column);
	}

}
