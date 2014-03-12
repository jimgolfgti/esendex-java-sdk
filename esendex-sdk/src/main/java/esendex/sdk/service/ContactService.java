
package esendex.sdk.service;

import java.util.List;

import esendex.sdk.EsendexException;
import esendex.sdk.model.domain.request.ContactRequest;
import esendex.sdk.model.domain.response.ContactResponse;

/**
 * A ContactService is a service for creating, retrieving, updating and deleting
 * contact resources. All methods throw an EsendexException to indicate either 
 * a problem during the underlying connection or there was a none okay response. 
 * 
 * @author Mike Whittaker
 */
public interface ContactService {

	ContactResponse createContact(ContactRequest contactRequest) throws EsendexException;

	boolean createContacts(List<ContactRequest> contactRequests) throws EsendexException;

	ContactResponse getContact(String id) throws EsendexException;

	List<ContactResponse> getContacts(int pageNumber, int pageSize) throws EsendexException;

	ContactResponse updateContact(String id, ContactRequest contactRequest) throws EsendexException;

	boolean deleteContact(String id) throws EsendexException;

}