
public class Text {
	private final String dbPrefix;
	private final String dbSuffix;
	private final String dbQuery;
	private final String dbDirectory;
	private final String dbLog;
	
	public Text() {
		dbPrefix = "jdbc:ucanaccess://C:/AllianceApp/Database/";
		dbSuffix = "petitionlist.accdb";
		dbQuery = "SELECT PetitionList.ID, PetitionList.Address, PetitionList.City, PetitionList.State " + 
				"FROM PetitionList " + 
				"WHERE PetitionList.Address is not null LIMIT 100";
		dbDirectory = "C:\\AllianceApp/Database";
		dbLog = "C:\\AllianceApp/Logs";
	}

	/**
	 * @return the dbPrefix
	 */
	public String getDbPrefix() {
		return dbPrefix;
	}

	/**
	 * @return the dbSuffix
	 */
	public String getDbSuffix() {
		return dbSuffix;
	}

	/**
	 * @return the dbQuery
	 */
	public String getDbQuery() {
		return dbQuery;
	}
	
	/**
	 * @return the dbDirectory
	 */
	public String getDbDirectory() {
		return dbDirectory;
	}
	
	/**
	 * @return the dbLog
	 */
	public String getDbLog() {
		return dbLog;
	}

	/**
	 * @return formatted dbConnection
	 */
	public String dbStringBuilder() {
		return dbPrefix + dbSuffix;
	}
}
