/*
 Database class

 This class looks after a collection of customer name/details pairs in two arrays
 and product name/details pairs in two arrays.

 The arrays are set up with some basic fixed customer and product names.

 SBJ March 2002, revised February 2005, February 2007
 */

import javax.swing.JComboBox;

public class Database {

			/**
	 * @link aggregation
	 */
	ProductDB theProductDB = new ProductDB();
	/**
	 * @link aggregation
	 */
	
	private CustomerDB theCustomerDB = new CustomerDB();

	// Database constructor
	public Database() {


	} // constructor

	// Fill up the given combo-box list with the contents of the customerNames array
	public void fillCustomerList(JComboBox list) {

		theCustomerDB.fillCustomerList(list);

	} // fillCustomerList

	// Search for the given customer in the customerNames array,
	// and return the corresponding details, or "Not found" if not found
	public String getCustomerDetails(String name) {

		return theCustomerDB.getCustomerDetails(name);

	} // getCustomerDetails

	// Search for the given customer in the customerNames array,
	// set their details if found and return "Done", or return "Not found"
	public String setCustomerDetails(String name, String details) {

		return theCustomerDB.setCustomerDetails(name, details);
	} // setCustomerDetails

	// Fill up the given combo-box list with the contents of the productNames array
	public void fillProductList(JComboBox list) {

		theProductDB.fillProductList(list);

	} // fillProductList

	// Search for the given product in the productNames array,
	// and return the corresponding details, or "Not found" if not found
	public String getProductDetails(String name) {

		return theProductDB.getProductDetails(name);

	} // getProductDetails

	// Search for the given product in the productNames array,
	// set their details if found and return "Done", or return "Not found"
	public String setProductDetails(String name, String details) {

		return theProductDB.setProductDetails(name,details);

	} // setProductDetails

} // class Database