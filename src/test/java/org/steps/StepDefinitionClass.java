package org.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.swagger.Category;
import org.swagger.Root;
import org.swagger.Tags;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitionClass {
	private RequestSpecification requestSpecification;
	private Response response;

	@Given("the base URI is set to {string}")
	public void theBaseURIIsSetTo(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	@And("the request headers are set and Validate")
	public void theRequestHeadersAreSet() {
		requestSpecification = RestAssured.given();
		requestSpecification.header("accept", "application/json");
		requestSpecification.header("Content-Type", "application/json");
	}

	@And("the pet details are set with category {string}, name {string}, photo URLs {string}, {string}, and tags {string}, {string}")
	public void thePetDetailsAreSet(String category, String name, String photoUrl1, String photoUrl2, String tag1,
			String tag2) throws JsonProcessingException {
		Category categoryObj = new Category(0, category);

		List<String> photoUrls = new ArrayList<>();
		photoUrls.add(photoUrl1);
		photoUrls.add(photoUrl2);

		List<Tags> tags = new ArrayList<>();
		tags.add(new Tags(0, tag1));
		tags.add(new Tags(1, tag2));

		Root root = new Root(0, categoryObj, name, photoUrls, tags, "Available");

		
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(root);
			requestSpecification.body(jsonString);
		
	}

	@When("I send a POST request to {string}")
	public void iSendAPostRequestTo(String endpoint) {
		response = requestSpecification.post(endpoint);
	}

	@Then("the response status code should be {int}")
	public void theResponseStatusCodeShouldBe(int statusCode) {
		Assert.assertEquals(statusCode, response.getStatusCode());
	}

	@And("the response should contain the pet details")
	public void theResponseShouldContainThePetDetails() throws JsonMappingException, JsonProcessingException {
		
			ObjectMapper objectMapper = new ObjectMapper();
			Root deserializedRoot = objectMapper.readValue(response.getBody().asString(), Root.class);
			System.out.println("ID: " + deserializedRoot.getId());
			System.out.println("Name: " + deserializedRoot.getName());
			System.out.println("Status: " + deserializedRoot.getStatus());
		
	}
}
