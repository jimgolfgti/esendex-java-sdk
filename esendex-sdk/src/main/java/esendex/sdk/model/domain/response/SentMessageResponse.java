package esendex.sdk.model.domain.response;

import java.util.Date;

import esendex.sdk.model.domain.impl.MessageResponse;

public interface SentMessageResponse extends MessageResponse {
	
	public Date getSentat();

	public Date getDeliveredAt();

	public Date getLastStatusAt();

	public Date getSubmittedAt();

	public String getUserName();
}
