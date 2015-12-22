package org.caeit.cs.client.dky;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class SafetyCertificationClient {
	
	//http://60.5.1.21:18080/sas/oauth/token?client_id=DomainManagement&grant_type=password&scope=read,write&username=admin&password=123456
	//{"access_token":"91e53906-6620-4bce-8236-f71aa9e15b7f","token_type":"bearer","refresh_token":"8c7ea5ed-6598-421a-9dcc-80e9dcf71893","expires_in":43199,"scope":"read write"}
	
	public JsonNode getTokenByPassword(String oAuthTokenUrl){
		HttpPost post = new HttpPost(oAuthTokenUrl);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(post);
			String token = EntityUtils.toString(response.getEntity());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(token);
			return jsonNode;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		SafetyCertificationClient client = new SafetyCertificationClient();
		JsonNode token = client.getTokenByPassword("http://192.169.10.2:7080/sas/oauth/token?client_id=DomainManagement&grant_type=password&scope=read,write&username=admin&password=admin");
		System.out.println("token:"+token);
		JsonNode userinfo = client.getUserInfo("http://192.169.10.2:7080/sas/oauth/user", token.get("token_type").getTextValue(), token.get("access_token").getTextValue());
		
		System.out.println("userinfo:"+userinfo);
	}
	
	public JsonNode getUserInfo(String userinfoUrl,String tokenType, String accessToken) {
		HttpPost post = new HttpPost(userinfoUrl);
		post.addHeader("Authorization",
				String.format("%s %s", tokenType, accessToken));
		List<NameValuePair> qp = new ArrayList<NameValuePair>();
		qp.add(new BasicNameValuePair("access_token",accessToken)); 
		try {
//			HttpEntity entity = new UrlEncodedFormEntity(qp);
//			post.setEntity(entity);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(post);
			String info = EntityUtils.toString(response.getEntity());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(info);
			return jsonNode;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
