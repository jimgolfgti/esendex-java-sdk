package esendex.sdk.model.domain.response;

import java.util.List;

public interface MessageResultResponse {

	String getBatchId();

	List<ResourceLinkResponse> getMessageIds();

}