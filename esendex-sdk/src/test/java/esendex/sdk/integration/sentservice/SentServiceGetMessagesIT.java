package esendex.sdk.integration.sentservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.response.SentMessageCollectionResponse;
import esendex.sdk.model.domain.response.SentMessageResponse;
import esendex.sdk.service.SentService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SentServiceGetMessagesIT extends BaseTest {

    private static SentMessageCollectionResponse messages;
    private static int pageSize;

    @BeforeClass
    public static void whenGettingMessagesForTheAccount() throws EsendexException {

        SentService sentService = getFactory().getSentService();

        pageSize = 10;
        messages = sentService.getMessages(ACCOUNT, 0, pageSize);
    }

    @Test
    public void thenNoMoreThanTheSpecifiedNumberOfMessagesReturnedIsLessThanOrEqualToTheTotalCount() {

        assertTrue(messages.getCount() <= messages.getTotalCount());
    }

    @Test
    public void thenTheNumberOfMessagesReturnedIsLessThanOrEqualToTheTotalCount() {

        assertTrue(messages.getCount() <= pageSize);
    }

    @Test
    public void thenEachMessageHasAnIdAndBody() {

        for(SentMessageResponse m : messages.getMessages()) {
            assertNotNull(m.getId());
        }
    }

    @Test
    public void thenAllTheMessagesAreForTheSpecifiedAccount() {

        for(SentMessageResponse m : messages.getMessages()) {
            assertEquals(ACCOUNT, m.getReference());
        }
    }
}
