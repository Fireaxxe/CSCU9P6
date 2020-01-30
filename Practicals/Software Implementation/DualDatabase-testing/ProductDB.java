
import javax.swing.JComboBox;

public class ProductDB {

	private final int MAX = 5; // Used as limit on arrays

	private String[] productName = new String[MAX]; // The product data storage

	private String[] productDetails = new String[MAX];

	// Database constructor
	public ProductDB() {


		// Set up a simple database of products: names and some details
		productName[0] = "Table";
		productDetails[0] = "Four legged";
		productName[1] = "Chair";
		productDetails[1] = "Swivel";
		productName[2] = "Screen";
		productDetails[2] = "Black and white";
		productName[3] = "Whiteboard";
		productDetails[3] = "1m by 2m";
		productName[4] = "Door";
		productDetails[4] = "With brass fittings";

	} // constructor

	// setCustomerDetails

	// Fill up the given combo-box list with the contents of the productNames array
	public void fillProductList(JComboBox list) {

		list.removeAllItems(); // Empty out current contents
		for (int i = 0; i < MAX; i++)
			list.addItem(productName[i]);

	} // fillProductList

	// Search for the given product in the productNames array,
	// and return the corresponding details, or "Not found" if not found
	public String getProductDetails(String name) {

		for (int i = 0; i < MAX; i++)
			if (name.equals(productName[i])) // Check next text
				return productDetails[i]; // Found the required entry
		return "Not found"; // Didn't find the required entry

	} // getProductDetails

	// Search for the given product in the productNames array,
	// set their details if found and return "Done", or return "Not found"
	public String setProductDetails(String name, String details) {

		for (int i = 0; i < MAX; i++)
			if (name.equals(productName[i])) { // Check next text
				productDetails[i] = details; // Found the required entry
				return "Done";
			}
		return "Not found"; // Didn't find the required entry

	} // setProductDetails

} // class Database