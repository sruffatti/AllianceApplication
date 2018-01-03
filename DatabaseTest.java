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
	public void establishConnection(String dbPrefix, String dbSuffix) {
		try {
			conn = DriverManager.getConnection(dbPrefix + dbSuffix);
			
			if(conn.isValid(1)) {
				System.out.println("Database connection: "+conn.isValid(1)+"\n");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Make sure the database is in the right folder...\nC://AllianceApp/Database");
		}
	}
}