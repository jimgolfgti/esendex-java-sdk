
package esendex.sdk.service.resource.contact;

import esendex.sdk.http.HttpRequestMethod;
import esendex.sdk.model.transfer.contact.ContactCollectionDto;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.base.XmlRequesterResource;

/**
 * This operation allows the creation of multiple Contacts with one request POST
 * to the Contacts resource. There is only an HTTP Status Code result from this
 * operation.
 * 
 * @author Mike Whittaker
 */
public class CreateContactsResource extends XmlRequesterResource<ContactCollectionDto> {

	/**
	 * Instantiates a new creates the contacts resource.
	 * @param auth the authenticator
	 */
	public CreateContactsResource(Authenticator auth) {
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
