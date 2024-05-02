package com.rodrigohacks.atm.model;

import java.sql.Timestamp;

/**
 * This class represents a transaction in the ATM system.
 */
public class ATMTransaction {
    private final String roleDescription;
    private final int userId;
    private final int userType;
    private final String holder;
    private final int transactionId;
    private final Timestamp transactionDate;
    private final String transactionType;
    private final int accountId;
    private final double transactionAmount;

    /**
     * @param userId
     * @param userType
     * @param holder
     * @param transactionId
     * @param transactionDate
     * @param transactionType
     * @param accountId
     * @param amount
     * @param roleDescription
     */
    public ATMTransaction(
            int userId,
            int userType,
            String holder,
            int transactionId,
            Timestamp transactionDate,
            String transactionType,
            int accountId,
            double amount,
            String roleDescription) {
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

    /**
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @return
     */
    public int getUserType() {
        return userType;
    }


    /**
     * @return
     */
    public String getHolder() {
        return holder;
    }

    /**
     * @return
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * @return
     */
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    /**
     * @return
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @return
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @return
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @return
     */
    public String getRoleDescription() {
        return roleDescription;
    }
}
