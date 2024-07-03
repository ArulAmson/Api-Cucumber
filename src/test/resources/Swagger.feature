Feature: Pet Store API

  Scenario: Add a new pet to the store
    Given the base URI is set to "https://petstore.swagger.io/"
    And The request headers are set and Validate
    And The pet details are set with category "java", name "Selenium", photo URLs "Mavenn", "FrameWork", and tags "Python", "Ruby"
    When I send a POST request to "v2/pet"
    Then The response status code should be 200
    And The response should contain the pet details
