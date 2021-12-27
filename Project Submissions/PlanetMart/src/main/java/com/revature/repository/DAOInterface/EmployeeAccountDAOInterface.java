package com.revature.repository.DAOInterface;


import com.revature.models.accounts.EmployeeAccount;
import com.revature.repository.Exception.InvalidAdminIdException;
import com.revature.repository.Exception.InvalidEmployeeAccountIdException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.repository.Exception.InvalidUserIdException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeAccountDAOInterface {

    List<EmployeeAccount> getAllEmployeeAccounts();
    EmployeeAccount getEmployeeAccountByEmployeeId(int id);
    EmployeeAccount getEmployeeAccountsByUserId(int id) throws SQLException, InvalidUserCredentialException;
    void updateEmployeeAdmin(int employeeId, int adminId) throws InvalidEmployeeAccountIdException, InvalidAdminIdException;
    void updateEmployeeAccountUser(int employeeId, int userId) throws InvalidEmployeeAccountIdException, InvalidUserIdException, SQLException, InvalidUserCredentialException;

    void deleteEmployeeAccountById(int id) throws InvalidEmployeeAccountIdException;
    void addEmployeeAccount(int userId, int adminId) throws SQLException, InvalidAdminIdException, InvalidUserCredentialException;
}
