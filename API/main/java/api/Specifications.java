package api;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

	private static final String BASE_URI = "http://77.50.236.203:4880/";

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(BASE_URI)
				.setContentType(ContentType.JSON)
				.build();
	}

	public static ResponseSpecification responseSpec200() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
	}

	public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
		RestAssured.requestSpecification = requestSpec;
		RestAssured.responseSpecification = responseSpec;
	}


}
