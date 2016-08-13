package controllers;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.junit.Test;

public class UserControllerTest {
	
	@Test
	public void getUser() {
		try{
			HttpClient httpClient = new HttpClient();
			GetMethod get = new GetMethod("http://127.0.0.1:8080/account/user");
			get.setRequestHeader("access_token", "1f1d3aa3-318e-43ad-83ce-c7246974fd95");
			get.setRequestHeader("user_login_id", "18700000000");
			NameValuePair[] meta_list = new NameValuePair[2];
			meta_list[0] = new NameValuePair("mobile", "18700000000");
			meta_list[1] = new NameValuePair("password", "3");
			get.setQueryString(meta_list);
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
			post.setRequestHeader("user_login_id", "18700000000");
			JSONObject json = new JSONObject();
			json.put("mobile", "18700000000");
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
	
	@Test
	public void register() {
		try{
			HttpClient httpClient = new HttpClient();
			PostMethod post = new PostMethod("http://127.0.0.1:8080/account/user/register");
			JSONObject json = new JSONObject();
			json.put("mobile", "18700000000");
			json.put("password", "123456");
			json.put("userName", "张三");
			json.put("email", "18700000000@163.com");
			json.put("openId", "18700000000+18700000000");
			json.put("paltType", 1);
			post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "utf-8"));
			int result = httpClient.executeMethod(post);
			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(post.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendCheckCode() {
		try{
			HttpClient httpClient = new HttpClient();
			PostMethod post = new PostMethod("http://127.0.0.1:8080/account/user/sendCheckCode");
			JSONObject json = new JSONObject();
			json.put("mobile", "18700000000");
			post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "utf-8"));
			int result = httpClient.executeMethod(post);
			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(post.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkCode() {
		try{
			HttpClient httpClient = new HttpClient();
			PostMethod post = new PostMethod("http://127.0.0.1:8080/account/user/checkCode");
			JSONObject json = new JSONObject();
			json.put("mobile", "18700000000");
			json.put("checkCode", "083451");
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
