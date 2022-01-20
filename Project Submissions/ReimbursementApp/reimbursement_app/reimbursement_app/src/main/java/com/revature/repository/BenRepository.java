package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursements_Ben;

public interface BenRepository {

    List<Reimbursements_Ben> findAllBen();

    void submitReimbursement(Reimbursements_Ben reimbursements);

}
