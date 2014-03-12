
package esendex.sdk.integration;

import org.junit.Test;
import static org.junit.Assert.*;

public class SanityIT extends BaseTest {

	/**
	 * Test account details available.
	 */
	@Test
	public void testAccountDetailsAvailable() {
		assertTrue("To perform the tests BaseTest.USER, BaseTest.PASSWORD and " +
				"BaseTest.ACCOUNT must all be set", 
				USER != null && PASSWORD != null && ACCOUNT!= null);	
	}
}
