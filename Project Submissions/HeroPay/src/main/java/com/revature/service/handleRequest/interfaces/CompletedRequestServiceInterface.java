package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DTO.CompletedRequestEntity;

import java.util.List;

public interface CompletedRequestServiceInterface {

    CompletedRequestEntity storeCompletedRequest(CompletedRequest completedRequest);

    CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity);

    void validateCompletedRequest(CompletedRequest completedRequest);

    List<CompletedRequest> getAllCompletedRequests();

    List<CompletedRequest> getAllEmployeeRequests(int employeeId);

}
