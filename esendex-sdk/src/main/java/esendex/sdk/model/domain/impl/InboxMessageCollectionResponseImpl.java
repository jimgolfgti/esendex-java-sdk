package esendex.sdk.model.domain.impl;

import java.util.List;

import esendex.sdk.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.model.domain.response.InboxMessageResponse;


public class InboxMessageCollectionResponseImpl 
		extends MessageCollectionResponseImpl<InboxMessageResponse> 
		implements InboxMessageCollectionResponse {

	public InboxMessageCollectionResponseImpl(List<InboxMessageResponse> messages) {
		super(messages);
	}
	
}
