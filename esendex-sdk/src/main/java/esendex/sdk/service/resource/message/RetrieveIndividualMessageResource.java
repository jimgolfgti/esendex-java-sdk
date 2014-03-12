
package esendex.sdk.service.resource.message;

import esendex.sdk.http.HttpRequestMethod;
import esendex.sdk.model.transfer.message.MessageResponseDto;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.base.XmlResponderResource;

/**
 * This operation will return a message header response for an individual 
 * message. This operation will support queries for both inbound 
 * and outbound message ID GUIDs.
 * 
 * @author Mike Whittaker
 */
public class RetrieveIndividualMessageResource 
		extends XmlResponderResource<MessageResponseDto> {

	/**
	 * Instantiates a new retrieve individual message resource.
	 * @param auth the authenticator
	 * @param id the id
	 */
	public RetrieveIndividualMessageResource(
			Authenticator auth, String id) {
		super(auth, null, id, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEndpointChild() {
		return "messageheaders/" + getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpRequestMethod getRequestMethod() {
		return HttpRequestMethod.GET;
	}

}
