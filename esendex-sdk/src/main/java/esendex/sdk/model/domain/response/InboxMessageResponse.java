package esendex.sdk.model.domain.response;

import java.util.Date;

import esendex.sdk.model.domain.impl.MessageResponse;

public interface InboxMessageResponse extends MessageResponse {
	
	public Date getReceivedAt();

	public Date getReadAt();

	public String getReadBy();
}
