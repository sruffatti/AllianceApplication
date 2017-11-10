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
		
		//Directory Builder.
		System.out.println("Starting up Alliance Application.\n\nChecking directory.");
		DirectoryBuilder builder = new DirectoryBuilder();
			builder.buildDirectory();

		//Check for database connection
		System.out.println("\nEstablishing connection to database.\n");
		DatabaseTest x = new DatabaseTest();
			x.establishConnection();
			
		//Database connection for queries
		try {
			Record a = queryDB();
			if(a != null) {
				System.out.println(a);
			}
			System.out.println("Finished.");
		} catch (SQLException e) {
			System.out.println("Error in querying database. SQL Exception");
		}		
	}

	/**
	 * @throws SQLException
	 */
	private static Record queryDB() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/AllianceApp/Database/petitionlist.accdb");
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery("SELECT PetitionList.ID, PetitionList.Address, PetitionList.City, PetitionList.State "
				+ "FROM PetitionList "
				+ "WHERE PetitionList.Address is not null LIMIT 1");
		while(results.next()) {
			String formatted = String.format("%s %s %s", results.getString("Address"), results.getString("City"), results.getString("State"));
			Record a = new Record(Integer.parseInt(results.getString("ID")) ,formatted);
			a.gatherInformation();
			return a;
		}
		return null;
	}
}
