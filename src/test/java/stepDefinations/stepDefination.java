package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

public class stepDefination extends Utilities {
	RequestSpecification req;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add plcae payload {string} {string} {string}")
	public void add_plcae_payload(String name, String address, String language) throws IOException {
		res = given().spec(RequestSpecificationDemo()).body(data.addPlacePayload(name, address, language));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//Constructor will be called with value of resource which you pass
		APIResources ResourcesAPI = APIResources.valueOf(resource);
		System.out.println(ResourcesAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(ResourcesAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().post(ResourcesAPI.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		
		assertEquals(getJsonPath(response,keyValue),ExpectedValue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		res = given().spec(RequestSpecificationDemo()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res= given().spec(RequestSpecificationDemo()).body(data.deletePlacePayload(place_id));
	}

}
