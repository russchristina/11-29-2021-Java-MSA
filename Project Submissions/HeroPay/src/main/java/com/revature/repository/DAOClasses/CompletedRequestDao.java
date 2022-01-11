package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.CompletedRequestInterface;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.utility.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestDao implements CompletedRequestInterface {
    @Override
    public CompletedRequestEntity insertCompletedRequest(CompletedRequestEntity completedRequestEntity) {
        return null;
    }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByEmployeeId(int employeeId) {
        return null;
    }

    @Override
    public CompletedRequestEntity getCompletedRequest(int requestId) {
        return null;
    }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByManagerIdList(int managerId) {
        return null;
    }

    @Override
    public List<CompletedRequestEntity> getAllCompletedRequestList() {
        return null;
    }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByStatus(boolean status) {
        return null;
    }

    @Override
    public CompletedRequestEntity updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity) {
        return null;
    }

    @Override
    public CompletedRequestEntity updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity) {
        return null;
    }

    @Override
    public CompletedRequestEntity updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity) {
        return null;
    }

    @Override
    public CompletedRequestEntity deleteCompletedRequest(CompletedRequestEntity completedRequestEntity) {
        return null;
    }
}
