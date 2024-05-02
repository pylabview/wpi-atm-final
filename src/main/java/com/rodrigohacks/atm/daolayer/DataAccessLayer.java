package com.rodrigohacks.atm.daolayer;

import com.rodrigohacks.atm.model.ATMTransaction;
import com.rodrigohacks.atm.model.ATMUser;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataAccessLayer class provides methods to interact with the database.
 * It uses dotenv library to load environment variables for database connection.
 */
public class DataAccessLayer {
    String dbUrl;
    String dbUser;
    String dbPassword;

    public DataAccessLayer() {
        Dotenv dotenv = Dotenv.load();

        this.dbUrl = dotenv.get("DB_URL");
        this.dbUser = dotenv.get("DB_USER");
        this.dbPassword = dotenv.get("DB_PASSWORD");
    }

    /**
     * @return
     */
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

    /**
     * @param atmUser
     */
    public List<ATMTransaction> getTransactionsFromDatabase() {

        List<ATMTransaction> atmTransactions = new ArrayList<>();
        String query = MySQLQueryStrings.TRANSACTION_REPORT;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int userId = rs.getInt("userId");
                int userType = rs.getInt("userType");
                String holder = rs.getString("holder");
                String roleDescription = rs.getString("roleDescription");
                int transactionId = rs.getInt("trasactionId");
                Timestamp transactionDate = rs.getTimestamp("transactionDate");
                String transactionType = rs.getString("transactionType");
                int accountId = rs.getInt("accountId");
                double amount = rs.getDouble("amount");

                atmTransactions.add(new ATMTransaction(userId, userType, holder, transactionId, transactionDate, transactionType, accountId, amount, roleDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atmTransactions;
    }

    /**
     * @param accId
     * @return
     */
    public ATMUser getUserByAccountId(int accId) {
        String q1 = MySQLQueryStrings.GET_USER_BY_ACCOUNT_ID;
        ATMUser atmUser = null;
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmtSelect = conn.prepareStatement(q1)) {
            stmtSelect.setInt(1, accId);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();
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

                atmUser = new ATMUser(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active, accountId, userType);
            }
            return atmUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atmUser;
    }

    /**
     * @param userId
     * @return
     */
    public ATMUser getUserById(int userId) {
        String q1 = MySQLQueryStrings.GET_USER_BY_ID;
        ATMUser atmUser = null;
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmtSelect = conn.prepareStatement(q1)) {
            stmtSelect.setInt(1, userId);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();
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

                atmUser = new ATMUser(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active, accountId, userType);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atmUser;
    }

    /**
     * @param uId
     * @return
     */
    public ATMTransaction getTransactionByUserId(int uId) {
        String q1 = MySQLQueryStrings.TRANSACTION_REPORT_BY_USER_ID;
        ATMTransaction atmTransactionm = null;
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmtSelect = conn.prepareStatement(q1)) {
            stmtSelect.setInt(1, uId);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                int userType = rs.getInt("userType");
                String holder = rs.getString("holder");
                String roleDescription = rs.getString("roleDescription");
                int transactionId = rs.getInt("trasactionId");
                Timestamp transactionDate = rs.getTimestamp("transactionDate");
                String transactionType = rs.getString("transactionType");
                int accountId = rs.getInt("accountId");
                double amount = rs.getDouble("amount");

                atmTransactionm = new ATMTransaction(userId, userType, holder, transactionId, transactionDate, transactionType, accountId, amount, roleDescription);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atmTransactionm;
    }

    /**
     * @param uLogin
     * @return
     */
    public ATMUser getUserByLogin(String uLogin) {
        String q1 = MySQLQueryStrings.GET_USER_BY_LOGIN;
        ATMUser atmUser = null;
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmtSelect = conn.prepareStatement(q1)) {
            stmtSelect.setString(1, uLogin);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();
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

                atmUser = new ATMUser(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active, accountId, userType);
            }
            return atmUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atmUser;
    }

    /**
     * @param newATMUser
     * @return
     */
    public int addUserToDatabase(ATMUser newATMUser) {
        String q1 = MySQLQueryStrings.ADD_USER_TO_DATABASE(newATMUser).get("insertQuery");

        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             Statement stmt = conn.createStatement()) {

            // Execute SQL query to insert new user
            stmt.executeUpdate(q1);
            // Get the last inserted user ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                newATMUser.setId(rs.getInt(1));
            }
            String q2 = MySQLQueryStrings.ADD_USER_TO_DATABASE(newATMUser).get("insertRoleQuery");
            // Execute SQL query to insert user role
            stmt.executeUpdate(q2);

            // Execute SQL query to insert user account
            String q3 = MySQLQueryStrings.ADD_USER_TO_DATABASE(newATMUser).get("insertAccountQuery");
            // Create a PreparedStatement with the INSERT query and specify that you want to retrieve generated keys
            PreparedStatement stmt1 = conn.prepareStatement(q3, Statement.RETURN_GENERATED_KEYS);

            // Execute the INSERT query
            int affectedRows = stmt1.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows == 1) {
                // Retrieve the generated keys
                ResultSet rs1 = stmt1.getGeneratedKeys();
                if (rs1.next()) {
                    newATMUser.setAccountId(rs1.getInt(1)); // Retrieve the generated account ID
                    System.out.println("\"Account Successfully Created – the account number assigned is: " + newATMUser.getAccountId());
                } else {
                    System.out.println("Failed to retrieve the generated account ID.");
                }
            } else {
                System.out.println("Failed to create a new account.");
            }

            return newATMUser.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;


    }

    /**
     * @param userId
     * @return
     */
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

    /**
     * @param atmUser
     * @return
     */
    public boolean updateUserFromDatabase(ATMUser atmUser) {
        HashMap<String, String> q1 = MySQLQueryStrings.UPDATE_USER_TO_DATABASE();
        boolean updateOkay = false;

        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement userStmt = conn.prepareStatement(q1.get("updateUserQuery"));
             PreparedStatement accountStmt = conn.prepareStatement(q1.get("updateAccountQuery"), Statement.RETURN_GENERATED_KEYS)) {

            // Update user details
            userStmt.setString(1, atmUser.getUserLoginPin());
            userStmt.setString(2, atmUser.getUserLogin());
            userStmt.setString(3, atmUser.getHolder());
            userStmt.setInt(4, atmUser.getId());
            int userUpdatedRows = userStmt.executeUpdate();

            // Update account status
            accountStmt.setInt(1, atmUser.getActive());
            accountStmt.setInt(2, atmUser.getId());
            int accountUpdatedRows = accountStmt.executeUpdate();

            if (userUpdatedRows > 0 || accountUpdatedRows > 0) {
                System.out.println("User details updated successfully.");
                System.out.println("*********************************");
                System.out.println("Holder: " + atmUser.getHolder());
                System.out.println("Login: " + atmUser.getUserLogin());
                System.out.println("Pin Code: " + atmUser.getUserLoginPin());
                System.out.println("Status: " + atmUser.getActive());
                System.out.println("*********************************");
                updateOkay = true;
            } else {
                System.out.println("No changes were made.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateOkay;
    }

    /**
     * @param atmUser
     * @return
     */
    public double getBalanceFromUser(int userId) {
        double balance = 0.0;
        // Query to set the current timestamp
        String setTimestampQuery = "SET @now = CURRENT_TIMESTAMP()";

        // Query to select user's balance
        String q1 = MySQLQueryStrings.GET_BALANCE_FROM_DATABASE;

        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement stmtSetTimestamp = conn.prepareStatement(setTimestampQuery);
             PreparedStatement stmtSelect = conn.prepareStatement(q1)) {

            // Execute the SET statement
            stmtSetTimestamp.executeUpdate();

            // Set the user ID parameter for the SELECT statement
            stmtSelect.setInt(1, userId);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("balance");
                int accountNumber = rs.getInt("account_number");
                String holder = rs.getString("holder");
                String timeStamp = rs.getString("time_stamp");
                System.out.println("*********************************");
                System.out.println("Balance: " + balance);
                System.out.println("Account Number: " + accountNumber);
                System.out.println("Holder: " + holder);
                System.out.println("Timestamp: " + timeStamp);
                System.out.println("*********************************");
            } else {
                System.out.println("No balance found for user with ID " + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    /**
     * @param userId
     * @param amount
     */
    public void withdrawFromUser(int userId, double amount) {
        // Query to update user's balance for withdrawal
        HashMap<String, String> queries = MySQLQueryStrings.WITHDRAW_FROM_ACCOUNT();

        double currentBalance = getBalanceFromUser(userId);
        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement stmtUpdateBalance = conn.prepareStatement(queries.get("updateWithdrawBalanceQuery"));
             PreparedStatement stmtInsertWithdrawalTransaction = conn.prepareStatement(queries.get("insertWithdrawalTransactionQuery"));
             PreparedStatement stmtLinkWithdrawalTransaction = conn.prepareStatement(queries.get("linkWithdrawalTransactionQuery"))) {

            if (currentBalance < amount) {
                System.out.println("Insufficient funds for withdrawal.");
                return;
            }

            // Withdraw amount from the balance
            stmtUpdateBalance.setDouble(1, amount);
            stmtUpdateBalance.setInt(2, userId);
            stmtUpdateBalance.executeUpdate();

            // Record the withdrawal transaction
            stmtInsertWithdrawalTransaction.setString(1, "Withdraw");
            stmtInsertWithdrawalTransaction.setDouble(2, amount);
            stmtInsertWithdrawalTransaction.executeUpdate();

            // Link the withdrawal transaction to the user
            stmtLinkWithdrawalTransaction.setInt(1, userId);
            stmtLinkWithdrawalTransaction.executeUpdate();

            // Display withdrawal details
            System.out.println("*********************************");
            System.out.println("Account #" + userId);
            System.out.println("Date: " + "01/29/2024"); // Assuming a static date for this example
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + (currentBalance - amount));
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + userId);
            System.out.println("Date: " + "01/29/2024"); // Assuming a static date for this example
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + (currentBalance - amount));
            System.out.println("*********************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userId
     * @param amount
     */
    public void depositToUserAccount(int userId, double amount) {
        // Query to update user's balance for deposit
        HashMap<String, String> queries = MySQLQueryStrings.DEPOSIT_TO_ACCOUNT();
        double currentBalance = getBalanceFromUser(userId);
        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement stmtUpdateBalance = conn.prepareStatement(queries.get("updateDepositBalanceQuery"));
             PreparedStatement stmtInsertDepositTransaction = conn.prepareStatement(queries.get("insertDepositTransactionQuery"));
             PreparedStatement stmtLinkDepositTransaction = conn.prepareStatement(queries.get("linkDepositTransactionQuery"))) {

            // Deposit funds into the user's account
            stmtUpdateBalance.setDouble(1, amount);
            stmtUpdateBalance.setInt(2, userId);
            stmtUpdateBalance.executeUpdate();

            // Record the deposit transaction
            stmtInsertDepositTransaction.setString(1, "Deposit");
            stmtInsertDepositTransaction.setDouble(2, amount);
            stmtInsertDepositTransaction.executeUpdate();

            // Link the deposit transaction to the user
            stmtLinkDepositTransaction.setInt(1, userId);
            stmtLinkDepositTransaction.executeUpdate();

            // Display deposit details
            System.out.println("*********************************");
            System.out.println("Cash Deposited Successfully.");
            System.out.println("Account #" + userId);
            System.out.println("Date: " + "01/29/2024"); // Assuming a static date for this example
            System.out.println("Deposited: " + amount);
            System.out.println("Balance: " + (currentBalance + amount));
            System.out.println("*********************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userId
     * @param amount
     */
    public Map<String, Object> getRoleDescription(String userLogin, String userLoginPin) {
        // Check if user login pin is 5 characters long
        if (userLoginPin.length() != 5) {
            System.out.println("Failed to log in. User login pin must be 5 characters long.");
            return null;
        }

        // Check if user login pin consists of digits
        if (!userLoginPin.matches("\\d+")) {
            System.out.println("Failed to log in. User login pin must consist of digits.");
            return null;
        }


        // Query to retrieve role description and user ID
        String selectRoleQuery = MySQLQueryStrings.GET_USER_ROLE_BY_LOGIN_AND_PIN;

        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement stmtSelectRole = conn.prepareStatement(selectRoleQuery)) {

            // Set parameters for the query
            stmtSelectRole.setString(1, userLogin);
            stmtSelectRole.setString(2, userLoginPin);

            // Execute the query
            ResultSet rs = stmtSelectRole.executeQuery();

            // Check if a result is found
            if (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("role_description", rs.getString("role_description"));
                result.put("user_id", rs.getInt("id"));
                return result;
            } else {
                System.out.println("Failed to log in. Invalid login credentials.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param userId
     */
    public void searchAccount(int accountId) {
        String searchAccountQuery = MySQLQueryStrings.SEARCH_USER_BY_ACCOUNT_ID;

        try (Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement stmtSearchAccount = conn.prepareStatement(searchAccountQuery)) {

            // Set parameter for the query
            stmtSearchAccount.setInt(1, accountId);

            // Execute the query
            ResultSet rs = stmtSearchAccount.executeQuery();

            // Check if an account is found
            if (rs.next()) {
                String holderName = rs.getString("holder");
                int accountIdFound = rs.getInt("id");
                System.out.println("The account information is:");
                System.out.println("Account #" + accountIdFound);
                System.out.println("Holder: " + holderName);
            } else {
                System.out.println("No account found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
