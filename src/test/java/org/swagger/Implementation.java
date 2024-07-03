package org.swagger;


import java.util.ArrayList;

import org.baseclass.BaseClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Implementation extends BaseClass {
	@Test
	private void tc01() {
//		requestObject("https://petstore.swagger.io/");
//		//attach the header
//		requestSpecification.header("accept","application/json");
//		requestSpecification.header("Content-Type","application?json");
//		Category category = new Category(0,"java");
//		//Category category = new Category(0,"Java");
//		
//		ArrayList <String> PhotoUrls = new ArrayList();
//		
//		PhotoUrls.add("Mavenn");
//		PhotoUrls.add("FrameWork");
//		
//		ArrayList <Tags> tags = new ArrayList<>();
//		Tags tags2 = new Tags(0,"Python");
//		Tags tags3 = new Tags(1,"Ruby");
//		
//		tags.add(tags2);
//		tags.add(tags3);
//		
//		Root root =new Root(0, category, "Selenium", PhotoUrls, tags ,"Available");
//		
//		requestSpecification.body(root);
//		Response response = responseObject("POST", "v2/pet");
//		getResponseCode(response);
//		getResponseBody(response);
//		
//		Root as =response.as(Root.class);
//		System.out.println(as.getId());
//		System.out.println(as.getName());
//		System.out.println(as.getStatus());
		// Base URI
		RestAssured.baseURI = "https://petstore.swagger.io/";

		// Request Specification
		RequestSpecification requestSpecification = RestAssured.given();

		// Attach the header
		requestSpecification.header("accept", "application/json");
		requestSpecification.header("Content-Type", "application/json");

		// Category object
		Category category = new Category(0, "java");

		// Photo URLs
		ArrayList<String> photoUrls = new ArrayList<>();
		photoUrls.add("Mavenn");
		photoUrls.add("FrameWork");

		// Tags
		ArrayList<Tags> tags = new ArrayList<>();
		Tags tags2 = new Tags(0, "Python");
		Tags tags3 = new Tags(1, "Ruby");
		tags.add(tags2);
		tags.add(tags3);

		// Root object
		Root root = new Root(0, category, "Selenium", photoUrls, tags, "Available");

		// Set body using ObjectMapper
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(root);
			requestSpecification.body(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Send POST request
		Response response = requestSpecification.post("v2/pet");

		// Print response
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());

		// Deserialize response to Root object
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Root deserializedRoot = objectMapper.readValue(response.getBody().asString(), Root.class);
			System.out.println(deserializedRoot.getId());
			System.out.println(deserializedRoot.getName());
			System.out.println(deserializedRoot.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
