package com.revature.service.handleManager.interfaces;

import com.revature.presentation.model.requests.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;

public interface ManagerServiceInterface {

    //view all reimbursements past and pending
    //approve or deny any reimbursement
    //view statistics page

    CompletedRequest approveRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response);

    CompletedRequest denyRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response);

}
