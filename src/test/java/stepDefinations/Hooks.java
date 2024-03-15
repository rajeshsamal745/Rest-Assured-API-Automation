package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// write a code that will give you place id
		// execute this code only when place id is null
		if (stepDefination.place_id == null) {
			stepDefination step = new stepDefination();
			step.add_plcae_payload("Rajesh", "USA", "German");
			step.user_calls_with_http_request("AddPlaceAPI", "POST");
			step.verify_place_id_created_maps_to_using("Raj", "getPlaceAPI");
		}
	}
}
