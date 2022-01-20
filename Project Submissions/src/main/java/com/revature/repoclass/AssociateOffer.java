package com.revature.repoclass;

import java.util.List;

import com.revature.models.Associate;
import com.revature.models.TypeOffer;

public interface AssociateOffer {

	List<TypeOffer> findOffersAssociateName(String fullName);

	void makeOffer(TypeOffer t);

	List<TypeOffer> findPendingOffers();

	List<TypeOffer> findAllRequests();

	double averageCost();

	int numberOffers();

	TypeOffer TopSpender();

	void updateOffer(TypeOffer t);

	Associate locateAssociateName(String fullName);

}
