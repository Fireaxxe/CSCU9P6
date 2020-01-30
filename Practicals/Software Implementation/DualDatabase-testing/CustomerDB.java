
import javax.swing.JComboBox;

public class CustomerDB {

	private final int MAX = 5; // Used as limit on arrays

	private String[] customerName = new String[MAX]; // The customer data storage

	private String[] customerDetails = new String[MAX];

	// Database constructor
	public CustomerDB() {

		// Set up a simple database of customers: names and some details
		customerName[0] = "James Smith";
		customerDetails[0] = "House 1";
		customerName[1] = "Sarah Brown";
		customerDetails[1] = "Flat 3";
		customerName[2] = "Alexander Johnson";
		customerDetails[2] = "The Palace";
		customerName[3] = "Archie McKay";
		customerDetails[3] = "Dunprogrammin";
		customerName[4] = "Moira Patterson";
		customerDetails[4] = "House 2";


	} // constructor

	// Fill up the given combo-box list with the contents of the customerNames array
	public void fillCustomerList(JComboBox list) {

		list.removeAllItems(); // Empty out current contents
		for (int i = 0; i < MAX; i++)
			list.addItem(customerName[i]);

	} // fillCustomerList

	// Search for the given customer in the customerNames array,
	// and return the corresponding details, or "Not found" if not found
	public String getCustomerDetails(String name) {

		for (int i = 0; i < MAX; i++)
			if (name.equals(customerName[i])) // Check next text
				return customerDetails[i]; // Found the required entry
		return "Not found"; // Didn't find the required entry

	} // getCustomerDetails

	// Search for the given customer in the customerNames array,
	// set their details if found and return "Done", or return "Not found"
	public String setCustomerDetails(String name, String details) {

		for (int i = 0; i < MAX; i++)
			if (name.equals(customerName[i])) { // Check next text
				customerDetails[i] = details; // Found the required entry
				return "Done";
			}
		return "Not found"; // Didn't find the required entry

	} // setCustomerDetails

	// setProductDetails

} // class Database