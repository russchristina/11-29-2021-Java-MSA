package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.service.DTO.SortedRequests;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public interface SortingServiceInterface {

    List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests);

    List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests);

    List<QuickSortEmployee> orderSortedEmployee(List<QuickSortEmployee> sortedEmployees);

    SortedRequests sortPendingRequestsByStatus(List<PendingRequest> pendingRequests);

    List<QuickSortEmployee> employeeRankedSort(HashMap<EmployeeAccountEntity, BigDecimal> employeeSet);
}
