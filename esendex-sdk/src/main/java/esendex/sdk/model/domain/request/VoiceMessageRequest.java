package esendex.sdk.model.domain.request;

import esendex.sdk.model.domain.impl.MessageRequest;
import esendex.sdk.model.types.MessageLanguage;
import esendex.sdk.model.types.MessageType;

public class VoiceMessageRequest extends MessageRequest {

	private MessageLanguage messageLanguage;		
	private Integer retries;

	public VoiceMessageRequest(String to, String body) {
		super(to, body, MessageType.VOICE);
	}

	public MessageLanguage getMessageLanguage() {
		return messageLanguage;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setMessageLanguage(MessageLanguage messageLanguage) {
		this.messageLanguage = messageLanguage;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString() +
			"\nmessageLanguage: " + messageLanguage +
			"\nretries: " + retries;
	}
	
}
