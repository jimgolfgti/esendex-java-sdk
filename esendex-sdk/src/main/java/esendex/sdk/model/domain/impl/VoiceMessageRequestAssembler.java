package esendex.sdk.model.domain.impl;

import esendex.sdk.model.domain.request.VoiceMessageRequest;
import esendex.sdk.model.transfer.message.MessageRequestDto;

public class VoiceMessageRequestAssembler extends MessageRequestAssembler {
	
	private VoiceMessageRequest request;
	
	public VoiceMessageRequestAssembler(VoiceMessageRequest request) {
		super(request);
		this.request = request;
	}
	
	public MessageRequestDto createRequest() {		
		MessageRequestDto dto = new MessageRequestDto();		
		populateRequest(dto);		
		return dto;
	}
	
	@Override
	protected void populateRequest(MessageRequestDto dto) {
		super.populateRequest(dto);
		dto.setRetries(request.getRetries());
		dto.setLang(request.getMessageLanguage());
	}
	
}
