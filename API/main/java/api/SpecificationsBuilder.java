package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class SpecificationsBuilder {

	private static final String BASE_URI = "http://77.50.236.203:4880/";

	public RequestSpecification requestSpecification;
	public ResponseSpecification responseSpecification;

	public SpecificationsBuilder requestSpec() {
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(BASE_URI)
				.setContentType(ContentType.JSON)
				.build();
		return this;
	}

	public SpecificationsBuilder responseSpec200() {
		responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		return this;
	}

	public SpecificationsBuilder build() {
		RestAssured.requestSpecification = this.requestSpecification;
		RestAssured.responseSpecification = this.responseSpecification;
		return this;
	}


}
