
package esendex.sdk.model.domain.request;

import esendex.sdk.model.domain.impl.MessageRequest;
import esendex.sdk.model.types.MessageType;

public class SmsMessageRequest extends MessageRequest {

	/**
	 * 
	 * @param to
	 * @param body
	 */
	public SmsMessageRequest(String to, String body) {
		super(to, body, MessageType.SMS);
	}

	
	
}
