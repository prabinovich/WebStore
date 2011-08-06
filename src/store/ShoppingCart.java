package store;

import java.util.HashMap;
import java.util.Iterator;

public class ShoppingCart {
	
	private HashMap<String,Integer> items;
	private ProductCatalog catalog;
	
	public ShoppingCart() {
		items = new HashMap<String, Integer>();
		catalog = new ProductCatalog();
	}
	
	public void addProduct(String id) {
		if(!items.containsKey(id)) {
			items.put(id, 1);
		} else {
			items.put(id, items.get(id)+1);
		}
	}
	
	public void setProduct(String id, int amount) {
		items.put(id, amount);
	}
	
	public void removeProduct(String id) {
		items.remove(id);
	}
	
	public int numProductByID(String id) {
		return items.get(id);
	}
	
	public boolean containsProduct(String id) {
		return items.containsKey(id);
	}
	
	public Iterator<String> getIterator() {
		return items.keySet().iterator();
	}
	
	public int numProducts() {
		return items.size();
	}
	
	public double calculateTotalCost() {
		Iterator<String> iter = getIterator();
		double cost = 0;
		while(iter.hasNext()) {
			String id = iter.next();
			int numItems = items.get(id);
			cost += catalog.getProductByID(id).getPrice() * numItems;
		}
		return cost;
	}
}
