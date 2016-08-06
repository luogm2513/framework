package controllers;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.junit.Test;

public class UserControllerTest {
	
	@Test
	public void getUser() {
		try{
			HttpClient httpClient = new HttpClient();
			GetMethod get = new GetMethod("http://127.0.0.1:8080/account/user/get");
			get.setRequestHeader("access_token", "123456");
			HttpMethodParams params = new HttpMethodParams();
			params.setParameter("userId", 1L);
			get.setParams(params);
			int result = httpClient.executeMethod(get);
			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(get.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void login() {
		try{
			HttpClient httpClient = new HttpClient();
			PostMethod post = new PostMethod("http://127.0.0.1:8080/account/user/login");
			post.setRequestHeader("user_login_id", "18767122513");
			JSONObject json = new JSONObject();
			json.put("loginId", "18767122513");
			json.put("password", "123456");
			post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "utf-8"));
			int result = httpClient.executeMethod(post);
			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(post.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
