package com.api.runner.Scripts;

import java.io.IOException;

import org.apache.http.auth.AUTH;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generic.testcase.Spec_Builder_Body;
import com.pojo.testcase.LoginPojo;
import com.utils.testcase.Property;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class loginShopper 
{
	@Test
	public void login() throws IOException
	{
		Property p=new Property();
		Object url = p.get("baseurl");
		Object endlogin = p.get("login_Endpoint");
		Object content = p.get("content_type");
		Object email = p.get("email");
		
		
		LoginPojo lp=new LoginPojo();
		lp.setEmail(""+email+"");
		lp.setPassword("Dhiru@23");
		lp.setRole("SHOPPER");
		
		ObjectMapper obj=new ObjectMapper();
		String jsonbody = obj.writeValueAsString(lp);
		
		Spec_Builder_Body spec=new Spec_Builder_Body();
		RequestSpecification req = spec.request(""+url+"", jsonbody, ""+content+"");
		ResponseSpecification res = spec.response(200, "HTTP/1.1 200 ", ""+content+"", "message", "OK", 7000l);
		 
		Response response = RestAssured.given().spec(req).when().post(""+endlogin+"").then().spec(res).extract().response();
		String result = response.asPrettyString();
		System.out.println(result);
		JsonPath jp=new JsonPath(result);
		
		Object bearer = jp.get("data.jwtToken");
		p.store("jwtToken", ""+bearer+"");
		
		

	}
}