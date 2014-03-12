
package esendex.sdk.model.transfer.contact;

import esendex.sdk.model.transfer.Dto;
import esendex.sdk.model.types.ContactType;

/**
 * Data holder for contact group entity data.  This class directly holds the
 * parsed xml data. 
 * @to.do This data is currently parsed but out of scope for the current release
 * @author Mike Whittaker
 */
public class ContactGroupDto extends Dto {
	
	private int contactcount;
	private String name;
	private ContactType type;
	
	/**
	 * Gets the contactcount.
	 * @return the contactcount
	 */
	protected int getContactcount() {
		return contactcount;
	}
	
	/**
	 * Gets the name.
	 * @return the name
	 */
	protected String getName() {
		return name;
	}
	
	/**
	 * Gets the type.
	 * @return the type
	 */
	protected ContactType getType() {
		return type;
	}
	
	
}
