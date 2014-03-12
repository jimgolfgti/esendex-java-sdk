package esendex.sdk.service.impl;

import esendex.sdk.EsendexException;
import esendex.sdk.http.HttpQuery;
import esendex.sdk.model.domain.impl.SentMessageCollectionResponseAssembler;
import esendex.sdk.model.domain.impl.SentMessageResponseAssembler;
import esendex.sdk.model.domain.response.SentMessageCollectionResponse;
import esendex.sdk.model.domain.response.SentMessageResponse;
import esendex.sdk.service.SentService;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.message.RetrieveIndividualMessageResource;
import esendex.sdk.service.resource.message.RetrieveSentMessagesResource;

/**
 * A concrete implementation of SentService
 * 
 * @author Mike Whittaker
 */
public class SentServiceImpl extends AbstractService implements SentService {

	/**
	 * Instantiates a new sent service
	 * @param authenticator the authenticator
	 */
	public SentServiceImpl(Authenticator authenticator) {
		super(authenticator);
	}

	/**
	 * {@inheritDoc}
	 */
	public SentMessageResponse getMessage(String id) throws EsendexException {

		RetrieveIndividualMessageResource resource 
			= new RetrieveIndividualMessageResource(authenticator, id);
		resource.execute();
		
		return new SentMessageResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public SentMessageCollectionResponse getMessages(
			int pageNumber, int pageSize) throws EsendexException {
		
		HttpQuery query = new HttpQuery();
		query.addParameter(HttpQuery.START_INDEX, pageNumber);
		query.addParameter(HttpQuery.COUNT, pageSize);

		RetrieveSentMessagesResource resource 
			= new RetrieveSentMessagesResource(authenticator, query);
		resource.execute();
		
		return new SentMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public SentMessageCollectionResponse getMessages(
			String account, int pageNumber, int pageSize)
			throws EsendexException {

		HttpQuery query = new HttpQuery();
		query.addParameter(HttpQuery.START_INDEX, pageNumber);
		query.addParameter(HttpQuery.COUNT, pageSize);
		query.addParameter(HttpQuery.FILTER_BY, HttpQuery.ACCOUNT_FILTER);
		query.addParameter(HttpQuery.FILTER_VALUE, account);

		RetrieveSentMessagesResource resource 
			= new RetrieveSentMessagesResource(authenticator, query);
		
		resource.execute();
		
		return new SentMessageCollectionResponseAssembler(
				resource.getResponseObject()).createResponse();
	}

}
