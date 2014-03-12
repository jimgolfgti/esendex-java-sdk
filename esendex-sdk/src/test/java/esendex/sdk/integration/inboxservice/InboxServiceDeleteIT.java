package esendex.sdk.integration.inboxservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.model.domain.response.InboxMessageResponse;
import esendex.sdk.service.InboxService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InboxServiceDeleteIT extends BaseTest {

    private static String messageIdToDelete;
    private static InboxMessageCollectionResponse messagesAfterDelete;

    @BeforeClass
    public static void whenDeletingAnInboxMessage() throws EsendexException {

        InboxService inboxService = getFactory().getInboxService();

        InboxMessageCollectionResponse messages = inboxService.getMessages();

        if ( messages.getTotalCount() != 0 )
        {
            messageIdToDelete = messages.getMessages().get(0).getId();
            assertTrue(inboxService.deleteMessage(messageIdToDelete));
        }

        messagesAfterDelete = inboxService.getMessages();
    }

    @Test
    public void thenTheMessageIsNotInTheMessagesRetrievedAfterTheDelete() {

        for (InboxMessageResponse inboxMessageResponse : messagesAfterDelete.getMessages()) {
            assertFalse(messageIdToDelete.equals(inboxMessageResponse.getId()));
        }
    }
}
