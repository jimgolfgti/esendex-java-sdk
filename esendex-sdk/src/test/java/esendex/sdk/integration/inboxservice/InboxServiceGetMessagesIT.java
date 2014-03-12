package esendex.sdk.integration.inboxservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.model.domain.response.InboxMessageResponse;
import esendex.sdk.service.InboxService;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class InboxServiceGetMessagesIT extends BaseTest {

    private static InboxMessageCollectionResponse messages;

    @BeforeClass
    public static void whenRetrievingTheInboxMessages() throws EsendexException {

        InboxService inboxService = getFactory().getInboxService();

        messages = inboxService.getMessages();
    }


    @Test
    public void thenMessagesAreReturned() {

        for(InboxMessageResponse response : messages.getMessages()) {
            assertNotNull(response.getId());
        }
    }
}
