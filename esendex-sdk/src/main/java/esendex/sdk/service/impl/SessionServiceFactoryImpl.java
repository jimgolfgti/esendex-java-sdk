
package esendex.sdk.service.impl;

import esendex.sdk.EsendexException;
import esendex.sdk.ServiceFactory;
import esendex.sdk.service.SessionServiceFactory;
import esendex.sdk.service.auth.SessionAuthenticator;
import esendex.sdk.service.auth.UserPassword;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionServiceFactoryImpl.
 */
public class SessionServiceFactoryImpl extends ServiceFactory implements SessionServiceFactory {

	/**
	 * Instantiates a new session service factory impl.
	 * @param userPassword the user password
	 * @throws EsendexException the esendex exception
	 */
	public SessionServiceFactoryImpl(UserPassword userPassword) throws EsendexException {
		super(new SessionAuthenticator(userPassword));
	}

}
