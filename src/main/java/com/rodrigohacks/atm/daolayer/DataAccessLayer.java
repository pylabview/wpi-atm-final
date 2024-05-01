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

                userList.add(new ATMUser(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active, accountId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public boolean AddUserToDatabase(ATMUser atmUser) {


        return false;
    }


    public boolean addUserToDatabase(ATMUser atmUser) {
        double balance = atmUser.getBalance();
//        int accountId = atmUser.getAccountId();

        return false;




    }



    public static void main(String[] args) {
        DataAccessLayer dataAccessLayer = new DataAccessLayer();
        System.out.println(dataAccessLayer.dbUrl);
        System.out.println(dataAccessLayer.dbUser);
        System.out.println(dataAccessLayer.dbPassword);
        List<ATMUser> userList = dataAccessLayer.getUsersFromDatabase();

        for (ATMUser user : userList) {
            System.out.println(user.getHolder());
        }
    }

}
