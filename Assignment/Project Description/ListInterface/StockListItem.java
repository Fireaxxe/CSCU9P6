public class StockListItem {

	private String itemDisplayName;

	private int indexInDB;

	public StockListItem(String itemDisplayName, int indexInDB) {
		this.indexInDB = indexInDB;
		this.itemDisplayName = itemDisplayName;
	}

	public int getIndexInDB() {
		return indexInDB;
	}

	public void setIndexInDB(int indexInDB) {
		this.indexInDB = indexInDB;
	}

	public String getItemDisplayName() {
		return itemDisplayName;
	}

	public void setItemDisplayName(String itemDisplayName) {
		this.itemDisplayName = itemDisplayName;
	}
	
	public String toString() {
		return itemDisplayName;
	}
}
