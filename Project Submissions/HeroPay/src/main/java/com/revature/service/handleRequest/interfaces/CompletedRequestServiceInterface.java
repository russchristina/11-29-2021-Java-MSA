package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.repository.DTO.CompletedRequestEntity;

import java.util.List;

public interface CompletedRequestServiceInterface {

    CompletedRequestEntity storeCompletedRequest(CompletedRequest completedRequest);

    CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity);

    List<CompletedRequest> getAllCompletedRequests();

    List<CompletedRequest> getAllCompletedRequestsByManagerId(int managerId);

    List<CompletedRequest> getAllCompletedRequestsByStatus(boolean status);

    CompletedRequestEntity deleteCompletedRequest(int requestId);



}
