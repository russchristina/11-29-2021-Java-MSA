package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DTO.CompletedRequestEntity;

import java.util.List;

public interface CompletedRequestServiceInterface {

    CompletedRequestEntity storeCompletedRequest(CompletedRequest completedRequest);

    CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity);

    boolean validateCompletedRequest(CompletedRequest completedRequest);

    List<CompletedRequest> getAllCompletedRequests();

    List<CompletedRequest> getAllCompletedRequestsByManagerId(int managerId);

    List<CompletedRequest> getAllCompletedRequestsByStatus(boolean status);

    CompletedRequestEntity deleteCompletedRequest(int requestId);

    CompletedRequest convertPendingRequest(PendingRequest pendingRequest, int managerId, boolean status, String response);

    List<CompletedRequest> getAllEmployeeRequests(int employeeId);
}
