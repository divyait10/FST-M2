package Ex_Act;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Activity1 {
	    String ROOT_URI = "https://petstore.swagger.io/v2/pet";

	   
	    @Test(priority=1)
		public void PostNewPet() {
		    String reqBody = "{\"id\": 77235,\"name\": \"myActivity1\", \"status\": \"alive\"}"; 
		    given().contentType(ContentType.JSON)
		    .body(reqBody).when().post(ROOT_URI);
		}
	    
		@Test(priority=2)
		public void GetPostedPet() {
			Response response = 
					given().contentType(ContentType.JSON) 
					.when().pathParam("petId", "77235")
		            .get(ROOT_URI + "/{petId}");
			
			response.then().statusCode(200);
			response.then().body("status", equalTo("alive"));
			response.then().body("id", equalTo(77235));
			response.then().body("name", equalTo("myActivity1"));
		}
		
		@Test(priority=3)
		public void DeletePostedPet() {
		    Response response = 
		        given().contentType(ContentType.JSON)
		        .when().delete(ROOT_URI + "/77235");
		    
		    response.then().body("code", equalTo(200));
		}
}
