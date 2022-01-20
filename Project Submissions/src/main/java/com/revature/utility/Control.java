package com.revature.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Associate;
import com.revature.models.TypeOffer;
import com.revature.repoclass.AssociateOfferImp;

import io.javalin.http.Handler;

public class Control {
	
	public static Handler saveRequest = ctx -> {
		String fullName = ctx.formParam("assoName");
		String amount = ctx.formParam("reqAmount");
		String status = ctx.formParam("reqMessage");
		
		try {
			double numberAmount = Double.parseDouble(amount);
			TypeOffer r = new TypeOffer(fullName, numberAmount, status);
			AssociateOfferImp repo = new AssociateOfferImp();
			repo.makeOffer(r);
			Logger myLogger = LoggerFactory.getLogger("infoLogger");
			myLogger.info("Reimbursement request for " + String.format("%.2f", r.retreiveAmount()) + " submitted by " + r.retreiveFullName() + " for " + r.retreiveStatus().toLowerCase() + ".");
			ctx.html("Offer submitted successfully");
		} catch (NumberFormatException e) {
			ctx.html("Invalid input. Request not created");
		} 
	};
	
	public static Handler fetchAverageAmountReq = ctx -> {
		AssociateOfferImp repo = new AssociateOfferImp();
		double avgReqAmt = repo.averageCost();
		ctx.html(String.format("%.2f", avgReqAmt));
	};
	
	public static Handler fetchNumberOffers = ctx -> {
		AssociateOfferImp repo = new AssociateOfferImp();
		int numOffers = repo.numberOffers();
		ctx.html(String.valueOf(numOffers));
	}; 
	
	public static Handler fetchHighestAmount = ctx -> {
		AssociateOfferImp repo = new AssociateOfferImp();
		TypeOffer r = repo.TopSpender();
		ctx.json(r);
	}; 
	
	public static Handler changeRequest = ctx -> {
		TypeOffer r = ctx.bodyAsClass(TypeOffer.class);
		AssociateOfferImp repo = new AssociateOfferImp();
		if (r.retreiveStatus().equals("Approved") || r.retreiveStatus().equals("Denied")) {
			repo.updateOffer(r);
			Logger myLogger = LoggerFactory.getLogger("infoLogger");
			if(r.retreiveStatus().equals("Approved")) {
				myLogger.info("Reimbursement request for " + r.retreiveMessage().toLowerCase() + " submitted by " + r.retreiveFullName() + " has been approved");
				ctx.html("Reimbursement request for " + r.retreiveMessage().toLowerCase() + " submitted by " + r.retreiveFullName() + " has been approved");
			} else if (r.retreiveStatus().equals("Denied")) {
				myLogger.info("Reimbursement request for " + r.retreiveMessage().toLowerCase() + " submitted by " + r.retreiveFullName() + " has been denied");
				ctx.html("Reimbursement request for " + r.retreiveMessage().toLowerCase() + " submitted by " + r.retreiveFullName() + " has been denied");
			} 
		} else {
			ctx.html("Action failed");
		} 
	}; 
	
	public static Handler fetchAllOffers = ctx -> {
		AssociateOfferImp repo = new AssociateOfferImp();
		Iterable<TypeOffer> allOffers = repo.findAllRequests();
		ctx.json(allOffers);
	};
	
	public static Handler fetchEmployeeRequests = ctx -> {
		Associate r = ctx.bodyAsClass(Associate.class);
		AssociateOfferImp repo = new AssociateOfferImp();
		Iterable<TypeOffer> employeeRequests = repo.findOffersAssociateName(r.retreivefullName());
		ctx.json(employeeRequests);
	}; 
	
	public static Handler fetchPendingRequests = ctx -> {
		AssociateOfferImp repo = new AssociateOfferImp();
		Iterable<TypeOffer> allRequests = repo.findPendingOffers();
		ctx.json(allRequests);
	}; 
	
	public static Handler fetchEmployeeByName = ctx -> {
		 
		String fullName = ctx.formParam("username");
		String pass = ctx.formParam("password");
		
		AssociateOfferImp repo = new AssociateOfferImp();
		Associate user = repo.locateAssociateName(fullName);
		if (user == null) {
			ctx.html("Username not found");
		} else if (!pass.equals(user.retreivePassword())) {
			ctx.html("Password incorrect");
		} else {
			ctx.json(user);
		} 
	}; 
	

}
