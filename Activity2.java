package Ex_Act;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2 {
	String ROOT_URI = "https://petstore.swagger.io/v2/user";

	@Test(priority=1)
	public void PostNewPet() throws IOException {

		FileInputStream inputJSON = new FileInputStream("src/Ex_Act/Activity2_Input.json");
		String reqBody = new String(inputJSON.readAllBytes());
		inputJSON.close();

		Response response =
				given().contentType(ContentType.JSON)
				.body(reqBody).when().post(ROOT_URI);
		response.then().statusCode(200);
	}

	@Test(priority=2)
	public void GetPostedPet() {
		Response response = 
				given().contentType(ContentType.JSON) 
				.when().pathParam("username", "myActivity2")
				.get(ROOT_URI + "/{username}");

		System.out.println(response.getBody().asPrettyString());

		response.then().statusCode(200);
		response.then().body("firstName", equalTo("Tej"));
		response.then().body("lastName", equalTo("Sn"));
		response.then().body("email", equalTo("tejsn001@mail.com"));
		response.then().body("password", equalTo("tejsn14277"));
		response.then().body("phone", equalTo("1427714277"));
	}

	@Test(priority=3)
	public void DeletePostedPet() {
		Response response = 
				given().contentType(ContentType.JSON) 
				.pathParam("username", "myActivity2") 
				.when().delete(ROOT_URI + "/{username}"); 

		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("myActivity2"));
		
		System.out.println(response.getBody().asPrettyString());
	}
}
