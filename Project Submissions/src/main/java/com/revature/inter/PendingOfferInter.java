package com.revature.inter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.revature.models.PendingOffers;
import com.revature.models.TypeOffer;

public interface PendingOfferInter {
	
	PendingOffers applyPendingOffer(int employee_id, int id, String message, double amount, java.sql.Date date_submission) throws SQLException;

    TypeOffer applyPendingOfferType(String type) throws SQLException;
    
    PendingOffers retreivePendingOffer(int id) throws SQLException;

    List<PendingOffers> retreiveAssociatePendingOfferList(int employee_id) throws SQLException;

    List<PendingOffers> retreivePendingOffers() throws SQLException;

    List<PendingOffers> retreivePendingRequestsByType(int id) throws SQLException;

    Map<Integer, String> retreiveRequestTypeMap() throws SQLException;

    TypeOffer retreiveTypeWithString(String type) throws SQLException;
    
    PendingOffers changePendingOfferType(int requestId, int id) throws SQLException;
    
    PendingOffers changePendingOfferStatus(int requestId, boolean status) throws SQLException;

    PendingOffers changePendingOfferMessage(int requestId, String message) throws SQLException;

    PendingOffers changePendingOfferAmount(int requestId, double amount) throws SQLException;
    
    PendingOffers deletePendingOffer(int requestId) throws SQLException;



}
