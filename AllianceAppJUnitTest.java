import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author Sean
 *
 */
class AllianceAppJUnitTest {
	
	//Variables
	Text text;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		text = new Text();
	}

	@Test
	void test() {
		//Tests for Text class
		assertEquals(text.getDbPrefix(), "jdbc:ucanaccess://C:/AllianceApp/Database/");
		assertEquals(text.getDbSuffix(), "petitionlist.accdb");
		assertEquals(text.getDbDirectory(), "C:\\AllianceApp/Database");
		assertEquals(text.getDbLog(), "C:\\AllianceApp/Logs");
		assertEquals(text.getDbQuery(), "SELECT PetitionList.ID, PetitionList.Address, PetitionList.City, PetitionList.State " + 
				"FROM PetitionList " + 
				"WHERE PetitionList.Address is not null LIMIT 100");
	}
}
