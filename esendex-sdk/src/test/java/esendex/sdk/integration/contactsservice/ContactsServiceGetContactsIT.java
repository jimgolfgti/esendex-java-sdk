package esendex.sdk.integration.contactsservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.ContactResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContactsServiceGetContactsIT extends BaseTest {

    private static List<ContactResponse> contacts;
    private static int pageSize;

    @BeforeClass
    public static void whenGettingContacts() throws EsendexException {

        pageSize = 10;
        contacts = getFactory().getContactService().getContacts(0, pageSize);

    }

    @Test
    public void thenNoMoreThanThePageSizeOfContactsAreReturned() {

        assertTrue(contacts.size() <= pageSize);
    }

    @Test
    public void thenTheContactsContainTheExpectedData() {

        for (ContactResponse contact : contacts) {
            assertNotNull(contact.getMobileNumber());
            assertNotNull(contact.getQuickName());
            assertNotNull(contact.getFirstName());
            assertNotNull(contact.getLastName());
            assertNotNull(contact.getId());
            assertNotNull(contact.getType());
        }
    }

}
