package GitHub_RestAssured_Project;

import static io.restassured.RestAssured.given;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GitHub_Project {

	RequestSpecification requestSpec;
	String sshKey;
	int keyId;


	@BeforeClass
	public void setUp() {

		requestSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "token ")
				.setBaseUri("https://api.github.com")
				.build();

		sshKey = "ssh-rsa xyz";
		
	}

	@Test(priority=1)
	public void GitPost() {
		String reqBody = "{\"title\": \"TestKey\",\"key\": \""+sshKey+"\"}"; 
		Response response = given().spec(requestSpec)
				.body(reqBody).when().post("/user/keys");

		System.out.println("POST GIT Response--> "+response.getBody().asPrettyString());
		Reporter.log("POST GIT Response--> "+response.getBody().asPrettyString());

		keyId = response.then().extract().path("id");

		System.out.println("SSH Key Id--> "+keyId);
		Reporter.log("SSH Key Id--> "+keyId);
		response.then().statusCode(201);
	}

	@Test(priority=2)
	public void GitGet() {
		Response response = given().spec(requestSpec)
				.when().get("/user/keys");

		System.out.println("GET GIT Response--> "+response.getBody().asPrettyString());
		Reporter.log("GET GIT Response--> "+response.getBody().asPrettyString());
		response.then().statusCode(200);
	}

	@Test(priority=3)
	public void GitDelete() {
		Response response = 
				given().spec(requestSpec)
				.when().pathParam("keyId", keyId)
				.when().delete("/user/keys/{keyId}");

		System.out.println("DELETE GIT Response--> "+response.getBody().asPrettyString());
		Reporter.log("DELETE GIT Response--> "+response.getBody().asPrettyString());
		response.then().statusCode(204);
	}
}
