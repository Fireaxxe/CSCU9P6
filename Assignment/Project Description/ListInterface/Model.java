import java.util.*;

// A simple stock control database

// The items in the database are stored in an array and are referred to by their index.
// This is a bit crude. A better option would be to include a key field in DBRecord,
// say a part number, and to use a HashTable here, hashing on the key field.
// The method getInStock would then return a list of part numbers - thus decoupling
// the identification of each item from precisely where it is held (in case it changes!).

public class Model extends Observable {

  private DBRecord[] database;
  
  // Constructor
  public Model() { 
    database = new DBRecord[10];
    database[0] = new DBRecord(3, "Wheels");
    database[1] = new DBRecord(10, "Spark plugs");
    database[2] = new DBRecord(9, "Seats");
    database[3] = new DBRecord(5, "Doors");
    database[4] = new DBRecord(20, "Nuts");
    database[5] = new DBRecord(4, "Petrol tanks");
    database[6] = new DBRecord(99, "Handles");
    database[7] = new DBRecord(5, "Exhaust pipes");
    database[8] = new DBRecord(11, "Air bags");
    database[9] = new DBRecord(4, "Furry dice");
}   
  
  // One "modifier" service:
  
  // "Sell": Decrement specified item in database
  public void decrement(int n) {
    database[n].decrement();
    setChanged();
    notifyObservers();
  }
  
  // Three "view" services:
  
  // Return list of items of items still in stock
  public Extract getInStock() {
    Extract list = new Extract();                // An Extract contains an array of indices and details
    for (int i = 0; i < database.length; i++)
      if (database[i].getCount() > 0) {
    	StockListItem sLI = new StockListItem(database[i].getDetails(), i);
        list.add(sLI);
     }
    return list;
  }
  
  // Return count from specified item in database
  public int getStockLevel(int n) {
    return database[n].getCount();
  }
  
  // Return part name of specified item in database
  public String getName(int n) {
    return database[n].getDetails();
  }

}