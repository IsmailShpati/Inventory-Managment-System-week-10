package models;

/*
 * @Ismail Shpati
 * BINF B / GROUPD D 
 */
public class Product{
	
	private String name, description;
	private ColorItem color;
	private String weightClassification;
	private String id;
	private Boolean autoGenerateID, hasCatalogue;
	
	public Product(String name, String description, Boolean autoGenerateID, Boolean hasCatalogue, ColorItem color,
			String weightClassification) {
		this.name = name;
		this.description = description;
		this.autoGenerateID = autoGenerateID;
		if(autoGenerateID)
			autoGenerateID();
		this.hasCatalogue = hasCatalogue;
		this.color = color;
		this.weightClassification = weightClassification;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getHasCatalogue() {
		if(hasCatalogue)
			return "Yes";
		return "No";
	}

	public ColorItem getColor() {
		return color;
	}

	public String getWeightClassification() {
		return weightClassification;
	}
	
	public String getID() { return id; }
	
	public void setID(String id) { this.id = id; }
	
	public Boolean getAutoGenerateID() { return autoGenerateID; }
	
	private void autoGenerateID() {
		this.id = "~"+ name + "%";
	}

	public String toString() {
		return name.replace(' ', '_') + " " + description.replace(' ', '_') + " " + id.replace(' ', '_') + " " + hasCatalogue + " "
				+ ColorItem.getIndex(color) + " " + weightClassification +"\n" ;
	}
	
}
