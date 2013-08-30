package esendex.sdk.java.unit.parser;

import esendex.sdk.java.EsendexException;
import esendex.sdk.java.model.transfer.message.MessageCollectionRequestDto;
import esendex.sdk.java.model.transfer.message.MessageCollectionResponseDto;
import esendex.sdk.java.model.transfer.message.MessageRequestDto;
import esendex.sdk.java.model.transfer.message.MessageResponseDto;
import esendex.sdk.java.parser.xstream.XStreamParser;
import org.junit.Before;
import org.junit.Test;

import esendex.sdk.java.BaseTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class XStreamParserTests extends BaseTest {

    XStreamParser xStream;

    @Before
    public void setUpXStreamParser() {

        xStream = new XStreamParser();
    }

    @Test
    public void testWhenTurningMessageRequestDtoToXmlThenExpectedXmlIsReturned() {

        List<MessageRequestDto> messages = new ArrayList<MessageRequestDto>();
        MessageRequestDto messageRequestDto = new MessageRequestDto();
        String body = "Message Body";
        String to = "44231876546";
        messageRequestDto.setBody(body);
        messageRequestDto.setTo(to);
        messages.add(messageRequestDto);

        MessageCollectionRequestDto dto = new MessageCollectionRequestDto(messages);
        dto.setSendat(new GregorianCalendar(1991, 10, 05).getTime());

        String xml = xStream.toXml(dto);

        System.out.println(xml);

        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<messages xmlns=\"http://api.esendex.com/ns/\">" +
                "<sendat>1991-11-05T00:00:00Z</sendat>" +
                "<message>" +
                "<to>" + to + "</to>" +
                "<body>" + body + "</body>" +
                "</message>" +
                "</messages>";

        assertEquals(expectedXml, xml);
    }

    @Test
    public void testWhenCreatingMessageRequestDtoFromXmlThenExpectedBodyIsReturned()
        throws EsendexException {

        String expectedId = "01234567-1234-4567-9142-145236487951";
        String expectedUri = "https://api.esendex.com/v1.0/messageheaders/01234567-1234-4567-9142-145236487951";
        String expectedReference = "EX000XXXX";

        String expectedStatus = "Delivered";
        String deliveredAt = "2013-08-28T12:30:00Z";

        String sentAt = "2013-08-28T12:30:46.673Z";
        String lastStatusAt = "2013-08-28T12:30:00Z";
        String submittedAt = "2013-08-28T12:30:47.197Z";
        String type = "SMS";
        String toNumber = "497700900123";
        String expectedSummary = "This is an SMS message";
        String expectedBodyUri = "https://api.esendex.com/v1.0/messageheaders/01234567-1234-4567-9142-145236487951/body";
        String direction = "Outbound";
        String expectedUser = "user@company.com";

        int startIndex = 0;
        int count = 20;
        int totalCount = 234181;
        Integer parts = 1;

        String fromPhone = "fromPhoneAd";
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<messageheaders xmlns=\"http://api.esendex.com/ns/\" startindex=\"" + startIndex + "\" count=\"" + count + "\" totalcount=\"" + totalCount + "\">\n" +
                "<messageheader id=\"" + expectedId + "\" uri=\"" + expectedUri + "\">\n" +
                "<reference>" + expectedReference + "</reference>\n" +
                "<status>" + expectedStatus + "</status>\n" +
                "<deliveredat>" + deliveredAt + "</deliveredat>\n" +
                "<sentat>" + sentAt + "</sentat>\n" +
                "<laststatusat>" + lastStatusAt + "</laststatusat>\n" +
                "<submittedat>" + submittedAt + "</submittedat>\n" +
                "<type>" + type + "</type>\n" +
                "<to>\n" +
                "<phonenumber>" + toNumber + "</phonenumber>\n" +
                "</to>\n" +
                "<from>\n" +
                "<phonenumber>" + fromPhone + "</phonenumber>\n" +
                "</from>\n" +
                "<summary>" + expectedSummary + "</summary>\n" +
                "<body id=\"" + expectedId + "\" uri=\"" + expectedBodyUri + "\"/>\n" +
                "<direction>" + direction + "</direction>\n" +
                "<parts>" + parts + "</parts>\n" +
                "<username>" + expectedUser + "</username>\n" +
                "</messageheader>" +
                "</messageheaders>";

        MessageCollectionResponseDto dto = (MessageCollectionResponseDto)xStream.fromXml(xml);

        //assertEquals(expectedId, dto.getBatchid());
        assertEquals((int)dto.getCount(), count);
        assertEquals((int)dto.getTotalcount(), totalCount);
        //assertEquals(expectedUri, dto.getUri());



        MessageResponseDto messageheader = dto.getMessageheaders().get(0);

        assertEquals(expectedId, messageheader.getId());
        assertEquals(expectedUri, messageheader.getUri());
        assertEquals(expectedReference, messageheader.getReference());
        assertEquals(expectedStatus, messageheader.getStatus().toString());
        assertEquals(deliveredAt, utcFormatter.format(messageheader.getDeliveredat()));
        assertEquals(sentAt, utcFormatter.format(messageheader.getSentat()));
        assertEquals(lastStatusAt, utcFormatter.format(messageheader.getLaststatusat()));
        assertEquals(type, messageheader.getType().toString());
        assertEquals(toNumber, messageheader.getTo().toString());
        assertEquals(fromPhone, messageheader.getFrom().toString());
        assertEquals(expectedSummary, messageheader.getSummary());
        assertEquals(expectedId, messageheader.getBody().getId());
        assertEquals(expectedBodyUri, messageheader.getBody().getUri());
        assertEquals(direction, messageheader.getDirection().toString());
        assertEquals(parts, messageheader.getParts());
        assertEquals(expectedUser, messageheader.getUsername());
    }

    public String formatDate(Date someDate){
        SimpleDateFormat utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return utcFormatter.format(someDate);
    }
}
	
	
