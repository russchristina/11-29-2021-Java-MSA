package com.revature.repository.DAOInterface;


import com.revature.models.accounts.EmployeeAccount;

import java.util.List;

public interface EmployeeAccountDAOInterface {

    List<EmployeeAccount> getAllEmployeeAccounts();
    EmployeeAccount getEmployeeAccountsById(int id);
    void updateEmployeeAdmin(int employeeId, int adminId);
    void updateEmployeeAccountUser(int employeeId, int userId);

    void deleteEmployeeAccountById(int id);
    void addEmployeeAccount(int userId, int adminId);
}
