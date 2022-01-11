package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.PendingRequestInterface;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.repository.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingRequestDao implements PendingRequestInterface {

    @Override
    public PendingRequestEntity insertPendingRequest(PendingRequestEntity pendingRequestEntity) {
        return null;
    }

    @Override
    public PendingRequestEntity getPendingRequest(int requestId) {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getEmployeePendingRequestList(int employeeId) {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequests() {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequestsByTypeId(int typeId) {
        return null;
    }

    @Override
    public Map<Integer, String> getRequestTypeMap() {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getAnsweredRequests() {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getEmployeeAnsweredRequests(int employeeId) {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getEmployeeAnsweredRequestsByTypeId(int typeId) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestStatus(PendingRequestEntity pendingRequestEntity) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestMessage(PendingRequestEntity pendingRequestEntity) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestAmount(PendingRequestEntity pendingRequestEntity) {
        return null;
    }

    @Override
    public PendingRequestEntity deletePendingRequest(PendingRequestEntity pendingRequestEntity) {
        return null;
    }
}
