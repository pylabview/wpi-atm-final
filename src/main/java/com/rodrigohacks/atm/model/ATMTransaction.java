package com.rodrigohacks.atm.model;

import java.sql.Timestamp;

public class ATMTransaction {
    private final String roleDescription;
    private int userId;
    private int userType;
    private String holder;
    private int transactionId;
    private Timestamp transactionDate;
    private String transactionType;
    private int accountId;
    private double transactionAmount;

    public ATMTransaction(
            int userId,
            int userType,
            String holder,
            int transactionId,
            Timestamp transactionDate,
            String transactionType,
            int accountId,
            double amount,
            String roleDescription)
    {
        this.userId = userId;
        this.userType = userType;
        this.holder = holder;
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.transactionAmount = amount;
        this.roleDescription = roleDescription;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserType() {
        return userType;
    }

    public String getHolder() {
        return holder;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
