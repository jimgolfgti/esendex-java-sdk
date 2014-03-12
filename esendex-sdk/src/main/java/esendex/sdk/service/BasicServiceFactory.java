
package esendex.sdk.service;

import esendex.sdk.service.impl.IServiceFactory;


/**
 * A factory for creating BasicService objects.
 */
public interface BasicServiceFactory extends IServiceFactory {

	SessionService getSessionService();

}