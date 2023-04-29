package db;

import POJO.UserPojo;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.*;
import java.sql.*;

public class DBController {

    private static final Config conf = ConfigFactory.parseResources("db.conf");

    private static final String pgUrl;
    private static final String user;
    private static final String password;

    static {
        pgUrl = conf.getString("DB_URL");
        user = conf.getString("DB_USER");
        password = conf.getString("DB_PASSWORD");
    }

    public static List<UserPojo> getAllUsers() {
        ResultSet rs = sendSQL("SELECT * from public.users");
        System.out.println(rs.toString());
        List<UserPojo> users = new ArrayList<>();
        try {
            while (rs.next()) {
                UserPojo user = collectUserFields(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserPojo getUserByID(int id) {
        ResultSet rs = sendSQL("SELECT * from users where id = " + id);
        UserPojo user;
        try {
            rs.next();
            user = collectUserFields(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    private static ResultSet sendSQL(String query) {
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

    private static UserPojo collectUserFields(ResultSet rs) throws SQLException {
        return new UserPojo(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("description"),
                rs.getDate("created_date"),
                rs.getDate("updated_date")
        );
    }
}
