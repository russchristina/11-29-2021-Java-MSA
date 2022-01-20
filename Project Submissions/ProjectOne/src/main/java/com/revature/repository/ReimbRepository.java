package com.revature.repository;

import java.util.List;

import com.revature.model.Reimb;



public interface ReimbRepository {
//CRUD
	
	public void submitReimb (Reimb reimb);
	public void approveReimb (Reimb reimb);
	public void denyReimb (Reimb reimb);
	List<Reimb> findAll();
	List<Reimb> findPending();
	double	findHighest();
	double findAvg();
}
