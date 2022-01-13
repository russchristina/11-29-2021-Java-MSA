package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.EmployeeAccountEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CompletedRequestInterface {

        /*
    Create
    Read
    Update
    Delete
     */

    //Create

    Integer insertCompletedRequest(CompletedRequestEntity completedRequestEntity);

    //Read

    List<CompletedRequestEntity> getCompletedRequestByEmployeeId(EmployeeAccountEntity employee);

    CompletedRequestEntity getCompletedRequestWithUniqueId(int requestId);

    List<CompletedRequestEntity> getCompletedRequestByManagerId(EmployeeAccountEntity manager);

    List<CompletedRequestEntity> getAllCompletedRequestList();



    //Update

    Integer updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity);

    Integer updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity);

    Integer updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity);

    //Delete


    List<CompletedRequestEntity> getCompletedRequestsByStatus(boolean status);
}
