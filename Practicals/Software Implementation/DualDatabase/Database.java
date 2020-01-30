/*
  Database class

  This class looks after a collection of customer name/details pairs in two arrays
  and product name/details pairs in two arrays.

  The arrays are set up with some basic fixed customer and product names.

  SBJ March 2002, revised February 2005, February 2007
*/

import javax.swing.*;

public class Database {

   private final int MAX = 5;                           // Used as limit on arrays

   private String[] customerName = new String[MAX];     // The customer data storage
   private String[] customerDetails = new String[MAX];

   private String[] productName = new String[MAX];      // The product data storage
   private String[] productDetails = new String[MAX];

   // Database constructor
   public Database() {
       
      // Set up a simple database of customers: names and some details
      customerName[0] = "James Smith";        customerDetails[0] = "House 1";
      customerName[1] = "Sarah Brown";        customerDetails[1] = "Flat 3";
      customerName[2] = "Alexander Johnson";  customerDetails[2] = "The Palace";
      customerName[3] = "Archie McKay";       customerDetails[3] = "Dunprogrammin";
      customerName[4] = "Moira Patterson";    customerDetails[4] = "House 2";
      
      // Set up a simple database of products: names and some details
      productName[0] = "Table";               productDetails[0] = "Four legged";
      productName[1] = "Chair";               productDetails[1] = "Swivel";
      productName[2] = "Screen";              productDetails[2] = "Black and white";
      productName[3] = "Whiteboard";          productDetails[3] = "1m by 2m";
      productName[4] = "Door";                productDetails[4] = "With brass fittings";
      
   } // constructor

   // Fill up the given combo-box list with the contents of the customerNames array
   public void fillCustomerList(JComboBox list) {
       
      list.removeAllItems();                                // Empty out current contents
      for (int i = 0; i < MAX; i++)
         list.addItem(customerName[i]);
     
   } // fillCustomerList

   // Search for the given customer in the customerNames array,
   // and return the corresponding details, or "Not found" if not found
   public String getCustomerDetails(String name) {
       
      for (int i = 0; i < MAX; i++)
         if (name.equals(customerName[i]))                  // Check next text
            return customerDetails[i];                      // Found the required entry
      return "Not found";                                   // Didn't find the required entry

   } // getCustomerDetails

   // Search for the given customer in the customerNames array,
   // set their details if found and return "Done", or return "Not found"
   public String setCustomerDetails(String name, String details) {
       
      for (int i = 0; i < MAX; i++)
         if (name.equals(customerName[i])) {                // Check next text
            customerDetails[i] = details;                   // Found the required entry
            return "Done";
         }
      return "Not found";                                   // Didn't find the required entry

   } // setCustomerDetails

   // Fill up the given combo-box list with the contents of the productNames array
   public void fillProductList(JComboBox list) {
       
      list.removeAllItems();                                // Empty out current contents
      for (int i = 0; i < MAX; i++)
         list.addItem(productName[i]);
     
   } // fillProductList

   // Search for the given product in the productNames array,
   // and return the corresponding details, or "Not found" if not found
   public String getProductDetails(String name) {
       
      for (int i = 0; i < MAX; i++)
         if (name.equals(productName[i]))                   // Check next text
            return productDetails[i];                       // Found the required entry
      return "Not found";                                   // Didn't find the required entry

   } // getProductDetails

   // Search for the given product in the productNames array,
   // set their details if found and return "Done", or return "Not found"
   public String setProductDetails(String name, String details) {
       
      for (int i = 0; i < MAX; i++)
         if (name.equals(productName[i])) {                 // Check next text
            productDetails[i] = details;                    // Found the required entry
            return "Done";
         }
      return "Not found";                                   // Didn't find the required entry

   } // setProductDetails

} // class Database