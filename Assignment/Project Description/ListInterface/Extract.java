// Just a wrapper around an array and top index pair

// The array is intended for passing around a list of items
// from a database array.

public class Extract {

  public final int max = 50;  
  public StockListItem[] items = new StockListItem[max];     // An array filled
  public int top = -1;                     // up to this index
  
  public void add(StockListItem sLI) {
    if (top < max-1)                       // Silently don't add if full
      items[++top] = sLI;
  }

}