Feature: Validating Google Map API's

  @AddPlace
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add plcae payload "<name>" "<address>" "<language>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name          | address               | language   |
      | Janaki Bhavan | GGP Colony ,Sai Vihar | English-IN |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
