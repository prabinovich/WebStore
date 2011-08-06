package store;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductCatalog {

	private HashMap<String,Product> catalogMap;
	private ArrayList<Product> catalogList;
	
	public ProductCatalog() {
		catalogMap = new HashMap<String,Product>();
		catalogList = new ArrayList<Product>();
		DBConnection db = new DBConnection();
		ResultSet rs = db.queryDBWith("*");
		try {
			while(rs.next()) {
				Product temp = new Product(rs.getString("productid"),
						rs.getString("name"),
						rs.getString("imagefile"),
						rs.getDouble("price"));
				catalogMap.put(rs.getString("productid"),temp);
				catalogList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Product getProductByID(String id) {
		return catalogMap.get(id);
	}
	
	public Set<String> getProductIDSet() {
		return catalogMap.keySet();
	}
	
	public ArrayList<Product> getProductList() {
		return catalogList;
	}
}
