package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.CompletedRequestInterface;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.service.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestDao implements CompletedRequestInterface {
    @Override
    public CompletedRequestEntity insertCompletedRequest(int managerId, boolean status, String response, double amount, Date dateSubmission) throws SQLException {
        CompletedRequestEntity entity = null;
        final String SQL = "INSERT INTO completed_request values(default, ?, ?, ?, ?, ?) RETURNING *";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ){
            stmt.setInt(1, managerId);
            stmt.setBoolean(2, status);
            stmt.setString(3, response);
            stmt.setDouble(4, amount);
            stmt.setDate(5, dateSubmission);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            );
        }
        return entity;
    }

    @Override
    public CompletedRequestEntity getCompletedRequest(int requestId) throws SQLException {
        CompletedRequestEntity entity = null;
        final String SQL = "SELECT * FROM completed_request WHERE id = ?";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            );
        }
        return entity;    }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByManagerIdList(int managerId) throws SQLException {
        List<CompletedRequestEntity> entityList = new ArrayList<>();
        final String SQL = "SELECT * FROM completed_request WHERE manager_id = ?";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, managerId);
            rs = stmt.executeQuery();

            while(rs.next()) entityList.add(new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            ));
        }
        return entityList;        }

    @Override
    public List<CompletedRequestEntity> getAllCompletedRequestList() throws SQLException {
        List<CompletedRequestEntity> entityList = new ArrayList<>();
        final String SQL = "SELECT * FROM completed_request";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            rs = stmt.executeQuery();

            while(rs.next()) entityList.add(new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            ));
        }
        return entityList;            }

    @Override
    public List<CompletedRequestEntity> getResponseTypeWithStatus(boolean status) throws SQLException {
        List<CompletedRequestEntity> entityList = new ArrayList<>();
        final String SQL = "SELECT * FROM completed_request WHERE status = ?";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setBoolean(1, status);
            rs = stmt.executeQuery();

            while(rs.next()) entityList.add(new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            ));
        }
        return entityList;        }

    @Override
    public CompletedRequestEntity updateCompletedRequestByManagerId(int managerId, int requestId) throws SQLException {
        CompletedRequestEntity entity = null;
        final String SQL = "UPDATE completed_request SET manager_id = ? WHERE id = ?";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, managerId);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            );
        }
        return entity;    }

    @Override
    public CompletedRequestEntity updateCompletedRequestStatus(int requestId, boolean status) throws SQLException {
       CompletedRequestEntity entity = null;
       final String SQL = "UPDATE completed_request SET status = ? WHERE id = ?";
       ResultSet rs;
       try(
               Connection conn = ConnectionFactory.getConnection();
               PreparedStatement stmt = conn.prepareStatement(SQL)
               ){
           stmt.setBoolean(1, status);
           stmt.setInt(2, requestId);
           rs = stmt.executeQuery();

           if(rs.next()) entity = new CompletedRequestEntity(
                   rs.getInt(1),
                   rs.getInt(2),
                   rs.getBoolean(3),
                   rs.getString(4),
                   rs.getDate(5)
           );
       }
       return entity;
    }

    @Override
    public CompletedRequestEntity updateCompletedRequestResponse(int requestId, String response) throws SQLException {
        CompletedRequestEntity entity = null;
        final String SQL = "UPDATE completed_request SET response = ? WHERE id = ?";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, response);
            stmt.setInt(2, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            );
        }
        return entity;    }


    @Override
    public CompletedRequestEntity deleteCompletedRequest(int requestId) throws SQLException {
        CompletedRequestEntity entity = null;
        final String SQL = "DELETE FROM completed_request WHERE id = ? RETURNING *";
        ResultSet rs;
        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1, requestId);
            rs = stmt.executeQuery();

            if(rs.next()) entity = new CompletedRequestEntity(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBoolean(3),
                    rs.getString(4),
                    rs.getDate(5)
            );
        }
        return entity;    }
}
