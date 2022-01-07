package com.revature.service.handleManager.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;

import java.util.List;

public interface ManagerServiceInterface {

    //view all reimbursements past and pending
    //approve or deny any reimbursement
    //view statistics page

    CompletedRequest approveRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response);

    CompletedRequest denyRequest(PendingRequest pendingRequest, int employeeId, int managerId, String response);

}
