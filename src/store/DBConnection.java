package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static String account = "ccs108avald"; 
	private static String password = "eibezafu"; 
	private static String server = "mysql-user.stanford.edu";
	private static String database = "c_cs108_avald"; 
	
	public DBConnection() {
		
	}
	
	public ResultSet queryDBWith(String query) {
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://" + server, account ,password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			if(query.equals("")) {
				rs = stmt.executeQuery("SELECT * FROM products");
			} else {
				rs = stmt.executeQuery("SELECT "+query+" FROM products");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return rs;
	}	
}
