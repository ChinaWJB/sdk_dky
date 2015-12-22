package org.caeit.cs.api.security;

import org.codehaus.jackson.JsonNode;

public class OAuthAgent {
	
	OAuthService service = new OAuthService();

	public JsonNode getUserInfo(String clientId, String username, String password){
		return service.getUserInfo(clientId, username, password);
	}

}
