package db;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.*;
import java.sql.*;

public class DBController {

	private static final String pgUrl;
	private static final String user;
	private static final String password;

	static {
		pgUrl = Dotenv.load().get("DB_URL");
		user = Dotenv.load().get("DB_USER");
		password = Dotenv.load().get("DB_PASSWORD");
	}

	public static List<DBUserData> getAllUsers() {
		ResultSet rs = sendSQL("SELECT * from person");
		List<DBUserData> users = new ArrayList<>();
		try {
			while (rs.next()){
				DBUserData user = new DBUserData(
						rs.getInt("id"),
						rs.getInt("age"),
						rs.getLong("money"),
						rs.getString("first_name"),
						rs.getString("second_name"),
						rs.getBoolean("sex")
				);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DBUserData getUserByID(int id) {
		ResultSet rs = sendSQL("SELECT * from person where id = " + id);
		DBUserData user;
		try {
			rs.next();
			user = new DBUserData(
					rs.getInt("id"),
					rs.getInt("age"),
					rs.getLong("money"),
					rs.getString("first_name"),
					rs.getString("second_name"),
					rs.getBoolean("sex")
			);
			return user;
			} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public static ResultSet sendSQL(String query) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(pgUrl, user, password);
			Statement stmt = connection.createStatement();
			return stmt.executeQuery(query);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
