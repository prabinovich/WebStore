package store;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductCatalog {

	//private HashMap<String,Product> catalogMap;
	//private ArrayList<Product> catalogList;
	
	public ProductCatalog() {
	}
	
	public Product getProductByID(String id) {
		HashMap<String,Product> catalogMap;
		
		catalogMap = new HashMap<String,Product>();
		//catalogList = new ArrayList<Product>();
		DBConnection db = new DBConnection();
		ResultSet rs = db.queryDBWith("*");
		try {
			while(rs.next()) {
				Product temp = new Product(rs.getString("productid"),
						rs.getString("name"),
						rs.getString("imagefile"),
						rs.getDouble("price"));
				catalogMap.put(rs.getString("productid"),temp);
				//catalogList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catalogMap.get(id);
	}
	
	public Set<String> getProductIDSet() {
		HashMap<String,Product> catalogMap;
		
		catalogMap = new HashMap<String,Product>();
		//catalogList = new ArrayList<Product>();
		DBConnection db = new DBConnection();
		ResultSet rs = db.queryDBWith("*");
		try {
			while(rs.next()) {
				Product temp = new Product(rs.getString("productid"),
						rs.getString("name"),
						rs.getString("imagefile"),
						rs.getDouble("price"));
				catalogMap.put(rs.getString("productid"),temp);
				//catalogList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catalogMap.keySet();
	}
	
	public ArrayList<Product> getProductList() {
		ArrayList<Product> catalogList;
		
		//catalogMap = new HashMap<String,Product>();
		catalogList = new ArrayList<Product>();
		DBConnection db = new DBConnection();
		ResultSet rs = db.queryDBWith("*");
		try {
			while(rs.next()) {
				Product temp = new Product(rs.getString("productid"),
						rs.getString("name"),
						rs.getString("imagefile"),
						rs.getDouble("price"));
				//catalogMap.put(rs.getString("productid"),temp);
				catalogList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catalogList;
	}
}
