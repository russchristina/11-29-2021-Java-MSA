package com.revature.presentation.manageRequest.controller;

import com.revature.presentation.model.requests.*;
import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.response.AllPendingRequestResponse;
import com.revature.presentation.model.requests.response.FailCreateRequestResponse;
import com.revature.presentation.model.requests.response.ManagerRequestResponse;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.service.DTO.SortedRequests;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.handleRequest.SortingService;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.NegativeAmountException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import com.revature.utility.JWTHandler;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RequestController {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService;
    private CompletedRequestService completedRequestService;
    private SortingService sortingService;


    public RequestController(PendingRequestService pendingRequestService, CompletedRequestService completedRequestService, SortingService sortingService) {
        this.pendingRequestService = pendingRequestService;
        this.completedRequestService = completedRequestService;
        this.sortingService = sortingService;
    }

    public final Handler getEmployeeRequests = ctx -> {
        if(JWTHandler.verifyUser(ctx.header("Authorization"))) {
            dLog.debug("User Verified");
            dLog.debug("Getting Employee Requests: " + ctx.queryParam("employeeId"));
            try{
                int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
                List<PendingRequest> pendingRequests = pendingRequestService.getAllEmployeePendingRequest(employeeId);
                SortedRequests sortedRequests = sortingService.sortPendingRequestsByStatus(pendingRequests);
                sortedRequests.setCompletedRequests(completedRequestService.getAllEmployeeRequests(employeeId));
                System.out.println(sortedRequests);
                ctx.json(sortedRequests);
            }catch (NumberFormatException e){
                dLog.debug(e.getMessage(), e);
                ctx.status(406);
            }
        }else{
            dLog.debug("Unauthorized User Access Attempt");
            ctx.status(401);
        }

    };

    public final Handler getEmployeePendingRequests = ctx -> {
        if(JWTHandler.verifyUser(ctx.header("Authorization"))) {
            dLog.debug("User Verified");
            dLog.debug("Getting Employee Pending Requests: " + ctx.queryParam("employeeId"));
            try{
                int employeeId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("employeeId")));
                AllPendingRequestResponse allRequests = new AllPendingRequestResponse();
                List<PendingRequest> pendingRequests = pendingRequestService.getAllEmployeePendingRequest(employeeId);
                SortedRequests sortedRequests = sortingService.sortPendingRequestsByStatus(pendingRequests);
                allRequests.setStatus(true);
                allRequests.setPendingRequests(sortedRequests.getUnansweredRequests());
                ctx.json(allRequests);
            }catch (NumberFormatException e){
                dLog.debug(e.getMessage(), e);
                ctx.status(406);
            }
        }else{
            dLog.debug("Unauthorized User Access Attempt");
            ctx.status(401);
        }
    };

    public final Handler createRequest = ctx -> {
        if(JWTHandler.verifyUser(ctx.header("Authorization"))) {
            dLog.debug("User Verified");
            dLog.debug("Creating new request: " + ctx.body());

            String fileUpload = ctx.queryParam("fileUpload");
            //Handle different request if file upload .contentEquals('check');
            //set boolean check in newRequest - boolean file upload
            try{
                NewRequest newRequest = ctx.bodyAsClass(NewRequest.class);
                if(fileUpload.contentEquals("check")){
                    dLog.debug("File upload checked for new request");
                    pendingRequestService.validateNewPendingRequest(newRequest);
                    PendingRequestEntity pendingRequestEntity = pendingRequestService.storePendingRequest(newRequest, true);
                    dLog.debug("The stored and returned pendingRequestEntity: " + pendingRequestEntity);
                    if (pendingRequestEntity == null) ctx.status(500);
                    PendingRequest pendingRequest = pendingRequestService.convertPendingRequestEntity(pendingRequestEntity);
                    tLog.info("Inserted new pending request in database");
                    ctx.json(pendingRequest);
                }else {
                    dLog.debug("File upload unchecked for new request");
                    pendingRequestService.validateNewPendingRequest(newRequest);
                    PendingRequestEntity pendingRequestEntity = pendingRequestService.storePendingRequest(newRequest, false);
                    dLog.debug("The stored and returned pendingRequestEntity: " + pendingRequestEntity);
                    if (pendingRequestEntity == null) ctx.status(500);
                    PendingRequest pendingRequest = pendingRequestService.convertPendingRequestEntity(pendingRequestEntity);
                    tLog.info("Inserted new pending request in database");
                    ctx.json(pendingRequest);
                }
                ctx.status(201);
            }catch(EmployeeIdException e){
                dLog.debug(e.getMessage(), e);
                ctx.json(new FailCreateRequestResponse("Invalid Employee Id"));
                ctx.status(406);
            }catch(RequestTypeException e){
                dLog.debug(e.getMessage(), e);
                ctx.json(new FailCreateRequestResponse("Invalid Request Type"));
                ctx.status(406);
            }catch(RequestMessageShortException e){
                dLog.debug(e.getMessage(), e);
                ctx.json(new FailCreateRequestResponse("Invalid Request Message, too short"));
                ctx.status(406);
            }catch(NegativeAmountException e){
                dLog.debug(e.getMessage(), e);
                ctx.json(new FailCreateRequestResponse("Invalid amount requested"));
                ctx.status(406);
            }
        }else{
            dLog.debug("Unauthorized User Access Attempt");
            ctx.status(401);
        }

    };

    public final Handler getAllRequests = ctx -> {
        if(JWTHandler.verifyAdmin(ctx.header("Authorization"))){
            dLog.debug("Admin Authorized");
            dLog.debug("Getting All Requests");
            try{
                List<PendingRequest> pendingRequests = pendingRequestService.getAllPendingRequests();
                SortedRequests sortedRequests = sortingService.sortPendingRequestsByStatus(pendingRequests);
                sortedRequests.setCompletedRequests(completedRequestService.getAllCompletedRequests());
                ctx.json(sortedRequests);
            }catch (NumberFormatException e){
                dLog.debug(e.getMessage(), e);
                ctx.status(406);
            }
        }else{
            dLog.debug("Unauthorized Admin Attempt");
            ctx.status(401);
        }
    };

    public final Handler respondToRequest = ctx -> {
        if(JWTHandler.verifyAdmin(ctx.header("Authorization"))){
            dLog.debug("Admin Authorized");
            dLog.debug("Responding to request: " + ctx.body());
            try{
                ManagerRequestResponse msg = ctx.bodyAsClass(ManagerRequestResponse.class);
                if(msg.getEmployeeId() == msg.getManagerId()) {
                    ctx.status(451);
                }
                CompletedRequest completedRequest = new CompletedRequest(
                        msg.getRequestId(),
                        msg.getEmployeeId(),
                        msg.getManagerId(),
                        msg.isStatus(),
                        msg.getResponse(),
                        LocalDate.now()
                );
                completedRequestService.validateCompletedRequest(completedRequest);
                CompletedRequestEntity storedRequest = completedRequestService.storeCompletedRequest(completedRequest);
                pendingRequestService.updatePendingRequestStatus(storedRequest.getPendingRequest().getId(), true);
                ctx.json(completedRequestService.convertCompletedRequestEntity(storedRequest));
                ctx.status(201);

            } catch (Exception e){
                dLog.debug(e.getMessage(), e);
                ctx.status(500);
            }
        }else{
            dLog.debug("Unauthorized Admin Attempt");
            ctx.status(401);
        }
    };
}
