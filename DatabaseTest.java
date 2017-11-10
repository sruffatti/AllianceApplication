import java.sql.*;
/**
 * This class sets up a database connection.
 */

/**
 * @author Sean
 * @version 1.0
 */
public class DatabaseTest {
	private Connection conn;
	
	/**
	 * no-Arg constructor for database test class
	 */
	public DatabaseTest() {}

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Method to check if connection to database can be established.
	 */
	public void establishConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:ucanaccess://C:/AllianceApp/Database/petitionlist.accdb");
			
			if(conn.isValid(1)) {
				System.out.println("Database connection: "+conn.isValid(1));
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Make sure the database is in the right folder...\nC://AllianceApp/Database");
		}
	}
}