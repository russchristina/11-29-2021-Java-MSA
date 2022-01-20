package com.revature.inter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.CompleteOffers;

public interface CompleteOfferInterface {
	
    CompleteOffers applyCompleteOffer(int managerid, boolean status, String response, Date resolvedate, int employee_id, int requestId) throws SQLException;

   CompleteOffers getCompletedRequest(int requestId) throws SQLException;
   
   List<CompleteOffers> getCompletedRequestByEmployeeId(int employee_id) throws SQLException;

    List<CompleteOffers>retrieveCompleteOfferByManagerIdList(int managerId) throws SQLException;

    List<CompleteOffers> retreiveCompleteOfferList() throws SQLException;

    List<CompleteOffers> retreiveTypeResponseWithStatus(boolean status) throws SQLException;
    
    CompleteOffers updateCompletedRequestByManagerId(int managerId, int requestId) throws SQLException;

    CompleteOffers updateCompletedRequestStatus(int requestId, boolean status) throws SQLException;

    CompleteOffers updateCompletedRequestResponse(int requestId, String response) throws SQLException;

    CompleteOffers deleteCompleteOffer(int requestId) throws SQLException;

}
