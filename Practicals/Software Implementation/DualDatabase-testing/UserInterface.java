/*
 A database user interface.

 Note: This is a JFrame so gives a standalone window, but needs a main class to
 launch the application and instantiate this class.

 Provides interactive access to a database of customer names and details (in
 one panel of the window) and to a database of product names and details (in
 another panel of the window).

 SBJ March 2002, revised February 2005, February 2007
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface extends JFrame implements ActionListener {

	/**
	 * @link association
	 */

	private Database theData; // Reference, initialized in the constructor

	private JPanel customerPanel = new JPanel(); // Customer management panel

	private JComboBox customerList = new JComboBox(); // For displaying a drop down list of customers

	private JButton getCustomerDetailsButton = new JButton(
			"Get customer details"); // Action buttons

	private JButton setCustomerDetailsButton = new JButton(
			"Set customer details");

	private JTextField customerDetailsField = new JTextField(30); // For displaying/entering the selected customer's details

	private JPanel productPanel = new JPanel(); // Product management panel

	private JComboBox productList = new JComboBox(); // For displaying a drop down list of products

	private JButton getProductDetailsButton = new JButton("Get product details"); // Action buttons

	private JButton setProductDetailsButton = new JButton("Set product details");

	private JTextField productDetailsField = new JTextField(30); // For displaying/entering the selected product's details

	// Frame constructor
	public UserInterface(Database theData) {

		this.theData = theData; // Record the reference to the database
		setTitle("Database user interface");
		setSize(600, 200); // Width, height of window
		createGUI(); // Set up the GUI

	} // constructor

	// Set up the GUI
	public void createGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new GridLayout(2, 1)); // Otherwise JFrame defaults to BorderLayout

		// Set up customer management panel
		customerPanel.setBackground(Color.cyan);
		customerPanel.add(new JLabel("Customer:"));
		theData.fillCustomerList(customerList); // Populate the list with the customer names
		customerPanel.add(customerList);
		customerList.addActionListener(this); // Notify actionPerformed when a selection is made
		customerList.setEditable(false); // Set not editable - for choosing only
		customerPanel.add(getCustomerDetailsButton);
		getCustomerDetailsButton.addActionListener(this); // Notify actionPerformed when button is clicked
		customerPanel.add(setCustomerDetailsButton);
		setCustomerDetailsButton.addActionListener(this); // Notify actionPerformed when button is clicked
		customerPanel.add(customerDetailsField);
		customerDetailsField.setEditable(true); // Allow user editing
		window.add(customerPanel);

		// Set up product management panel
		productPanel.setBackground(Color.green);
		productPanel.add(new JLabel("Product:"));
		theData.fillProductList(productList); // Populate the list with the product names
		productPanel.add(productList);
		productList.addActionListener(this); // Notify actionPerformed when a selection is made
		productList.setEditable(false); // Set not editable - for choosing only
		productPanel.add(getProductDetailsButton);
		getProductDetailsButton.addActionListener(this); // Notify actionPerformed when button is clicked
		productPanel.add(setProductDetailsButton);
		setProductDetailsButton.addActionListener(this); // Notify actionPerformed when button is clicked
		productPanel.add(productDetailsField);
		productDetailsField.setEditable(true); // Allow user editing
		window.add(productPanel);

	} // createGUI

	// Handle button presses and selection of items from the lists
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == customerList) // Clear text field when an item is selected
			customerDetailsField.setText("");

		if (e.getSource() == productList) // Clear text field when an item is selected
			productDetailsField.setText("");

		if (e.getSource() == getCustomerDetailsButton) { // Get customer details?
			String chosen = (String) customerList.getSelectedItem(); // Which customer selected?
			String theDetails = theData.getCustomerDetails(chosen); // Look up details
			customerDetailsField.setText(theDetails); // And display them
		}

		if (e.getSource() == setCustomerDetailsButton) { // Change customer details?
			String chosen = (String) customerList.getSelectedItem(); // Which customer selected?
			String theDetails = customerDetailsField.getText(); // Look up details
			String result = theData.setCustomerDetails(chosen, theDetails); // Update the database
			customerDetailsField.setText(result); // And report the result
		}

		if (e.getSource() == getProductDetailsButton) { // Get product details?
			String chosen = (String) productList.getSelectedItem(); // Which product selected?
			String theDetails = theData.getProductDetails(chosen); // Look up details
			productDetailsField.setText(theDetails); // And display them
		}

		if (e.getSource() == setProductDetailsButton) { // Change product details?
			String chosen = (String) productList.getSelectedItem(); // Which product selected?
			String theDetails = productDetailsField.getText(); // Look up details
			String result = theData.setProductDetails(chosen, theDetails); // Update the database
			productDetailsField.setText(result); // And report the result
		}

		// No need for repaint(); as there is no paint!

	} // actionPerformed

} // class UserInterface