package com.revature.service.handleManager;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.service.handleManager.interfaces.ManagerServiceInterface;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerService implements ManagerServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pRService;
    private CompletedRequestService cRService;

    public ManagerService(PendingRequestService pRService, CompletedRequestService cRService) {
        this.pRService = pRService;
        this.cRService = cRService;
    }

    @Override
    public CompletedRequest approveRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response) {
        dLog.debug("Approving pending request" + ManagerService.class);
        PendingRequest approvedRequest = pRService.convertPendingRequestEntity(pRService.updatePendingRequestStatus(pendingRequest.getId(), true));
        tLog.info("Pending request approved" + approvedRequest.toString());
        return cRService.convertPendingRequest(approvedRequest, managerId, true, response);
    }

    @Override
    public CompletedRequest denyRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response) {
        dLog.debug("Denying pending request" + ManagerService.class);
        PendingRequest deniedRequest = pRService.convertPendingRequestEntity(pRService.updatePendingRequestStatus(pendingRequest.getId(), true));
        tLog.info("Pending request denied" + deniedRequest.toString());
        return cRService.convertPendingRequest(deniedRequest, managerId, false, response);    }
}
