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
        System.out.println(createShapes.indent + "1. INVENTORY");
        System.out.println(createShapes.indent + "2. SHOP");
        System.out.println(createShapes.indent + "3. CHANGE USER");
        System.out.println(createShapes.indent + "4. MANAGE MONEY");
        System.out.println(createShapes.indent + "5. LOGOUT");
        System.out.println(createShapes.border);

    }

    public void displayCustomerUpgradedOptions(CustomerAccount customerAccount, User user) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "PRIMARY USER OPTIONS");
        System.out.println(createShapes.indent + "1. INVENTORY");
        System.out.println(createShapes.indent + "2. SHOP");
        System.out.println(createShapes.indent + "3. CHANGE USER");
        System.out.println(createShapes.indent + "4. MANAGE MONEY");
        System.out.println(createShapes.indent + "5. LOGOUT");
        System.out.println(createShapes.indent + "6. ADD USER");
        System.out.println(createShapes.indent + "7. TRANSFER FUNDS BETWEEN USERS");
        System.out.println(createShapes.indent + "8. CHANGE NAME OF USER");
        System.out.println(createShapes.indent + "9. REMOVE USER");
        System.out.println(createShapes.indent + "10. ADD ACCOUNT");
        System.out.println(createShapes.indent + "11. CHANGE CURRENT ACCOUNT");
        System.out.println(createShapes.border);

    }

    public void displayCustomerAccount(CustomerAccount customerAccount) {
        System.out.println();
        System.out.println(createShapes.indent + "CUSTOMER ACCOUNT ID: " + customerAccount.getCustomerAccountId());
        System.out.println(createShapes.indent + "PRIMARY USER ATTACHED TO ACCOUNT: " + customerAccount.getPrimaryUserId());
        System.out.println();
    }

    public void displayUsers(List<User> users, CustomerAccount customerAccounts) {
        System.out.println(createShapes.border);

        for (User user : users) {
            System.out.println();
            if(user.getUserId() == customerAccounts.getPrimaryUserId()){
                System.out.println(createShapes.indent + "PRIMARY USER ID: " + user.getUserId());
                System.out.println(createShapes.indent + "USER NAME: " + user.getName());
            }else{
                System.out.println(createShapes.indent + "SECONDARY USER ID: " + user.getUserId());
                System.out.println(createShapes.indent + "USER NAME: " + user.getName());
            }
        }
    }

    public void displayEmployeeAccount(EmployeeAccount employeeAccount) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "EMPLOYEE OPTIONS");
        System.out.println(createShapes.indent + "1. VIEW ALL CUSTOMER ACCOUNTS");
        System.out.println(createShapes.indent + "2. VIEW SPECIFIC CUSTOMER ACCOUNT INFORMATION");
        System.out.println(createShapes.indent + "3. VIEW ALL USERS");
        System.out.println(createShapes.indent + "4. VIEW SPECIFIC USER INFORMATION");
        System.out.println(createShapes.indent + "5. WIPE ACCOUNT FROM HISTORY");
        System.out.println(createShapes.indent + "6. DELETE SPECIFIC ACCOUNT");
        System.out.println(createShapes.indent + "7. LOGOUT");
        System.out.println(createShapes.border);

    }

    public void displayAdminAccount(EmployeeAccount employeeAccount) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "ADMIN OPTIONS");
        System.out.println(createShapes.indent + "1. VIEW ALL CUSTOMER ACCOUNTS");
        System.out.println(createShapes.indent + "2. VIEW SPECIFIC CUSTOMER ACCOUNT INFORMATION");
        System.out.println(createShapes.indent + "3. VIEW ALL USERS");
        System.out.println(createShapes.indent + "4. VIEW SPECIFIC USER INFORMATION");
        System.out.println(createShapes.indent + "5. WIPE ACCOUNT FROM HISTORY");
        System.out.println(createShapes.indent + "6. DELETE SPECIFIC ACCOUNT");
        System.out.println(createShapes.indent + "7. LOGOUT");
        System.out.println(createShapes.indent + "8. VIEW ALL EMPLOYEE ACCOUNTS");
        System.out.println(createShapes.indent + "9. ALTER ACCOUNT DATA");
        System.out.println(createShapes.indent + "10. ALTER USER DATA");
        System.out.println(createShapes.indent + "11. ADD EMPLOYEE ACCOUNT");
        System.out.println(createShapes.indent + "12. ADD ADMIN ACCOUNT");
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
