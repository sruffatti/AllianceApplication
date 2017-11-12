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
		System.out.println("\nEstablishing connection to database.");
		DatabaseTest x = new DatabaseTest();
			x.establishConnection();
			
		//
		try {
			int count = 0;
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/AllianceApp/Database/petitionlist.accdb");
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT PetitionList.ID, PetitionList.Address, PetitionList.City, PetitionList.State "
					+ "FROM PetitionList "
					+ "WHERE PetitionList.Address is not null LIMIT 100");
			while(results.next()) {
				String formatted = String.format("%s %s %s", results.getString("Address"), results.getString("City"), results.getString("State"));
				Record a = new Record(results.getInt("ID") ,formatted);
				a.gatherInformation();
				System.out.println(a);
				count++;
			}
			System.out.println(count);
		} catch (SQLException e) {
			System.out.println("Error in querying database. SQL Exception");
		}		
	}
}
