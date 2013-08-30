package esendex.sdk.java.integration.inboxservice;

import esendex.sdk.java.BaseTest;
import esendex.sdk.java.EsendexException;
import esendex.sdk.java.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.java.model.domain.response.InboxMessageResponse;
import esendex.sdk.java.service.InboxService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InboxServiceDeleteTests extends BaseTest {

    private static String messageIdToDelete;
    private static InboxMessageCollectionResponse messagesAfterDelete;

    @BeforeClass
    public static void whenDeletingAnInboxMessage() throws EsendexException {

        InboxService inboxService = getFactory().getInboxService();

        InboxMessageCollectionResponse messages = inboxService.getMessages();

        if ( messages.getTotalCount() != 0 ) {
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
