package esendex.sdk.model.domain.impl;

import esendex.sdk.model.domain.response.SessionResponse;
import esendex.sdk.model.transfer.session.SessionDto;

public class SessionResponseAssembler {
	
	public SessionResponse createResponse(SessionDto dto) {
		
		Identity resp = new Identity();
		resp.setId(dto.getId());
		return resp;
	}
	
	
}
