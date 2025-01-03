package com.generic.testcase;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec_Builder_Body 
{
	@BeforeMethod
	public RequestSpecification request(String url,String body,String  content)
	{
		RequestSpecBuilder spec=new RequestSpecBuilder();
		RequestSpecification req = spec.setBaseUri(url).setBody(body).setContentType(content).build();
		return req;
		
		
	}
	@AfterMethod
	public ResponseSpecification response(int code,String line,String content,String key,String value,long time)
	{
		ResponseSpecBuilder spec=new ResponseSpecBuilder();
		ResponseSpecification res = spec.expectStatusCode(code).
		expectStatusLine(line).
		expectContentType(content).
		expectBody(key,Matchers.equalTo(value)).
		expectResponseTime(Matchers.lessThanOrEqualTo(time)).build();
		return res;
	
	}

}
