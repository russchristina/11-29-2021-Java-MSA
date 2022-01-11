package com.revature.presentation.manageRequest.controller;

import com.revature.presentation.model.requests.*;
import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.response.AllPendingRequestResponse;
import com.revature.presentation.model.requests.response.AllRequestResponse;
import com.revature.presentation.model.requests.response.FailCreateRequestResponse;
import com.revature.presentation.model.requests.response.ManagerRequestResponse;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.NegativeAmountException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class RequestController {



    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService = null;
    private CompletedRequestService completedRequestService = null;

    public RequestController(PendingRequestService pendingRequestService, CompletedRequestService completedRequestService) {
        this.pendingRequestService = pendingRequestService;
        this.completedRequestService = completedRequestService;
    }

    public final Handler getEmployeeRequests = ctx -> {
        dLog.debug("Getting Employee Requests: " + ctx.queryParam("employeeId"));
        try{
            int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
            System.out.println(employeeId);
            AllRequestResponse allRequests = new AllRequestResponse();
            List<PendingRequest> pendingRequests = pendingRequestService.getAllEmployeePendingRequest(employeeId);
            List<PendingRequest> answeredRequests = pendingRequestService.getAllAnsweredRequests(employeeId);
            List<CompletedRequest> completedRequests = completedRequestService.getAllEmployeeRequests(employeeId);
            System.out.println(answeredRequests);
            allRequests.setPendingRequests(pendingRequests);
            allRequests.setAnsweredRequests(answeredRequests);
            allRequests.setCompletedRequests(completedRequests);
            ctx.json(allRequests);
        }catch (NumberFormatException e){
            dLog.debug(e.getMessage(), e);
            ctx.status(406);
        }
    };

    public final Handler getEmployeePendingRequests = ctx -> {
        dLog.debug("Getting Employee Pending Requests: " + ctx.queryParam("employeeId"));
        try{
            int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
            AllPendingRequestResponse allRequests = new AllPendingRequestResponse();
            List<PendingRequest> pendingRequests = pendingRequestService.getAllEmployeePendingRequest(employeeId);
            allRequests.setStatus(true);
            allRequests.setPendingRequests(pendingRequests);
            ctx.json(allRequests);
        }catch (NumberFormatException e){
            dLog.debug(e.getMessage(), e);
            ctx.status(406);
        }
    };

    public final Handler createRequest = ctx -> {
        dLog.debug("Creating new request: " + ctx.body());
        try{
            NewRequest newRequest = ctx.bodyAsClass(NewRequest.class);
            PendingRequest pendingRequest = new PendingRequest(0,newRequest.getEmployeeId(),newRequest.getType(),newRequest.getRequestMessage(),newRequest.getAmount(),LocalDate.now());
            try{
                pendingRequestService.validateNewPendingRequest(pendingRequest);
                tLog.info("New Pending Request Created: " + pendingRequest);
                ctx.json(pendingRequestService.convertPendingRequestEntity(pendingRequestService.storePendingRequest(pendingRequest)));
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

        }catch(Exception e){
            dLog.debug(e.getMessage(), e);
        }
    };

    public final Handler getAllRequests = ctx -> {
        dLog.debug("Getting All Requests");
        try{
            AllRequestResponse allRequests = new AllRequestResponse();
            List<PendingRequest> pendingRequests = pendingRequestService.getAllPendingRequests();
            List<PendingRequest> answeredRequests = pendingRequestService.getAnsweredRequests();
            List<CompletedRequest> completedRequests = completedRequestService.getAllCompletedRequests();
            allRequests.setPendingRequests(pendingRequests);
            allRequests.setAnsweredRequests(answeredRequests);
            allRequests.setCompletedRequests(completedRequests);
            ctx.json(allRequests);
        }catch (NumberFormatException e){
            dLog.debug(e.getMessage(), e);
            ctx.status(406);
        }
    };

    public final Handler respondToRequest = ctx -> {
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
//            pendingRequestService.updatePendingRequestStatus(storedRequest.getId(), true);
            ctx.json(completedRequestService.convertCompletedRequestEntity(storedRequest));
            ctx.status(201);

        } catch (Exception e){
            dLog.debug(e.getMessage(), e);
            ctx.status(500);
        }

    };


}
