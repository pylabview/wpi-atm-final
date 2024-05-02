package com.rodrigohacks.atm.model;

/**
 *
 */
public class ATMUser {
    private final String holder, roleDescription, userLogin, userLoginPin;
    private final int active, userType;
    private final double balance;
    private int id;
    private int accountId;

    /**
     * @param id
     * @param holder
     * @param roleDescription
     * @param userLogin
     * @param userLoginPin
     * @param balance
     * @param active
     * @param accountId
     * @param userType
     */
    public ATMUser(int id,
                   String holder,
                   String roleDescription,
                   String userLogin,
                   String userLoginPin,
                   double balance,
                   int active,
                   int accountId,
                   int userType) {
        this.id = id;
        this.holder = holder;
        this.roleDescription = roleDescription;
        this.userLogin = userLogin;
        this.userLoginPin = userLoginPin;
        this.balance = balance;
        this.active = active;
        this.accountId = accountId;
        this.userType = userType;
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
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * @return
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * @return
     */
    public String getUserLoginPin() {
        return userLoginPin;
    }

    /**
     * @return
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return
     */
    public int getActive() {
        return active;
    }

    /**
     * @return
     */
    public double getBalance() {
        return balance;
    }
}
