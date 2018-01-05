/**

 * This is the Main class. This class is used to drive the application.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Instantiate Text object containing all constants. 
			Text text = new Text();
		
		//Directory Builder.
		DirectoryBuilder builder = new DirectoryBuilder(text.getDbDirectory(), text.getDbLog());
			builder.buildDirectory();

		//Check for database connection
		DatabaseTest tester = new DatabaseTest();
			tester.establishConnection(text.getDbPrefix(), text.getDbSuffix());
			
		//Sequence of steps for establishing connection to db, execute a query, and loop through results
		try {
			//Connect to database
			Connection conn = DriverManager.getConnection(text.dbStringBuilder());
			
			//Instantiate statement, create statement
			Statement statement = conn.createStatement();
			
			//Execute query - query is from Text class
			ResultSet results = statement.executeQuery(text.getDbQuery());
			
				//Loop through results, instantiate record, and make API requests
				while(results.next()) {
					
					//Format address
					String formatted = formatAddress(results);
					
					//Instantiate record using query results and formatted address
					Record currRecord = new Record(results.getInt("ID") ,formatted);
					
					//Make API requests using current Record object
					currRecord.gatherInformation();
					
					//Print the current record to the console.
					System.out.println(currRecord);
				}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Error in querying database. SQL Exception");
		}		
	}

	/**
	 * Method is used to format multiple elements into a single string. 
	 * @param results
	 * @return
	 * @throws SQLException
	 */
	private static String formatAddress(ResultSet results) throws SQLException {
		String formatted = String.format("%s %s %s", results.getString("Address"), results.getString("City"), results.getString("State"));
		return formatted;
	}
}
