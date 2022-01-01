package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.CompletedRequestEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CompletedRequestInterface {

        /*
    Create
    Read
    Update
    Delete
     */

    //Create

    CompletedRequestEntity insertCompletedRequest(int employeeId, String type, String requestMessage, double amount, LocalDate dateSubmission);

    //Read

    CompletedRequestEntity getCompletedRequest(int requestId);

    List<CompletedRequestEntity> getCompletedRequestByManagerIdList(int managerId);

    List<CompletedRequestEntity> getAllCompletedRequestList();

    CompletedRequestEntity getResponseTypeWithId(int id);

    CompletedRequestEntity getResponseTypeWithString(String type);


    //Update

    CompletedRequestEntity updateCompletedRequestTyoe(int requestId, String type);

    CompletedRequestEntity updateCompletedRequestRequestMessage(int requestId, String requestMessage);

    CompletedRequestEntity updateCompletedRequestAmount(int requestId, double amount);

    CompletedRequestEntity updateCompletedRequest(int requestId, int employeeId, String type, String requestMessage, double amount, LocalDate dateSubmission);


    //Delete

    CompletedRequestEntity deleteCompletedRequest(int requestId);
}
