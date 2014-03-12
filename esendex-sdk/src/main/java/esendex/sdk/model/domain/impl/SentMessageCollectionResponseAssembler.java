package esendex.sdk.model.domain.impl;

import java.util.ArrayList;
import java.util.List;

import esendex.sdk.model.domain.response.SentMessageCollectionResponse;
import esendex.sdk.model.domain.response.SentMessageResponse;
import esendex.sdk.model.transfer.message.MessageCollectionResponseDto;
import esendex.sdk.model.transfer.message.MessageResponseDto;

public class SentMessageCollectionResponseAssembler extends MessageCollectionResponseAssembler {

	public SentMessageCollectionResponseAssembler(MessageCollectionResponseDto dto) {
		super(dto);
	}
	
	public SentMessageCollectionResponse createResponse() {
		
		List<SentMessageResponse> messages = new ArrayList<SentMessageResponse>();
		for (MessageResponseDto dto : collection) {
			SentMessageResponseAssembler assembler = new SentMessageResponseAssembler(dto);
			messages.add(assembler.createResponse());
		}
		
		SentMessageCollectionResponseImpl response = new SentMessageCollectionResponseImpl(messages);
		populateResponse(response);
		return response;
	}

	protected void populateResponse(SentMessageCollectionResponseImpl response) {
		super.populateResponse(response);
	}
}
