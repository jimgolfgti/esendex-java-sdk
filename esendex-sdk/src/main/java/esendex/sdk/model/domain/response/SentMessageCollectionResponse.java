package esendex.sdk.model.domain.response;

import java.util.List;

import esendex.sdk.model.domain.impl.Pageable;

public interface SentMessageCollectionResponse extends Pageable {

	List<SentMessageResponse> getMessages();
	
}