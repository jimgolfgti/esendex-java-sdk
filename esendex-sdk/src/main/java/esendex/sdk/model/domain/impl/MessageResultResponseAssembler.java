package esendex.sdk.model.domain.impl;

import java.util.ArrayList;
import java.util.List;

import esendex.sdk.model.domain.response.MessageResultResponse;
import esendex.sdk.model.domain.response.ResourceLinkResponse;
import esendex.sdk.model.transfer.message.MessageCollectionResponseDto;
import esendex.sdk.model.transfer.message.MessageResponseDto;

public class MessageResultResponseAssembler {
	
	private MessageCollectionResponseDto dto;
	
	public MessageResultResponseAssembler(
			MessageCollectionResponseDto dto) {
		this.dto = dto;
	}
	
	public MessageResultResponse createResponse() {
		
		List<ResourceLinkResponse> ids = new ArrayList<ResourceLinkResponse>();
		for (MessageResponseDto d : dto.getMessageheaders()) {
			Identity ident = new Identity();
			ident.setId(d.getId());
			ident.setUri(d.getUri());
			ids.add(ident);
		}		
		MessageResultResponseImpl response = new MessageResultResponseImpl(ids);	
		response.setBatchId(dto.getBatchid());
		return response;
	}
	
}
