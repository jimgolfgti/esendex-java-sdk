
package esendex.sdk.service.resource.contact;

import esendex.sdk.http.HttpQuery;
import esendex.sdk.http.HttpRequestMethod;
import esendex.sdk.model.transfer.contact.ContactCollectionDto;
import esendex.sdk.service.auth.Authenticator;
import esendex.sdk.service.resource.base.XmlResponderResource;

/**
 * This operation will return a ContactCollection response listing  a
 * user's stored contacts in a paged fashion.
 * 
 * @author Mike Whittaker
 */
public class RetrieveContactsResource extends
		XmlResponderResource<ContactCollectionDto> {

	/**
	 * Instantiates a new retrieve contacts resource.
	 * @param auth the authenticator
	 * @param query the query
	 */
	public RetrieveContactsResource(
			Authenticator auth, HttpQuery query) {
		super(auth, null, null, query);
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
		return HttpRequestMethod.GET;
	}

}
