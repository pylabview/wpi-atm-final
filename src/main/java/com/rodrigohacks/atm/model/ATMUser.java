package com.rodrigohacks.atm.model;

public class ATMUser {
    private final int id;
    private final String holder, roleDescription, userLogin, userLoginPin;
    private final int accountId, active, userType;
    private final double balance;

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

    public int getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserLoginPin() {
        return userLoginPin;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getActive() {
        return active;
    }

    public double getBalance() {
        return balance;
    }
}
