package tests;

import ObjectModels.UserModel;
import api.SpecificationsBuilder;
import db.DBController;
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
		new SpecificationsBuilder()
				.requestSpec()
				.responseSpec200()
				.build();

		SoftAssert result = new SoftAssert();

		List<UserModel> usersAPI = given()
				.when()
				.get("users")
				.then()
				.extract()
				.body()
				.jsonPath()
				.getList(".", UserModel.class)
				.stream()
				.sorted((Comparator.comparingInt(UserModel::getId)))
				.toList();

		List<UserModel> usersDB = DBController.getAllUsers()
				.stream()
				.sorted(Comparator.comparingInt(UserModel::getId))
				.toList();

		for (int i = 0; i < usersDB.size(); i++) {
			UserModel userDB = usersDB.get(i);
			UserModel userAPI = usersAPI.get(i);
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
		new SpecificationsBuilder()
				.requestSpec()
				.responseSpec200()
				.build();
		int intUserID = Integer.parseInt(userID);

		UserModel userAPI = given()
				.when()
				.get("user/" + intUserID)
				.then()
				.extract()
				.body()
				.as(UserModel.class);

		UserModel userDB = DBController.getUserByID(intUserID);

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
