package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;

import java.util.List;

public interface OrderingServiceInterface {

    List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> pendingRequests);

    List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests);

}
