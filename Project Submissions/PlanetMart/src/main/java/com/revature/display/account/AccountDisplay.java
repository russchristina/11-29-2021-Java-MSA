package com.revature.display.account;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.CustomerUserDAO;
import com.revature.repository.UserCredentialsDAO;

import java.util.List;

public class AccountDisplay {

    public void displayCustomerBasicOptions(CustomerAccount customerAccount, User user) {
        System.out.println("\nSECONDARY USER OPTIONS");
        System.out.println("1. Open Inventory");
        System.out.println("2. Open Shop");
        System.out.println("3. Change User");
        System.out.println("4. Manage Money");
        System.out.println("5. Logout\n");
    }

    public void displayCustomerUpgradedOptions(CustomerAccount customerAccount, User user) {
        System.out.println("\nPRIMARY USER OPTIONS");
        System.out.println("1. Open Inventory");
        System.out.println("2. Open Shop");
        System.out.println("3. Change User");
        System.out.println("4. Manage Money");
        System.out.println("5. Logout");
        System.out.println("6. Add User");
        System.out.println("7. Transfer Funds");
        System.out.println("8. Change User names");
        System.out.println("9. Remove User");
        System.out.println("10. Add Account");
        System.out.println("11. Change Account");
    }

    public void displayCustomerAccount(CustomerAccount customerAccount) {
        System.out.println("\nCUSTOMER ACCOUNT ID: " + customerAccount.getCustomerAccountId());
        System.out.println("PRIMARY USER ATTACHED TO ACCOUNT: " + customerAccount.getPrimaryUserId());
    }

    public void displayUsers(List<User> users, CustomerAccount customerAccounts) {

        for (User user : users) {
            if(user.getUserId() == customerAccounts.getPrimaryUserId()){
                System.out.println("\nPRIMARY USER ID: " + user.getUserId());
                System.out.println("USER NAME: " + user.getName());
            }else{
                System.out.println("\nSECONDARY USER ID: " + user.getUserId());
                System.out.println("USER NAME: " + user.getName());
            }
        }

    }

    public void displayEmployeeAccount(EmployeeAccount employeeAccount) {
        System.out.println("\nEMPLOYEE OPTIONS");
        System.out.println("1. View all Customer Accounts");
        System.out.println("2. View Customer Account Information");
        System.out.println("3. View all Users");
        System.out.println("4. View User Information");
        System.out.println("5. Delete Account");
        System.out.println("6. Logout\n");
    }

    public void displayAdminAccount(EmployeeAccount employeeAccount) {
        System.out.println("\nADMIN OPTIONS");
        System.out.println("1. View all Customer Accounts");
        System.out.println("2. View Customer Account Information");
        System.out.println("3. View all Users");
        System.out.println("4. View User Information");
        System.out.println("5. Delete Account");
        System.out.println("6. Logout");
        System.out.println("7. View all Employee Accounts");
        System.out.println("8. Alter Account Info");
        System.out.println("9. Alter User Info");
        System.out.println("10. Add Employee Account");
        System.out.println("11. Add Admin Account\n");

    }

    public void displayEmployeeAccountInformation(List<EmployeeAccount> employeeAccounts) {
        System.out.println("\nEMPLOYEE ACCOUNT INFORMATION\n");
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();

        for (EmployeeAccount employeeAccount : employeeAccounts) {
            System.out.println("EMPLOYEE ACCOUNT ID: " + employeeAccount.getEmployeeId());
            System.out.println("EMPLOYEE ADMIN ID: " + employeeAccount.getAdminId());
            System.out.println("EMPLOYEE NAME: " + userCredentialsDAO.getUserCredentialById(employeeAccount.getUserId()).getFirstName()
                    + " "
                    +userCredentialsDAO.getUserCredentialById(employeeAccount.getUserId()).getLastName());

        }
    }

    public void displayAllUsers(List<CustomerAccount> allCustomerAccounts) {
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        AccountDisplay accountDisplay = new AccountDisplay();
        for (CustomerAccount customerAccount : allCustomerAccounts) {
            accountDisplay.displayCustomerAccount(customerAccount);
            for (User user : customerUserDAO.getAllUsersByCustomerId(customerAccount.getCustomerAccountId())) {
                if(user.getUserId() == customerAccount.getPrimaryUserId()){
                    System.out.println("\nPRIMARY USER ID: " + user.getUserId());
                    System.out.println("USER NAME: " + user.getName());
                }else{
                    System.out.println("\nSECONDARY USER ID: " + user.getUserId());
                    System.out.println("USER NAME: " + user.getName());
                }
            }
        }
    }
}
