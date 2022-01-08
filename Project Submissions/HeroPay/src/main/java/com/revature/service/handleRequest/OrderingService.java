package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.service.handleRequest.interfaces.OrderingServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class OrderingService implements OrderingServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService;
    private CompletedRequestService completedRequestService;
    private CompletedRequestDao completedRequestDao;

    public OrderingService(PendingRequestService pendingRequestService, CompletedRequestService completedRequestService, CompletedRequestDao completedRequestDao) {
        this.pendingRequestService = pendingRequestService;
        this.completedRequestService = completedRequestService;
        this.completedRequestDao = completedRequestDao;
    }

    @Override
    public List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests) {

        List<PendingRequest> sortingList = pendingRequests;
        pendingRequests.sort(Comparator.comparing(PendingRequest::getDateSubmission));
        dLog.debug("Order by date of pendingRequests");
        return sortingList;
    }

    @Override
    public List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests) {
        List<CompletedRequest> sortingList = completedRequests;
        completedRequests.sort(Comparator.comparing(CompletedRequest::getDateResolved));
        dLog.debug("Order by date completedRequests");
        return sortingList;
    }

    @Override
    public List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests) {
        List<PendingRequest> sortingList = pendingRequests;
        pendingRequests.sort(Comparator.comparing(PendingRequest::getAmount));
        dLog.debug("Order by Amount pendingRequests");
        return sortingList;
    }

    @Override
    public SortedMap<Double, CompletedRequest> orderByAmountCompleted(List<CompletedRequest> completedRequestsTrue) throws SQLException {
        List<PendingRequest> pendingRequestList = pendingRequestService.getAllPendingRequests();

        List<PendingRequest> completePenderRequestList = new ArrayList<>();

        for (CompletedRequest completedRequest : completedRequestsTrue) {
            for (PendingRequest pendingRequest : pendingRequestList) {
                if(pendingRequest.getId() == completedRequest.getId()) completePenderRequestList.add(pendingRequest);
            }
        }
        List<PendingRequest> orderedCompletePendingReqList = orderByAmountPending(completePenderRequestList);
        SortedMap<Double, CompletedRequest> orderdCompleteRequestSortedMap = new TreeMap<>();

        for (PendingRequest pendingRequest : orderedCompletePendingReqList) {
            orderdCompleteRequestSortedMap.put(pendingRequest.getAmount(), completedRequestService.convertCompletedRequestEntity(completedRequestDao.getCompletedRequest(pendingRequest.getId())));
        }
        dLog.debug("order by amount CompletedRequests");
        return orderdCompleteRequestSortedMap;
    }
}
