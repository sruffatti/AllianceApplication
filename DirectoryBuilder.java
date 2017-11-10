/**
 * Directory Builder class. This class is used to build the folder structure. 
 */

/**
 * @author Sean
 * @version 1.0
 */
import java.io.File;
public class DirectoryBuilder {
	private File failLog;
	private File successLog;
	private File database;
	
	/**
	 * DirectoryBuilder constructor 
	 */
	public DirectoryBuilder() {
		setDatabase(new File("C:\\AllianceApp/Database"));
		setSuccessLog(new File("C:\\AllianceApp/Logs/Success"));
		setFailLog(new File("C:\\AllianceApp/Logs/Fail"));
	}

	/**
	 * @return the database
	 */
	public File getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(File database) {
		this.database = database;
	}

	/**
	 * @return the successLog
	 */
	public File getSuccessLog() {
		return successLog;
	}
	
	/**
	 * @param File the successLog
	 */
	public void setSuccessLog(File successLog) {
		this.successLog = successLog;
	}

	/**
	 * @return failLog
	 */
	public File getFailLog() {
		return failLog;
	}
	
	/**
	 * @param File failLog
	 */
	public void setFailLog(File failLog) {
		this.failLog = failLog;
	}
	
	/**
	 * Builds directories
	 */
	public void buildDirectory() {
		if(!checkDirectory()) {
			database.mkdirs();
			successLog.mkdirs();
			failLog.mkdirs();
			if(database.exists() && successLog.exists() && failLog.exists()) {
				System.out.println("Built directory");
			}
			else {
				System.out.println("Directory build failed. Refer to ReadMe doc or email developer - seanruffatti@yahoo.com");
			}
		}
		else {
			System.out.println("Directory exists.");
		}
	}
	
	/**
	 * Check to see if the directories exist
	 * @return returns boolean value
	 */
	public boolean checkDirectory() {
		if(successLog.exists() && database.exists() && failLog.exists()) {
			return true;
		}
		return false;
	}
}
