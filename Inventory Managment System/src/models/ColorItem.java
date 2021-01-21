package models;
/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */

import javafx.scene.paint.Color;

public class ColorItem {
	
	public static ColorItem[] COLORS = {
		new ColorItem("BLACK", Color.BLACK),
		new ColorItem("GREEN", Color.GREEN),
		new ColorItem("RED", Color.RED),
		new ColorItem("BLUE", Color.BLUE),
		new ColorItem("WHITE", Color.WHITE),
		new ColorItem("CYAN", Color.CYAN),
		new ColorItem("BROWN", Color.BROWN),
		new ColorItem("YELLOW", Color.YELLOW)
	};
	private String name;
	private Color color;
	
	ColorItem(String name, Color color){
		this.name = name;
		this.color = color;
	}
	
	public String getName() { return name; }
	public Color getColor() { return color; }
	
	public static int getIndex(ColorItem color) {
		for(int i = 0; i < COLORS.length; i++)
			if(COLORS[i] == color)
				return i;
		return -1;
	}
	
	public String toString() {
		return name;
	}
}