package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.CompletedRequestEntity;

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

    List<CompletedRequestEntity> getCompletedRequestByEmployeeId(int employeeId);

    CompletedRequestEntity getCompletedRequest(int requestId);

    List<CompletedRequestEntity> getCompletedRequestByManagerId(int managerId);

    List<CompletedRequestEntity> getAllCompletedRequestList();



    //Update

    Integer updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity);

    Integer updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity);

    Integer updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity);

    //Delete

    void deleteCompletedRequest(CompletedRequestEntity completedRequestEntity);


    List<CompletedRequestEntity> getCompletedRequestsByStatus(boolean status);
}
