package com.api.runner.Scripts;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generic.testcase.Framework_Constant;
import com.generic.testcase.Spec_Builder_Body;
import com.pojo.testcase.CreateShopperPojo;
import com.utils.testcase.Property;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ScriptRunnerShopper implements Framework_Constant
{
	@Test
	public void create() throws IOException
	{
		String random = RandomStringUtils.randomAlphanumeric(3);
		String randomemail = "Dhiraj"+random+"@gmail.com";
		CreateShopperPojo shopper=new CreateShopperPojo();
		shopper.setCity("Banglore");
		shopper.setCountry("India");
		shopper.setEmail(randomemail);
		shopper.setFirstName("Kumar");
		shopper.setGender("MALE");
		shopper.setLastName("dhiraj");
		shopper.setPassword("Dhiru@23");
		shopper.setPhone("9128644353");
		shopper.setState("Karnatka");
		shopper.setZoneId("ALPHA");           					//Store the value int the java

		ObjectMapper obj=new ObjectMapper();
		String jsonBody = obj.writeValueAsString(shopper);		//Converting the data into json

		Property p=new Property();     				
		Object url = p.get("baseurl");
		Object end = p.get("ShopperEndpoint");
		Object content = p.get("content_type");					//specbuilder access

		Spec_Builder_Body spec=new Spec_Builder_Body();
		RequestSpecification req = spec.request(""+url+"", jsonBody, ""+content+"");	//pre request
		ResponseSpecification res = spec.response(created, "HTTP/1.1 201 ", ""+content+"", "message", "Created", 5000l);	//post response

		Response resp = RestAssured.given().spec(req).when().post(""+end+"").then().spec(res).extract().response();
		System.out.println(resp.asPrettyString());
		
		
		JsonPath jp=new JsonPath(resp.asPrettyString());		//fetching the data from the response body

		Object id = jp.get("data.userId");
		Object email = jp.get("data.email");

		p.store("userId", ""+id+"");
		p.store("email", ""+email+"");							//Storing the data into propertyfile




	}
	
		
		
		
		
	}
	


