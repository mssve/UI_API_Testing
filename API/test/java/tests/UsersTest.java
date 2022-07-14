package tests;

import api.Specifications;
import api.APIUserData;
import db.DBController;
import db.DBUserData;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersTest {

	@Test (description = "Проверка соответствия данных пользователей с БД")
	public void checkAllUsersData() {
		Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec200());
		SoftAssert result = new SoftAssert();

		List<APIUserData> usersAPI = given()
				.when()
				.get("users")
				.then()
				.extract()
				.body()
				.jsonPath()
				.getList(".", APIUserData.class)
				.stream()
				.sorted((Comparator.comparingInt(APIUserData::getId)))
				.toList();

		List<DBUserData> usersDB = DBController.getAllUsers()
				.stream()
				.sorted(Comparator.comparingInt(DBUserData::getId))
				.toList();

		for (int i = 0; i < usersDB.size(); i++) {
			DBUserData userDB = usersDB.get(i);
			APIUserData userAPI = usersAPI.get(i);
			result.assertTrue(userDB.equals(userAPI), "User with errors: " + userDB.getId());
		}

		result.assertAll();
	}

	@Parameters ("testing user id")
	@Test
	public void checkSingleUserData(String userID) {
		int intUserID = Integer.parseInt(userID);
		Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec200());

		APIUserData userAPI = given()
				.when()
				.get("user/" + intUserID)
				.then()
				.log().all()
				.extract()
				.body()
				.as(APIUserData.class);

		DBUserData userDB = DBController.getUserByID(intUserID);

		Assert.assertTrue(userDB.equals(userAPI));

	}




}
