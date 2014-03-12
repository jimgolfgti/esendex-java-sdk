
package esendex.sdk.service.resource.contact;

import esendex.sdk.http.HttpRequestMethod;
import esendex.sdk.model.transfer.contact.ContactCollectionDto;
import esendex.sdk.model.transfer.contact.ContactDto;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.base.XmlRequesterResponderResource;

/**
 * This operation will add a new Contact based on the request submitted and
 * return a Contact response.
 * @author Mike Whittaker
 */
public class CreateContactResource extends XmlRequesterResponderResource<ContactCollectionDto, ContactDto> {

	/**
	 * Instantiates a new creates the contact resource.
	 * @param auth the authenticator
	 */
	public CreateContactResource(Authenticator auth) {
		super(auth, null, null, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEndpointChild() {
		return "contacts";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpRequestMethod getRequestMethod() {
		return HttpRequestMethod.POST;
	}

	
}
