package com.rodrigohacks.atm.daolayer;

import com.rodrigohacks.atm.model.ATMUser;

import java.util.HashMap;

public class MySQLQueryStrings {
    public static final String GET_USERS_FROM_DATABASE = "select users.id, users.holder, users.user_login, users.user_login_pin, roles.role_description, accounts.active, accounts.balance, accounts.id as accountId  from users " +
            "join accounts on  users.id = accounts.user_id " +
            "join roles on users.id = roles.user_id ";

    public static HashMap<String, String> UPDATE_USER_TO_DATABASE() {
        HashMap<String, String> updateUserQueryStrings = new HashMap<>();
        updateUserQueryStrings.put("updateUserQuery", "UPDATE users SET user_login_pin = ?, user_login = ?, holder = ? WHERE id = ?");
        updateUserQueryStrings.put("updateAccountQuery", "UPDATE accounts SET active = ? WHERE user_id = ?");

        return updateUserQueryStrings;
    }

    public static HashMap<String, String> ADD_USER_TO_DATABASE(ATMUser atmUser) {
        HashMap<String, String> newUserQueryStrings = new HashMap<>();
        newUserQueryStrings.put("insertQuery", """ 
                INSERT INTO users (user_type, holder, user_login_pin, user_login)
                VALUES (0, "%s", "%s", "%s");""".formatted(atmUser.getHolder(), atmUser.getUserLogin(), atmUser.getUserLoginPin()));
        newUserQueryStrings.put("insertAccountQuery", """ 
                INSERT INTO accounts (user_id, balance, active)
                VALUES (%d, %.2f, %d);""".formatted(atmUser.getId(), atmUser.getBalance(), atmUser.getActive()));
        newUserQueryStrings.put("insertRoleQuery", """ 
                INSERT INTO roles (role_description, user_id)
                VALUES ("%s", %d);""".formatted(atmUser.getRoleDescription(), atmUser.getId()));

    return newUserQueryStrings;
    }


    public static String DELETE_USER_FROM_DATABASE = "DELETE FROM users WHERE id = ?";

    public static String GET_BALANCE_FROM_DATABASE = "SELECT accounts.balance, accounts.id AS account_number, users.holder, @now AS time_stamp " +
            "FROM users " +
            "JOIN accounts ON accounts.user_id = users.id " +
            "WHERE users.id = ?";


    public static HashMap<String, String> WITHDRAW_FROM_ACCOUNT() {
        HashMap<String, String> withdrawFromAccount = new HashMap<>();
        withdrawFromAccount.put("updateWithdrawBalanceQuery", "UPDATE accounts SET balance = balance - ? WHERE user_id = ?");
        withdrawFromAccount.put("insertWithdrawalTransactionQuery", "INSERT INTO transactions (type) VALUES (?)");
        withdrawFromAccount.put("linkWithdrawalTransactionQuery", "INSERT INTO users_transactions (transaction_id, user_id) VALUES (LAST_INSERT_ID(), ?)");

        return withdrawFromAccount;
    }

    public static HashMap<String, String> DEPOSIT_TO_ACCOUNT() {
        HashMap<String, String> depositToAccount = new HashMap<>();
        depositToAccount.put("updateDepositBalanceQuery", "UPDATE accounts SET balance = balance + ? WHERE user_id = ?");
        depositToAccount.put("insertDepositTransactionQuery", "INSERT INTO transactions (type) VALUES (?)");
        depositToAccount.put("linkDepositTransactionQuery", "INSERT INTO users_transactions (transaction_id, user_id) VALUES (LAST_INSERT_ID(), ?)");

        return depositToAccount;
    }


    public static String GET_USER_ROLE_FROM_DATABASE = "SELECT roles.role_description, users.id " +
            "FROM users " +
            "JOIN roles ON users.id = roles.user_id " +
            "WHERE users.user_login = ? AND users.user_login_pin = ?";

    public static String SEARCH_ACCOUNT_FROM_DATABASE = "SELECT users.holder, accounts.id " +
            "FROM users " +
            "JOIN accounts ON users.id = accounts.user_id " +
            "WHERE accounts.id = ?";

    public static void main(String[] args) {
        ATMUser atmUser = new ATMUser(1, "Rodrigo", "Customer", "rodrigohacks", "1234", 1000.23, 1, 8);
        HashMap<String, String> userQuery = MySQLQueryStrings.UPDATE_USER_TO_DATABASE();
        System.out.println(userQuery.get("updateUserQuery"));
        System.out.println(userQuery.get("updateAccountQuery"));
        System.out.println(MySQLQueryStrings.DELETE_USER_FROM_DATABASE);
        System.out.println(MySQLQueryStrings.GET_BALANCE_FROM_DATABASE);
        System.out.println(MySQLQueryStrings.WITHDRAW_FROM_ACCOUNT().get("updateWithdrawBalanceQuery"));
        System.out.println(MySQLQueryStrings.WITHDRAW_FROM_ACCOUNT().get("insertWithdrawalTransactionQuery"));
        System.out.println(MySQLQueryStrings.WITHDRAW_FROM_ACCOUNT().get("linkWithdrawalTransactionQuery"));
        System.out.println(MySQLQueryStrings.DEPOSIT_TO_ACCOUNT().get("updateDepositBalanceQuery"));
        System.out.println(MySQLQueryStrings.DEPOSIT_TO_ACCOUNT().get("insertDepositTransactionQuery"));
        System.out.println(MySQLQueryStrings.DEPOSIT_TO_ACCOUNT().get("linkDepositTransactionQuery"));
        System.out.println(MySQLQueryStrings.GET_USER_ROLE_FROM_DATABASE);
        System.out.println(MySQLQueryStrings.SEARCH_ACCOUNT_FROM_DATABASE);
        System.out.println(MySQLQueryStrings.GET_USERS_FROM_DATABASE);
        System.out.println(MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertQuery"));
        System.out.println(MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertAccountQuery"));
        System.out.println(MySQLQueryStrings.ADD_USER_TO_DATABASE(atmUser).get("insertRoleQuery"));


    }

}