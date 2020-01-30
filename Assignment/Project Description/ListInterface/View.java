import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JPanel 
                  implements Observer {

  private Model model;
  private Controller contr;
  
  private JList stockList = new JList(new DefaultListModel());  // The visible list widget
  private JScrollPane scrollList = new JScrollPane(stockList);  // Embedded in a scrolling window
  
  private JTextArea description = new JTextArea(5,20);  // For item details when requested
  
  private int showingDetailsOf = -1;             // Index in DB of item to display details of,
                                                 // none initially
  
  public View(Controller contr, Model model) {
    this.contr = contr;
    this.model = model;
    // Set up view GUI
    setBackground(Color.cyan);
    setPreferredSize(new Dimension(450, 100));
    add(new JLabel("Items in stock"));
    stockList.setVisibleRowCount(5);
    add(scrollList);
    add(description);
    // Subscribe to the model
    model.addObserver(this);
    update();                              // Set up display from model initially
  }
  
  // The model invokes this
  public void update(Observable o, Object arg) {   
    update();
  }
  
  // Re-populate the displayed stock list from the database
  private void update() {
    Extract list = model.getInStock();    // Get list of in-stock items in the DB
    DefaultListModel theList = (DefaultListModel) stockList.getModel();  // Get the displayed stock list
    theList.clear();     // Clear the stockList
    // Then re-fill it
    for (int i = 0; i <= list.top; i++)   // Add each stock item in list to the stockList
      theList.addElement(list.items[i]);
    deselect();
    showDetails();                        // Update the details display
  }
  
  // Deselect currently selected item in the stockList
  public void deselect() {
    stockList.clearSelection();
  }
  
  // Return index in the DB of item selected in the stockList, -1 if none selected
  public int getSelected() {
    StockListItem sLI = (StockListItem) stockList.getSelectedValue();  // The selected item
    if (sLI == null)
    	return -1;                    // Nothing selected
    else 
    	return sLI.getIndexInDB();    // Position in the DB
  }
  
  // Change to displaying stock level of DB item n (n assumed valid) in text area
  public void showDetails(int n) {
    showingDetailsOf = n;
    showDetails();   
  }
  
  // Decoupled from previous method so that update() can call it too
  private void showDetails() {
    if (showingDetailsOf == -1)
      description.setText("");
    else
      description.setText("Stock level of \n"
                          + model.getName(showingDetailsOf) + "\n"
                          + "is "+model.getStockLevel(showingDetailsOf));
  }
    
}

