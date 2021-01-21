package controlers;
/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import models.ColorItem;
import models.Product;

public class ProductControler {
 
	private final static File database = new File("src/resources/Database");
	private static PrintWriter writer;
	
	private static ArrayList<Product> products = new ArrayList<>();
	
	static {
		try {
			Scanner fin = new Scanner(database);
		    while(fin.hasNext()) {
		    	products.add(readProduct(fin));
		    }
			fin.close();
			writer = new PrintWriter(new FileWriter(database, true));
		} catch (FileNotFoundException e) {
			System.err.println("Couldnt find file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private ProductControler() { }
	
	public static ArrayList<Product> getProducts() { return products; }
	
	public static String addProduct(Product p) {
		if(p.getName().length() < 1)
			return "Please fill the name field";
		if(p.getDescription().length() < 1)
			return "Please fill the description";
		if(!p.getAutoGenerateID() && p.getID().length() < 1)
			return "Please enter an id";
		products.add(p);
		writer.append(p.toString());
		return null;
	}
	
	public static Product getProductByID(String productID) {
		for(Product p : products) {
			if(p.getID().equals(productID))
				return p;
		}
		return null;	
	}
	
	public static Product getProductByName(String name) {
		for(Product p : products) {
			if(p.getName().equals(name))
				return p;
		}
		return null;	
	}
	
//	//For debugging purposes
//	private static void printProducts() {
//		System.out.println(products.get(products.size()-1));
//	}
	
	private static Product readProduct(Scanner fin) {
	 String name = fin.next().replace('_', ' ');
   	 String description = fin.next().replace('_', ' ');
   	 String id = fin.next().replace('_', ' ');
   	 boolean hasCatalogue = fin.nextBoolean();
   	 ColorItem color = ColorItem.COLORS[fin.nextInt()];
   	 String weightClassification = fin.next();
   	 fin.nextLine();
   	 Product p = new Product(name, description, true, hasCatalogue, color, weightClassification);
   	 p.setID(id);
   	 return p;
	}

	public static void save() {
		writer.close();
	}
}
