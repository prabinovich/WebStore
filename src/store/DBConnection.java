package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DBConnection {
	
	private static String account = "appuser"; 
	private static String password = "password"; 
	private static String server = "localhost:3306";
	private static String database = "webstore"; 
	
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
		}
		catch (SQLException e) {
			e.printStackTrace();
			
			Logger logger = Logger.getLogger(this.getClass());
			BasicConfigurator.configure();
			logger.error(e.toString());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			Logger logger = Logger.getLogger(this.getClass());
			BasicConfigurator.configure();
			logger.error(e.toString());
		}
		
		return rs;
	}	
}
