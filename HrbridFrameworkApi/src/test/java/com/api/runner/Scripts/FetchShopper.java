package com.api.runner.Scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import com.generic.testcase.Spec_Builder_Body;
import com.utils.testcase.Property;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FetchShopper
{
	@Test
	public void fetch() throws IOException
	{

		Property p=new Property();
		Object url = p.get("baseurl");
		Object  fetch= p.get("Get_Endpoint");
		Object content = p.get("content_type");
		Object email = p.get("email");
		Object userId = p.get("userId");
		String token = p.get("jwtToken");


		Spec_Builder_Body spec=new Spec_Builder_Body();
		RequestSpecification req = spec.request(""+url+"","",""+content+"");
		ResponseSpecification res = spec.response(200, "HTTP/1.1 200 ", ""+content+"", "message", "OK", 5000l);

		Response response = RestAssured.given().spec(req).when().auth().oauth2(token).post("/shoppers/"+""+userId+"").then().spec(res).extract().response();
		String result = response.asPrettyString();
		System.out.println(result);
		JsonPath jp=new JsonPath(result);


	}

}
