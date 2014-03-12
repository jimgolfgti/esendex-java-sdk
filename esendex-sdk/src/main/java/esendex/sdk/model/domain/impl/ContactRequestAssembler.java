package esendex.sdk.model.domain.impl;

import java.util.ArrayList;
import java.util.List;

import esendex.sdk.model.domain.request.ContactRequest;
import esendex.sdk.model.transfer.contact.ContactCollectionDto;
import esendex.sdk.model.transfer.contact.ContactDto;

public class ContactRequestAssembler {
	
	public ContactDto createRequest(ContactRequest c) {
		
		ContactDto dto = new ContactDto();
		
		dto.setId(c.getId());
		dto.setConcurrencyid(c.getConcurrencyId());
		dto.setFirstname(c.getFirstName());
		dto.setLastname(c.getLastName());
		dto.setQuickname(c.getQuickName());
		dto.setMobilenumber(c.getMobileNumber());
		dto.setType(c.getType());
		
		return dto;
	}

	public ContactCollectionDto createCollection(List<ContactRequest> contacts) {
		
		List<ContactDto> list = new ArrayList<ContactDto>();
		if (contacts != null) {
			for (ContactRequest c : contacts) {
				list.add(createRequest(c));
			}
		}
		return new ContactCollectionDto(list);
	}

	public ContactCollectionDto createCollection(ContactRequest contact) {
		
		List<ContactDto> list = new ArrayList<ContactDto>();
		list.add(createRequest(contact));	
		return new ContactCollectionDto(list);
	}
	
}
