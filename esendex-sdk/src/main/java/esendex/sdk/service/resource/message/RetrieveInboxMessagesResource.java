
package esendex.sdk.service.resource.message;

import esendex.sdk.http.HttpQuery;
import esendex.sdk.http.HttpRequestMethod;
import esendex.sdk.model.transfer.message.MessageCollectionResponseDto;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.base.XmlResponderResource;

/**
 * This operation will return a paged response of all inbound messages to any
 * Account that the user has permissions to view.
 * 
 * @author Mike Whittaker
 */
public class RetrieveInboxMessagesResource 
		extends XmlResponderResource<MessageCollectionResponseDto> {

	/**
	 * Instantiates a new retrieve inbox messages resource.
	 * @param auth the authenticator
	 * @param query the query
	 */
	public RetrieveInboxMessagesResource(
			Authenticator auth, HttpQuery query) {
		
		super(auth, null, null, query);
	}

	/**
	 * Instantiates a new retrieve inbox messages resource.
	 * @param auth the authenticator
	 */
	 public RetrieveInboxMessagesResource(
			 Authenticator auth) {
		 this(auth, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEndpointChild() {

		return "inbox/messages";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpRequestMethod getRequestMethod() {

		return HttpRequestMethod.GET;
	}

}
