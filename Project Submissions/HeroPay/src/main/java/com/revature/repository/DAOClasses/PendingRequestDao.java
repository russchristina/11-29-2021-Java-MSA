package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.PendingRequestInterface;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.service.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PendingRequestDao implements PendingRequestInterface {

    @Override
    public PendingRequestEntity insertPendingRequest(int employeeId, int typeId, String requestMessage, double amount, java.sql.Date dateSubmission) throws SQLException {
        PendingRequestEntity entity = null;
        final String SQL = "INSERT INTO pending_request values(default, ?, ?, ?, ?, ? RETURNING *";
        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, employeeId);
            stmt.setInt(2, typeId);
            stmt.setString(3, requestMessage);
            stmt.setDouble(4, amount);
            stmt.setDate(5, dateSubmission);

            if(rs.next()) entity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6)
            );
        }
        return entity;
    }

    @Override
    public RequestTypeEntity insertPendingRequestType(String type) throws SQLException {
        RequestTypeEntity entity = null;
        final String SQL = "INSERT INTO request_type values(default, ?) RETURNING *";

        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setString(1, type);

            if(rs.next()) entity = new RequestTypeEntity(
                    rs.getInt(1),
                    rs.getString(2)
            );

        }

        return entity;
    }

    @Override
    public PendingRequestEntity getPendingRequest(int requestId) throws SQLException {
        PendingRequestEntity entity = null;
        final String SQL = "SELECT * FROM pending_request WHERE id = ?";

        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setInt(1, requestId);
            if(rs.next()) entity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6)
            );
        }
        return entity;
    }

    @Override
    public List<PendingRequestEntity> getEmployeePendingRequestList(int employeeId) throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE employee_id = ?";

        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setInt(1, employeeId);
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6)
                    )
            );
        }
        return pendingRequestEntityList;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequests() {
        return null;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequestsByType(int typeId) {
        return null;
    }

    @Override
    public Map<Integer, String> getRequestTypeMap() {
        return null;
    }

    @Override
    public RequestTypeEntity getRequestTypeWithId(int id) {
        return null;
    }

    @Override
    public RequestTypeEntity getRequestTypeWithString(String type) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestTyoe(int requestId, int typeId) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestMessage(int requestId, String requestMessage) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequestAmount(int requestId, double amount) {
        return null;
    }

    @Override
    public PendingRequestEntity updatePendingRequest(int requestId, int employeeId, int typeId, String requestMessage, double amount, java.sql.Date dateSubmission) {
        return null;
    }

    @Override
    public PendingRequestEntity deletePendingRequest(int requestId) {
        return null;
    }
}
