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

    CompletedRequestEntity insertCompletedRequest(int managerId, boolean status, String response, LocalDate dateResolved) throws SQLException;

    //Read

    CompletedRequestEntity getCompletedRequest(int requestId) throws SQLException;

    List<CompletedRequestEntity> getCompletedRequestByManagerIdList(int managerId) throws SQLException;

    List<CompletedRequestEntity> getAllCompletedRequestList() throws SQLException;

    List<CompletedRequestEntity> getCompletedRequestByStatus(boolean status) throws SQLException;


    //Update

    CompletedRequestEntity updateCompletedRequestByManagerId(int managerId, int requestId) throws SQLException;

    CompletedRequestEntity updateCompletedRequestStatus(int requestId, boolean status) throws SQLException;

    CompletedRequestEntity updateCompletedRequestResponse(int requestId, String response) throws SQLException;

    //Delete

    CompletedRequestEntity deleteCompletedRequest(int requestId) throws SQLException;
}
