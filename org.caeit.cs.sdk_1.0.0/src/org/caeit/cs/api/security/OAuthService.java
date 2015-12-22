package org.caeit.cs.api.security;


import org.caeit.cs.client.dky.SafetyCertificationClient;
import org.caeit.cs.context.ApplicationContext;
import org.caeit.cs.util.CoreServiceProperties;
import org.codehaus.jackson.JsonNode;

public class OAuthService {
	private static final String URL_PREFIX = "http://";
	private static final String URL_POSTFIX_TOKEN = "/sas/oauth/token";
	private static final String URL_POSTFIX_USER = "/sas/info/user";

	private static final String GRANT_TYPE = "password";
	private static final String SCOPE = "read,write";

	public JsonNode getUserInfo(String clientId, String username,
			String password) throws SecurityException {
		String oAuthTokenUrl = URL_PREFIX
				+ CoreServiceProperties.safetyCertificationIP + ":"
				+ CoreServiceProperties.safetyCertificationPort
				+ URL_POSTFIX_TOKEN + "?" + "client_id=" + clientId
				+ "&grant_type=" + GRANT_TYPE + "&scope=" + SCOPE
				+ "&username=" + username + "&password=" + password;

		String userInfoUrl = URL_PREFIX
				+ CoreServiceProperties.safetyCertificationIP + ":"
				+ CoreServiceProperties.safetyCertificationPort
				+ URL_POSTFIX_USER;

		SafetyCertificationClient client = new SafetyCertificationClient();
		// 判断得到的token是否为空,如果认证出错，则说明这种情况下
		JsonNode token = client.getTokenByPassword(oAuthTokenUrl);
		if (token.get("access_token").getTextValue() == null) {
			throw new SecurityException("用户名或密码错误！");
		} else {
			JsonNode userInfo = client.getUserInfo(userInfoUrl,
					token.get("token_type").getTextValue(),
					token.get("access_token").getTextValue());
			System.out.println("Token:"+token);
			System.out.println("UserInfo:"+userInfo);
			//如果userInfo为空，则表示该用户不具备当前clientid的api使用权限                                                                                            
			if (userInfo == null) {
				throw new SecurityException("该用户不具备当前API使用权限！");
			}
			ApplicationContext context = ApplicationContext.getInstance();
			context.setToken(token);
			context.setUserinfo(userInfo);
			return userInfo;
		}
		
	}

}
