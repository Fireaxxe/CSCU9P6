// A simple class for database records
public class DBRecord {

  private String details;
  private int count;
  
  //Constructor
  public DBRecord(int count, String details) { 
    this.count = count;
    this.details = details;
  }
  
  public void decrement() {
    count--;
  }
  
  public int getCount() {
    return count;
  }
  
  public String getDetails() {
    return details;
  }
  
}