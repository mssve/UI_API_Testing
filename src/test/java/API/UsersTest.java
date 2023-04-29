package API;

import POJO.UserPojo;
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

		List<UserPojo> usersDB = DBController.getAllUsers()
				.stream()
				.sorted(Comparator.comparingInt(UserPojo::getId))
				.toList();

		usersDB.forEach(System.out::println);

//		TODO: release server for API requests
//		List<UserPojo> usersAPI = given()
//				.when()
//				.get("users")
//				.then()
//				.extract()
//				.body()
//				.jsonPath()
//				.getList(".", UserPojo.class)
//				.stream()
//				.sorted((Comparator.comparingInt(UserPojo::getId)))
//				.toList();
//
//
//		for (int i = 0; i < usersDB.size(); i++) {
//			UserPojo userDB = usersDB.get(i);
//			UserPojo userAPI = usersAPI.get(i);
//			if (!userDB.equals(userAPI)) {
//				result.assertEquals(userAPI.getId(), userDB.getId(), "User ID: " + userDB.getId());
//				result.assertEquals(userAPI.getName(), userDB.getName(), "User ID: " + userDB.getId());
//				result.assertEquals(userAPI.getAge(), userDB.getAge(), "User ID: " + userDB.getId());
//				result.assertEquals(userAPI.getDescription(), userDB.getDescription(), "User ID: " + userDB.getId());
//				result.assertEquals(userAPI.getCreatedDate(), userDB.getCreatedDate(), "User ID: " + userDB.getId());
//				result.assertEquals(userAPI.getUpdatedDate(), userDB.getUpdatedDate(), "User ID: " + userDB.getId());
//			}
//		}
//
//		result.assertAll();
	}

	@Parameters("testing user id")
	@Test
	public void checkSingleUserData(String userID) {
		new SpecificationsBuilder()
				.requestSpec()
				.responseSpec200()
				.build();
		int intUserID = Integer.parseInt(userID);

		UserPojo userAPI = given()
				.when()
				.get("user/" + intUserID)
				.then()
				.extract()
				.body()
				.as(UserPojo.class);

		UserPojo userDB = DBController.getUserByID(intUserID);

		if (!userDB.equals(userAPI)) {
			assertEquals(userAPI.getId(), userDB.getId(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getName(), userDB.getName(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getAge(), userDB.getAge(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getDescription(), userDB.getDescription(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getCreatedDate(), userDB.getCreatedDate(), "User ID: " + userDB.getId());
			assertEquals(userAPI.getUpdatedDate(), userDB.getUpdatedDate(), "User ID: " + userDB.getId());
		}
	}


}
