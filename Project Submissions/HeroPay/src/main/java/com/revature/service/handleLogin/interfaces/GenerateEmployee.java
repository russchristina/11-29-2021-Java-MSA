package com.revature.service.handleLogin.interfaces;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.LoginInfoEntity;

public interface GenerateEmployee {

    EmployeeAccountEntity generateEmployee(LoginInfoEntity loginInfoEntity);


}
