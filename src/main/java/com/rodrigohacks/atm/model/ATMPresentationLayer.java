package com.rodrigohacks.atm.model;

import com.rodrigohacks.atm.daolayer.DataAccessLayer;

import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the presentation layer of the ATM system.
 * It interacts with the user and the data access layer to perform various operations.
 *
 * @author Your Name
 * @version 1.0
 */
public class ATMPresentationLayer {
    DataAccessLayer dataAccessLayer = new DataAccessLayer();
    int currentUserId = 0;
    int adminUserId = 0;

    /**
     * Starts the application by displaying the login screen and verifying the login and pin code.
     * If the login and pin code are valid, it displays the appropriate menu based on the user's role.
     *
     * @param scanner The scanner object used to read user input.
     */
    public void startApplication() {
        Scanner scanner = new Scanner(System.in);

        // Display login screen
        System.out.println("Welcome to the ATM System");
        System.out.println("Please enter your login and 5-digit pin code->");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Pin code: ");
        String pinCode = scanner.nextLine();

        // Verify login and pin code
        Map<String, Object> roleInfo = dataAccessLayer.getRoleDescription(login, pinCode);
        if (roleInfo != null) {
            String roleDescription = (String) roleInfo.get("role_description");
            currentUserId = (int) roleInfo.get("user_id");
            System.out.println("Role Description: " + roleDescription);
            System.out.println("User ID: " + currentUserId);

            // Display appropriate menu based on user's role
            if (roleDescription.equalsIgnoreCase("Admin")) {
                adminUserId = currentUserId;
                displayAdminMenu();
            } else {
                displayCustomerMenu();
            }
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }

    /**
     * Displays the admin menu.
     */
    private void displayCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display customer option menu
            System.out.println("Customer Options:");
            System.out.println("1----Withdraw Cash");
            System.out.println("3----Deposit Cash");
            System.out.println("4----Display Balance");
            System.out.println("5----Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            // Perform actions based on selected option
            switch (option) {
                case 1:
                    // Call withdrawCash() method
                    withdrawCash();
                    break;
                case 3:
                    // Call depositCash() method
                    depositCash();
                    break;
                case 4:
                    // Call displayBalance() method
                    displayBalance();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private void displayBalance() {

        dataAccessLayer.getBalanceFromUser(currentUserId);
    }

    /**
     *
     */
    private void depositCash() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the cash amount to deposit: ");
        double amount = scanner.nextDouble();
        // Call depositToAccount() method with the specified amount
        dataAccessLayer.depositToUserAccount(currentUserId, amount); // You need to provide userId
    }


    private void withdrawCash() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();
        // Call withdrawFromAccount() method with the specified amount
        dataAccessLayer.withdrawFromUser(currentUserId, amount); // You need to provide userId
    }

    private void displayAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display admin menu options
            System.out.println("Administrator Options:");
            System.out.println("1----Create New Account");
            System.out.println("2----Delete Existing Account");
            System.out.println("3----Update Account Information");
            System.out.println("4----Search for Account");
            System.out.println("6----Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            // Perform actions based on selected option
            switch (option) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    deleteExistingAccount();
                    break;
                case 3:
                    updateAccountInformation();
                    break;
                case 4:
                    searchForAccount();
                    break;
                case 6:
                    System.out.println("Exiting admin menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);

    }


    private void searchForAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the account number to search: ");
        int accountNumber = scanner.nextInt();
        dataAccessLayer.searchAccount(accountNumber);
        // Display the retrieved account information
    }

    private void updateAccountInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Updating account information:");

        // Input account number to update
        System.out.print("Enter the User account number to update: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        ATMUser atmU = dataAccessLayer.getUserByAccountId(accountId);
        System.out.println("----> userId: " + atmU.getId());
        System.out.print("Login: ");
        String userLogin = scanner.nextLine();
        System.out.print("Pin Code: ");
        String userLoginPin = scanner.nextLine();
        System.out.print("Holder's Name: ");
        String holder = scanner.nextLine();
        System.out.print("Status (Active - [1] /Inactive - [0]): ");
        int active = Integer.parseInt(scanner.next());
        ATMUser atmUserUpdated = new ATMUser(atmU.getId(),
                holder,
                "Customer",
                userLogin,
                userLoginPin,
                -1,
                active,
                accountId,
                0);
        dataAccessLayer.updateUserFromDatabase(atmUserUpdated);
        // Perform the necessary actions to update the account information
        // Call the appropriate method from UserDetails class to update the account information
        // Display appropriate message after updating the account information
        System.out.println("Account information updated successfully.");
    }

    private void deleteExistingAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting an existing account:");

        // Input account number to delete
        System.out.print("Enter the user account number to delete: ");
        int userAccountNumber = scanner.nextInt();
        ATMUser atmU = dataAccessLayer.getUserByAccountId(userAccountNumber);
        System.out.println("You wish to delete the account held by John Doe. If this information is correct, please\n" +
                "re-enter the account number: " + userAccountNumber);
        int reenterUserAccountNumber = scanner.nextInt();
        // Perform the necessary actions to delete the account
        // Call the appropriate method from UserDetails class to delete the account
        if (userAccountNumber == reenterUserAccountNumber) {
            dataAccessLayer.deleteUserFromDatabase(atmU.getId());
        } else {
            System.out.println("Account # " + userAccountNumber + " deletion is aborted");
        }
    }

    private void createNewAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a new account:");

        // Input account information
        System.out.print("Login: ");
        String userLogin = scanner.nextLine();
        System.out.print("Pin Code: ");
        String userLoginPin = scanner.nextLine();
        System.out.print("Holder's Name: ");
        String holder = scanner.nextLine();
        System.out.print("Starting Balance: ");
        String startingBalance = scanner.nextLine();
        System.out.print("Status (Active/Inactive): ");
        int active = Integer.parseInt(scanner.next());

        ATMUser atmUser = new ATMUser(1,
                holder,
                "Customer",
                userLogin,
                userLoginPin,
                Double.valueOf(startingBalance),
                active,
                -1,
                0);

        dataAccessLayer.addUserToDatabase(atmUser);

    }


}
