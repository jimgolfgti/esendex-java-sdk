package esendex.sdk.integration.inboxservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.model.domain.response.InboxMessageResponse;
import esendex.sdk.service.InboxService;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InboxServiceGetPagedMessagesIT extends BaseTest {

    private static InboxMessageCollectionResponse messages;
    private static int pageSize;

    @BeforeClass
    public static void whenRetrievingPagedInboxMessages() throws EsendexException {

        InboxService inboxService = getFactory().getInboxService();

        pageSize = 5;
        messages = inboxService.getMessages(0, pageSize);
    }

    @Test
    public void thenTheTotalCountIsGreaterThanOrEqualToTheNumberOfMessagesReturned() {
        assertTrue(messages.getCount() <= messages.getTotalCount());
    }

    @Test
    public void thenNoMoreThanThePageSizeMessagesAreReturned() {
        assertTrue(messages.getCount() <= pageSize);
    }

    @Test
    public void thenAllTheReturnedMessagesHaveIdAndBody() {
        for (InboxMessageResponse inboxMessageResponse : messages.getMessages()) {
            assertNotNull(inboxMessageResponse.getId());
            assertNotNull(inboxMessageResponse.getBody());
        }
    }
}
