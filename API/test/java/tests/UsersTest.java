package tests;

import api.Specifications;
import api.APIUserData;
import db.DBController;
import db.DBUserData;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Comparator;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UsersTest {

	@Test(description = "Проверка соответствия данных пользователей с БД")
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
			if (!userDB.equals(userAPI)) {
				result.assertEquals(userAPI.getId(), userDB.getId(), "User ID: " + userDB.getId());
				result.assertEquals(userAPI.getFirstName(), userDB.getFirstName(), "User ID: " + userDB.getId());
				result.assertEquals(userAPI.getSecondName(), userDB.getSecondName(), "User ID: " + userDB.getId());
				result.assertEquals(userAPI.getAge(), userDB.getAge(), "User ID: " + userDB.getId());
				result.assertEquals(userAPI.getSex(), userDB.getSex(), "User ID: " + userDB.getId());
				result.assertEquals(userAPI.getMoney(), userDB.getMoney(), "User ID: " + userDB.getId());
			}
		}

		result.assertAll();
	}

	@Parameters("testing user id")
	@Test
	public void checkSingleUserData(String userID) {
		int intUserID = Integer.parseInt(userID);
		Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec200());

		APIUserData userAPI = given()
				.when()
				.get("user/" + intUserID)
				.then()
				.extract()
				.body()
				.as(APIUserData.class);

		DBUserData userDB = DBController.getUserByID(intUserID);

		if (!userDB.equals(userAPI)) {
			assertEquals(userAPI.getId(), userDB.getId(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getFirstName(), userDB.getFirstName(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getSecondName(), userDB.getSecondName(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getAge(), userDB.getAge(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getSex(), userDB.getSex(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getMoney(), userDB.getMoney(), "User ID: " + userDB.getId());
		}
	}


}
