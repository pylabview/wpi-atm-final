package com.rodrigohacks.atm.daolayer;

import com.rodrigohacks.atm.model.ATMUser;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataAccessLayer class provides methods to interact with the database.
 * It uses dotenv library to load environment variables for database connection.
 */
public class DataAccessLayer {
    String dbUrl, dbUser, dbPassword;
    ResultSet resultSet;

    public DataAccessLayer() {
        Dotenv dotenv = Dotenv.load();

        this.dbUrl = dotenv.get("DB_URL");
        this.dbUser = dotenv.get("DB_USER");
        this.dbPassword = dotenv.get("DB_PASSWORD");
        this.resultSet = null;
    }

    public static void main(String[] args) {
        ATMUser atmUser = new ATMUser(1,
                "Gina",
                "Customer",
                "gina1961",
                "12345",
                4000.23,
                1,
                -1,
                0);

        DataAccessLayer dataAccessLayer = new DataAccessLayer();
        int newUserId = dataAccessLayer.addUserToDatabase(atmUser);
        dataAccessLayer.deleteUserFromDatabase(newUserId);
        System.out.println(dataAccessLayer.dbUrl);
        System.out.println(dataAccessLayer.dbUser);
        System.out.println(dataAccessLayer.dbPassword);
        List<ATMUser> userList = dataAccessLayer.getUsersFromDatabase();

        for (ATMUser user : userList) {
            System.out.println(user.getHolder());
        }
    }

    public List<ATMUser> getUsersFromDatabase() {
        List<ATMUser> userList = new ArrayList<>();
        String query = MySQLQueryStrings.GET_USERS_FROM_DATABASE;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String holder = rs.getString("holder");
                String roleDescription = rs.getString("role_description");
                String userLogin = rs.getString("user_login");
                int currentBalance = rs.getInt("balance");
                int active = rs.getInt("active");
                String userLoginPin = rs.getString("user_login_pin");
                int accountId = rs.getInt("accountId");
                int userType = rs.getInt("userType");

                userList.add(new ATMUser(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active, accountId, userType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    public int addUserToDatabase(ATMUser atmUser) {
        String q1 = MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertQuery");
        System.out.println(q1);


        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             Statement stmt = conn.createStatement()) {

            // Execute SQL query to insert new user
            stmt.executeUpdate(q1);
            // Get the last inserted user ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                atmUser.setId(rs.getInt(1));
            }
            String q2 = MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertRoleQuery");
            // Execute SQL query to insert user role
            stmt.executeUpdate(q2);

            // Execute SQL query to insert user account
            String q3 = MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertAccountQuery");
            // Create a PreparedStatement with the INSERT query and specify that you want to retrieve generated keys
            PreparedStatement stmt1 = conn.prepareStatement(q3, Statement.RETURN_GENERATED_KEYS);

            // Execute the INSERT query
            int affectedRows = stmt1.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows == 1) {
                // Retrieve the generated keys
                ResultSet rs1 = stmt1.getGeneratedKeys();
                if (rs1.next()) {
                    atmUser.setAccountId(rs1.getInt(1)); // Retrieve the generated account ID
                    System.out.println("\"Account Successfully Created – the account number assigned is: " + atmUser.getAccountId());
                } else {
                    System.out.println("Failed to retrieve the generated account ID.");
                }
            } else {
                System.out.println("Failed to create a new account.");
            }

            return atmUser.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;


    }

    public boolean deleteUserFromDatabase(int userId) {
    String q1 = MySQLQueryStrings.DELETE_USER_FROM_DATABASE;
    boolean deletedOK = false;

    try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
         PreparedStatement stmt = conn.prepareStatement(q1)) {

        stmt.setInt(1, userId);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("User with ID " + userId + " does not exist.");
        } else {
            System.out.println("User with ID " + userId + " deleted successfully.");
            System.out.println("Account Deleted Successfully");
            deletedOK = true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return deletedOK;
    }
}
