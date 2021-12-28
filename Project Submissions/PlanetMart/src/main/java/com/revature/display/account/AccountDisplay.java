package com.revature.display.account;

import com.revature.display.utility.CreateShapes;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.CustomerUserDAO;
import com.revature.repository.UserCredentialsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountDisplay {

    CreateShapes createShapes = new CreateShapes();

    public void displayCustomerBasicOptions(CustomerAccount customerAccount, User user) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "SECONDARY USER OPTIONS");
        System.out.println(createShapes.indent + "1. Open Inventory");
        System.out.println(createShapes.indent + "2. Open Shop");
        System.out.println(createShapes.indent + "3. Change User");
        System.out.println(createShapes.indent + "4. Manage Money");
        System.out.println(createShapes.indent + "5. Logout");
        System.out.println(createShapes.border);

    }

    public void displayCustomerUpgradedOptions(CustomerAccount customerAccount, User user) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "PRIMARY USER OPTIONS");
        System.out.println(createShapes.indent + "1. Open Inventory");
        System.out.println(createShapes.indent + "2. Open Shop");
        System.out.println(createShapes.indent + "3. Change User");
        System.out.println(createShapes.indent + "4. Manage Money");
        System.out.println(createShapes.indent + "5. Logout");
        System.out.println(createShapes.indent + "6. Add User");
        System.out.println(createShapes.indent + "7. Transfer Funds");
        System.out.println(createShapes.indent + "8. Change User names");
        System.out.println(createShapes.indent + "9. Remove User");
        System.out.println(createShapes.indent + "10. Add Account");
        System.out.println(createShapes.indent + "11. Change Account");
        System.out.println(createShapes.border);

    }

    public void displayCustomerAccount(CustomerAccount customerAccount) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "CUSTOMER ACCOUNT ID: " + customerAccount.getCustomerAccountId());
        System.out.println(createShapes.indent + "PRIMARY USER ATTACHED TO ACCOUNT: " + customerAccount.getPrimaryUserId());
        System.out.println(createShapes.border);
    }

    public void displayUsers(List<User> users, CustomerAccount customerAccounts) {
        System.out.println(createShapes.border);

        for (User user : users) {
            System.out.println(createShapes.border);
            if(user.getUserId() == customerAccounts.getPrimaryUserId()){
                System.out.println(createShapes.indent + "PRIMARY USER ID: " + user.getUserId());
                System.out.println(createShapes.indent + "USER NAME: " + user.getName());
            }else{
                System.out.println(createShapes.indent + "SECONDARY USER ID: " + user.getUserId());
                System.out.println(createShapes.indent + "USER NAME: " + user.getName());
            }
        }
        System.out.println(createShapes.border);
    }

    public void displayEmployeeAccount(EmployeeAccount employeeAccount) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "EMPLOYEE OPTIONS");
        System.out.println(createShapes.indent + "1. View all Customer Accounts");
        System.out.println(createShapes.indent + "2. View Customer Account Information");
        System.out.println(createShapes.indent + "3. View all Users");
        System.out.println(createShapes.indent + "4. View User Information");
        System.out.println(createShapes.indent + "5. WIPE ACCOUNT FROM HISTORY");
        System.out.println(createShapes.indent + "6. Delete Specific Account");
        System.out.println(createShapes.indent + "6. Logout");
        System.out.println(createShapes.border);

    }

    public void displayAdminAccount(EmployeeAccount employeeAccount) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "ADMIN OPTIONS");
        System.out.println(createShapes.indent + "1. View all Customer Accounts");
        System.out.println(createShapes.indent + "2. View Customer Account Information");
        System.out.println(createShapes.indent + "3. View all Users");
        System.out.println(createShapes.indent + "4. View User Information");
        System.out.println(createShapes.indent + "5. WIPE ACCOUNT FROM HISTORY");
        System.out.println(createShapes.indent + "6. Delete Specific Account");
        System.out.println(createShapes.indent + "7. Logout");
        System.out.println(createShapes.indent + "8. View all Employee Accounts");
        System.out.println(createShapes.indent + "9. Alter Account");
        System.out.println(createShapes.indent + "10. Alter User");
        System.out.println(createShapes.indent + "11. Add Employee Account");
        System.out.println(createShapes.indent + "12. Add Admin Account");
        System.out.println(createShapes.border);


    }

    public void displayEmployeeAccountInformation(List<EmployeeAccount> employeeAccounts) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "EMPLOYEE ACCOUNT INFORMATION");
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();

        for (EmployeeAccount employeeAccount : employeeAccounts) {
            System.out.println(createShapes.border);
            System.out.println(createShapes.indent + "EMPLOYEE ACCOUNT ID: " + employeeAccount.getEmployeeId());
            System.out.println(createShapes.indent + "EMPLOYEE ADMIN ID: " + employeeAccount.getAdminId());
            System.out.println(createShapes.indent + "EMPLOYEE NAME: " + userCredentialsDAO.getUserCredentialById(employeeAccount.getUserId()).getFirstName()
                    + " "
                    +userCredentialsDAO.getUserCredentialById(employeeAccount.getUserId()).getLastName());

        }
        System.out.println(createShapes.border);

    }

    public void displayAllUsers(List<CustomerAccount> allCustomerAccounts) {
        System.out.println(createShapes.border);
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        AccountDisplay accountDisplay = new AccountDisplay();
        for (CustomerAccount customerAccount : allCustomerAccounts) {
            System.out.println(createShapes.border);
            accountDisplay.displayCustomerAccount(customerAccount);
            for (User user : customerUserDAO.getAllUsersByCustomerId(customerAccount.getCustomerAccountId())) {
                System.out.println(createShapes.border);
                if(user.getUserId() == customerAccount.getPrimaryUserId()){
                    System.out.println(createShapes.indent + "PRIMARY USER ID: " + user.getUserId());
                    System.out.println(createShapes.indent + "USER NAME: " + user.getName());
                }else{
                    System.out.println(createShapes.indent + "SECONDARY USER ID: " + user.getUserId());
                    System.out.println(createShapes.indent + "USER NAME: " + user.getName());
                }
            }        System.out.println(createShapes.border);

        }
    }
}
