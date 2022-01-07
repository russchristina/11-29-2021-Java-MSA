package com.revature.service.handleManager;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;
import com.revature.service.handleManager.interfaces.ManagerServiceInterface;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;

public class ManagerService implements ManagerServiceInterface {

    private PendingRequestService pRService;
    private CompletedRequestService cRService;

    public ManagerService(PendingRequestService pRService, CompletedRequestService cRService) {
        this.pRService = pRService;
        this.cRService = cRService;
    }

    @Override
    public CompletedRequest approveRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response) {

        PendingRequest approvedRequest = pRService.convertPendingRequestEntity(pRService.updatePendingRequestStatus(pendingRequest.getId(), true));
        return cRService.convertPendingRequest(pendingRequest, managerId, true, response);
    }

    @Override
    public CompletedRequest denyRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response) {
        PendingRequest approvedRequest = pRService.convertPendingRequestEntity(pRService.updatePendingRequestStatus(pendingRequest.getId(), true));
        return cRService.convertPendingRequest(pendingRequest, managerId, false, response);    }
}
