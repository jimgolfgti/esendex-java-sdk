package esendex.sdk.service.impl;

import esendex.sdk.service.auth.Authenticator;

/**
 * An AbstractService provides the base for all Services, holding 
 * the Authenticator
 */
public class AbstractService {

	protected Authenticator authenticator;

	/**
	 * Instantiates a new abstract service.
	 * @param authenticator the authenticator
	 */
	public AbstractService(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

}
