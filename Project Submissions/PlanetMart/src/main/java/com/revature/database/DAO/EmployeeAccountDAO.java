package com.revature.database.DAO;


import com.revature.models.accounts.EmployeeAccount;

import java.util.List;

public interface EmployeeAccountDAO {

    public List<EmployeeAccount> getAllEmployeeAccounts();
    public List<EmployeeAccount> getEmployeeAccountsByLocation(String location);
    public void updateEmployeeAccountById(int id);
    public void deleteEmployeeAccountById(int id);
    public void addEmployeeAccount(EmployeeAccount e);
}
