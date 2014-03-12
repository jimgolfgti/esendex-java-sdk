
package esendex.sdk.service.impl;

import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.impl.SessionResponseAssembler;
import esendex.sdk.model.domain.response.SessionResponse;
import esendex.sdk.service.SessionService;
import esendex.sdk.service.auth.BasicAuthenticator;
import esendex.sdk.service.resource.access.SessionResource;

/**
 * A concrete SessionService.
 */
public class SessionServiceImpl extends AbstractService implements SessionService {
	
	/**
	 * Instantiates a new session service.
	 * @param authenticator the authenticator
	 */
	public SessionServiceImpl(BasicAuthenticator authenticator) {
		super(authenticator);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SessionResponse getSession() throws EsendexException {
		SessionResource resource = new SessionResource((BasicAuthenticator)authenticator);
		resource.execute();
		return new SessionResponseAssembler().createResponse(
				resource.getResponseObject());
	}

}
