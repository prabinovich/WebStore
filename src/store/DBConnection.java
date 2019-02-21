package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import net.sf.cache4j.CacheFactory;
import net.sf.cache4j.Cache;
import net.sf.cache4j.CacheException;
import java.io.InputStream;
import java.io.FileInputStream;

public class DBConnection {
	
	private static String dbaccount = "appuser"; 
	private static String dbpassword = "Password1234%"; 
	private static String server = "localhost:3306";
	private static String database = "webstore"; 
	
	public DBConnection() {
		// Initialize cache factory
		CacheFactory sacheFactory = CacheFactory.getInstance ();
	    try {
	        InputStream in = new FileInputStream ("cache-config.xml");
	        sacheFactory.loadConfig (in);
	    } catch (Exception e) {
	        // ...
	    }
	}
	
	public ResultSet checkUserLogin(String uname, String upass){
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://" + server, dbaccount ,dbpassword);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
	        String sql = "SELECT * FROM users WHERE username='" + uname + "' AND password='" + upass + "'";
	        rs = stmt.executeQuery(sql);
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
	
	public ResultSet queryDBWith(String query) {
		ResultSet rs = null;
		String foo = null;
		
		// Check if data is available in cache (not functioning code; mockup only)
		try {
            // try to get the account with the ID number from the cache
			foo = (String) CacheFactory.getInstance().getCache("account").get(1);
        } catch (CacheException ce) {
            // blah
        }
		if (foo != null) {
			return rs;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://" + server, dbaccount ,dbpassword);
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
