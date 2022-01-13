package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.service.DTO.SortedRequests;
import com.revature.service.handleRequest.interfaces.SortingServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class SortingService implements SortingServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");


    public SortingService() {
    }

    @Override
    public List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests) {
        pendingRequests.sort(Comparator.comparing(PendingRequest::getDateSubmission));
        dLog.debug("Order by date of pendingRequests");
        return pendingRequests;
    }

    @Override
    public List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests) {
        completedRequests.sort(Comparator.comparing(CompletedRequest::getDateResolved));
        dLog.debug("Order by date completedRequests");
        return completedRequests;
    }

    @Override
    public List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests) {
        pendingRequests.sort(Comparator.comparing(PendingRequest::getAmount));
        dLog.debug("Order by Amount pendingRequests");
        return pendingRequests;
    }

    @Override
    public List<QuickSortEmployee> orderSortedEmployee(List<QuickSortEmployee> sortedEmployees) {
        sortedEmployees.sort(Comparator.comparing((QuickSortEmployee::getSum)));
        return sortedEmployees;
    }

    @Override
    public SortedRequests sortPendingRequestsByStatus(List<PendingRequest> pendingRequests) {
        List<PendingRequest> answeredRequests = new ArrayList<>();
        List<PendingRequest> unansweredRequests = new ArrayList<>();
        pendingRequests.forEach(e -> {
            if(e.isStatus()) answeredRequests.add(e);
            else unansweredRequests.add(e);
        });
        return new SortedRequests(unansweredRequests, answeredRequests, null);

    }

    @Override
    public List<QuickSortEmployee> employeeRankedSort(HashMap<EmployeeAccountEntity, BigDecimal> employeeSet) {
        List<QuickSortEmployee> quickSortEmployees = new ArrayList<>(employeeSet.size());

        for (EmployeeAccountEntity employeeAccount : employeeSet.keySet()) {
            quickSortEmployees.add(new QuickSortEmployee(employeeAccount.getId(), employeeSet.get(employeeAccount)));
        }
        
        return orderSortedEmployee(quickSortEmployees);
    }
}
