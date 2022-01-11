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

    CompletedRequestEntity insertCompletedRequest(CompletedRequestEntity completedRequestEntity);

    //Read

    List<CompletedRequestEntity> getCompletedRequestByEmployeeId(int employeeId);

    CompletedRequestEntity getCompletedRequest(int requestId);

    List<CompletedRequestEntity> getCompletedRequestByManagerIdList(int managerId);

    List<CompletedRequestEntity> getAllCompletedRequestList();

    List<CompletedRequestEntity> getCompletedRequestByStatus(boolean status);


    //Update

    CompletedRequestEntity updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity);

    CompletedRequestEntity updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity);

    CompletedRequestEntity updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity);

    //Delete

    CompletedRequestEntity deleteCompletedRequest(CompletedRequestEntity completedRequestEntity);


}
