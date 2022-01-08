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
    public PendingRequestEntity insertPendingRequest(int employeeId, int typeId, String requestMessage, double amount, java.sql.Date dateSubmission) throws SQLException {
        PendingRequestEntity entity = null;
        final String SQL = "INSERT INTO pending_request values(default, ?, ?, ?, ?, ?) RETURNING *";
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, employeeId);
            stmt.setInt(2, typeId);
            stmt.setString(3, requestMessage);
            stmt.setDouble(4, amount);
            stmt.setDate(5, dateSubmission);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
        }           rs.close();
        return entity;
    }

    @Override
    public RequestTypeEntity insertPendingRequestType(String type) throws SQLException {
        RequestTypeEntity entity = null;
        final String SQL = "INSERT INTO request_type values(default, ?) RETURNING *";

        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setString(1, type);
            rs = stmt.executeQuery();

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
        final String SQL = "SELECT * FROM pending_request WHERE id = ? AND status = false";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setInt(1, requestId);
            rs = stmt.executeQuery();
            if(rs.next()) entity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }
        return entity;
    }

    @Override
    public List<PendingRequestEntity> getEmployeePendingRequestList(int employeeId) throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE employee_id = ? AND status = false";

        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6),
                            rs.getBoolean(7)
                    )
            );
        }
        return pendingRequestEntityList;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequests() throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE status = false";

        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            rs = stmt.executeQuery();
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6),
                            rs.getBoolean(7)
                    )
            );
            rs.close();
        }
        return pendingRequestEntityList;
    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequestsByType(int typeId) throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE type = ? AND status = false";

        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, typeId);
            rs = stmt.executeQuery();
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6),
                            rs.getBoolean(7)
                    )
            );
            rs.close();
        }
        return pendingRequestEntityList;    }

    @Override
    public Map<Integer, String> getRequestTypeMap() throws SQLException {
        final String SQL = "SELECT * FROM request_type";

        Map<Integer, String> requestTypeMap = new HashMap<>();

        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            result = statement.executeQuery();

            while(result.next()) requestTypeMap.put(result.getInt(1), result.getString(2));

            result.close();
        }

        return requestTypeMap;    }

    @Override
    public RequestTypeEntity getRequestTypeWithString(String type) throws SQLException {
        RequestTypeEntity requestTypeEntity = null;

        final String SQL = "SELECT * FROM request_type WHERE type = ?";

        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while(rs.next()) requestTypeEntity = new RequestTypeEntity(rs.getInt(1), rs.getString(2));
        }
        return requestTypeEntity;        }

    @Override
    public PendingRequestEntity updatePendingRequestStatus(int requestId, boolean status) throws SQLException {
        final String SQL = "UPDATE pending_request SET status = ? WHERE id = ? RETURNING *";

        PendingRequestEntity pendingRequestEntity = null;
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setBoolean(1, status);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) pendingRequestEntity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }

        return pendingRequestEntity;    }

    @Override
    public PendingRequestEntity updatePendingRequestType(int requestId, int typeId) throws SQLException {
        final String SQL = "UPDATE pending_request SET type = ? WHERE id = ? RETURNING *";

        PendingRequestEntity pendingRequestEntity = null;
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, typeId);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) pendingRequestEntity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }

        return pendingRequestEntity;
    }

    @Override
    public PendingRequestEntity updatePendingRequestMessage(int requestId, String requestMessage) throws SQLException {
        final String SQL = "UPDATE pending_request SET request_message = ? WHERE id = ? RETURNING *";

        PendingRequestEntity pendingRequestEntity = null;
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, requestMessage);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) pendingRequestEntity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }

        return pendingRequestEntity;    }

    @Override
    public PendingRequestEntity updatePendingRequestAmount(int requestId, double amount) throws SQLException {
        final String SQL = "UPDATE pending_request SET amount = ? WHERE id = ? RETURNING *";

        PendingRequestEntity pendingRequestEntity = null;
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setDouble(1, amount);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) pendingRequestEntity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }

        return pendingRequestEntity;    }

    @Override
    public PendingRequestEntity deletePendingRequest(int requestId) throws SQLException {
        final String SQL = "DELETE FROM pending_request WHERE id = ? RETURNING *";

        PendingRequestEntity pendingRequestEntity = null;
        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, requestId);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) pendingRequestEntity = new PendingRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDate(6),
                    rs.getBoolean(7)
            );
            rs.close();
        }

        return pendingRequestEntity;    }

    @Override
    public List<PendingRequestEntity> getAnsweredRequests() throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE status = true";

        ResultSet rs;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            rs = stmt.executeQuery();
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6),
                            rs.getBoolean(7)
                    )
            );
            rs.close();
        }
        return pendingRequestEntityList;    }

    @Override
    public List<PendingRequestEntity> getEmployeeAnsweredRequests(int employeeId) throws SQLException {
        List<PendingRequestEntity> pendingRequestEntityList = new ArrayList<>();

        final String SQL = "SELECT * FROM pending_request WHERE employee_id = ? AND status = true";

        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();
            while(rs.next()) pendingRequestEntityList.add(
                    new PendingRequestEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDate(6),
                            rs.getBoolean(7)
                    )
            );
        }
        return pendingRequestEntityList;
    }
}
