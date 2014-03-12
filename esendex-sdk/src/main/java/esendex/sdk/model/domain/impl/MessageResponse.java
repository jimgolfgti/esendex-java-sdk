package esendex.sdk.model.domain.impl;

import esendex.sdk.model.types.MessageType;
import esendex.sdk.model.types.Status;

public interface MessageResponse {

	String getId();
	
	String getReference();

	Status getStatus();

	MessageType getType();

	MessageContact getTo();

	MessageContact getFrom();

	String getSummary();

	MessageBody getBody();

	Integer getParts();

}