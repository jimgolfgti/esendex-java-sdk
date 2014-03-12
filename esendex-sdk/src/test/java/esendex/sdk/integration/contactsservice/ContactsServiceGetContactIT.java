package esendex.sdk.integration.contactsservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.ContactResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

public class ContactsServiceGetContactIT extends BaseTest {

    private static List<ContactResponse> expectedContacts;
    private static List<ContactResponse> actualContacts;

    @BeforeClass
    public static void whenGettingContacts() throws EsendexException {

        expectedContacts = getFactory().getContactService().getContacts(0, 5);

        actualContacts = new Vector<ContactResponse>();
        for (ContactResponse expectedContact : expectedContacts) {
            ContactResponse contact = getFactory().getContactService().getContact(expectedContact.getId());

            actualContacts.add(contact);
        }

    }

    @Test
    public void thenTheContactsContainTheExpectedData() {

        for (ContactResponse expectedContact : expectedContacts) {

            ContactResponse actual = getActualContactResponseForId(expectedContact.getId());

            assertTrue(actual.getMobileNumber().equals(expectedContact.getMobileNumber()));
            assertTrue(actual.getQuickName().equals(expectedContact.getQuickName()));
            assertTrue(actual.getFirstName().equals(expectedContact.getFirstName()));
            assertTrue(actual.getLastName().equals(expectedContact.getLastName()));
            assertTrue(actual.getId().equals(expectedContact.getId()));
            assertTrue(actual.getType().equals(expectedContact.getType()));
        }
    }

    private ContactResponse getActualContactResponseForId(String id) {

        for(ContactResponse actualContact : actualContacts) {
            if ( actualContact.getId().equals(id) ) {
                return actualContact;
            }
        }
        return null;
    }

}
