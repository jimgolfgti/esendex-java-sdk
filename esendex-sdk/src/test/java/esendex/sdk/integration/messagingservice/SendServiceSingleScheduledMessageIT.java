package esendex.sdk.integration.messagingservice;

import esendex.sdk.integration.BaseTest;
import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.request.SmsMessageRequest;
import esendex.sdk.model.domain.response.MessageResultResponse;
import esendex.sdk.service.MessagingService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SendServiceSingleScheduledMessageIT extends BaseTest {

    private static MessageResultResponse resultResponse;

    @BeforeClass
    public static void whenSendingASingleScheduledMessage() throws EsendexException {

        MessagingService messagingService = getFactory().getMessagingService();

        resultResponse = messagingService.sendScheduledMessage(ACCOUNT, new SmsMessageRequest(DESTINATION_NUMBER, "Hello World"), new Date());
    }

    @Test
    public void thenABatchIdIsReturned() {
        assertNotNull(resultResponse.getBatchId());
    }

    @Test
    public void thenAMessageHeaderIsReturned() {
        assertTrue(resultResponse.getMessageIds().size() == 1);
        assertNotNull(resultResponse.getMessageIds().get(0));
    }
}

