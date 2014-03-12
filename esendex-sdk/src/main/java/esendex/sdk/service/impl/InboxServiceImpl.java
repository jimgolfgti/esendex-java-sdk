
package esendex.sdk.service.impl;

import esendex.sdk.EsendexException;
import esendex.sdk.http.HttpQuery;
import esendex.sdk.model.domain.impl.InboxMessageCollectionResponseAssembler;
import esendex.sdk.model.domain.impl.InboxMessageResponseAssembler;
import esendex.sdk.model.domain.response.InboxMessageCollectionResponse;
import esendex.sdk.model.domain.response.InboxMessageResponse;
import esendex.sdk.service.InboxService;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.message.DeleteInboxMessageResource;
import esendex.sdk.service.resource.message.RetrieveInboxMessagesForOneAccountResource;
import esendex.sdk.service.resource.message.RetrieveInboxMessagesResource;
import esendex.sdk.service.resource.message.RetrieveIndividualMessageResource;
import esendex.sdk.service.resource.message.UpdateMessageStatusResource;

/**
 * The Class InboxServiceImpl.
 * @author Mike Whittaker
 */
public class InboxServiceImpl extends AbstractService implements InboxService {
	
	/**
	 * Instantiates a new inbox service
	 * @param authenticator the authenticator
	 */
	public InboxServiceImpl(Authenticator authenticator) {
		super(authenticator);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteMessage(String id) throws EsendexException {
		
		DeleteInboxMessageResource resource = 
			new DeleteInboxMessageResource(authenticator, id);
		resource.execute();
		return resource.isHttpOkay();
	}

	/**
	 * {@inheritDoc}
	 */
	public InboxMessageResponse getMessage(String id) throws EsendexException {
		
		RetrieveIndividualMessageResource resource = 
			new RetrieveIndividualMessageResource(authenticator, id);
		
		resource.execute();
		
		return new InboxMessageResponseAssembler(
				resource.getResponseObject()).createResponse();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public InboxMessageCollectionResponse getMessages() throws EsendexException {
		
		RetrieveInboxMessagesResource resource = 
			new RetrieveInboxMessagesResource(authenticator);
		
		resource.execute();
		
		return new InboxMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public InboxMessageCollectionResponse getMessages(
			int pageNumber, int pageSize) throws EsendexException {

		HttpQuery query = new HttpQuery();
		query.addParameter(HttpQuery.START_INDEX, pageNumber);
		query.addParameter(HttpQuery.COUNT, pageSize);

		RetrieveInboxMessagesResource resource = 
			new RetrieveInboxMessagesResource(authenticator, query);
		
		resource.execute();
		
		return new InboxMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public InboxMessageCollectionResponse getMessages(
			String accountReference) throws EsendexException {
		
		RetrieveInboxMessagesForOneAccountResource resource = 
			new RetrieveInboxMessagesForOneAccountResource(
				authenticator, accountReference);
		
		resource.execute();
		
		return new InboxMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public InboxMessageCollectionResponse getMessages(String accountReference, 
			int pageNumber, int pageSize) throws EsendexException {

		HttpQuery query = new HttpQuery();
		query.addParameter(HttpQuery.START_INDEX, pageNumber);
		query.addParameter(HttpQuery.COUNT, pageSize);

		RetrieveInboxMessagesForOneAccountResource resource = 
			new RetrieveInboxMessagesForOneAccountResource(
				authenticator, accountReference, query);
		
		resource.execute();
		
		return new InboxMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean markMessageAsRead(String id) throws EsendexException {

		return markMessageAs(id, HttpQuery.Action.READ);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean markMessageAsUnread(String id) throws EsendexException {

		return markMessageAs(id, HttpQuery.Action.UNREAD);
	}
	
	private boolean markMessageAs(String id, 
			HttpQuery.Action action) throws EsendexException {

		HttpQuery query = new HttpQuery();
		query.addParameter(HttpQuery.ACTION, action);
		UpdateMessageStatusResource resource = new UpdateMessageStatusResource(
				authenticator, id, query);
		resource.execute();
		return resource.isHttpOkay();
	}

}
