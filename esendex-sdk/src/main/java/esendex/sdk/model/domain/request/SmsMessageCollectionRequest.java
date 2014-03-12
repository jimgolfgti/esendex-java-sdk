package esendex.sdk.model.domain.request;

import java.util.List;

import esendex.sdk.model.domain.impl.MessageCollectionRequest;
import esendex.sdk.model.types.MessageType;

public class SmsMessageCollectionRequest extends MessageCollectionRequest<SmsMessageRequest> {

	public SmsMessageCollectionRequest(String account,
			List<SmsMessageRequest> messages) {
		super(account, messages, MessageType.SMS);
	}

	public SmsMessageCollectionRequest(
			String account, SmsMessageRequest message) {
		super(account, message, MessageType.SMS);
	}
	
	
}
