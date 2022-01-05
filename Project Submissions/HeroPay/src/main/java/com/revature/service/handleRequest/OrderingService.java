package com.revature.service.handleRequest;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.service.handleRequest.interfaces.OrderingServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class OrderingService implements OrderingServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(OrderingService.class);

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
        logger.debug("Order by date of pendingRequests");
        return sortingList;
    }

    @Override
    public List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests) {
        List<CompletedRequest> sortingList = completedRequests;
        completedRequests.sort(Comparator.comparing(CompletedRequest::getDateResolved));
        logger.debug("Order by date completedRequests");
        return sortingList;
    }

    @Override
    public List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests) {
        List<PendingRequest> sortingList = pendingRequests;
        pendingRequests.sort(Comparator.comparing(PendingRequest::getAmount));
        logger.debug("Order by Amount pendingRequests");
        return sortingList;
    }

    @Override
    public List<CompletedRequest> orderByAmountCompleted(List<CompletedRequest> completedRequestsTrue) throws SQLException {
        List<CompletedRequest> sortingList = completedRequestsTrue;
        List<PendingRequest> pendingRequestList = pendingRequestService.getAllPendingRequests();

        List<PendingRequest> completePenderRequestList = new ArrayList<>();

        for (CompletedRequest completedRequest : sortingList) {
            for (PendingRequest pendingRequest : pendingRequestList) {
                if(pendingRequest.getId() == completedRequest.getId()) completePenderRequestList.add(pendingRequest);

            }
        }
        List<PendingRequest> orderedCompletePendingReqList = orderByAmountPending(completePenderRequestList);
        List<CompletedRequest> orderedCompleteList = new ArrayList<>(orderedCompletePendingReqList.size());

        for (PendingRequest pendingRequest : orderedCompletePendingReqList) {
            orderedCompleteList.add(completedRequestService.convertCompletedRequestEntity(completedRequestDao.getCompletedRequest(pendingRequest.getId())));
        }
        logger.debug("order by amount CompletedRequests");
        return orderedCompleteList;
    }
}
