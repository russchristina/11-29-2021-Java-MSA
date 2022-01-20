package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursements_Sam;

public interface SamRepository {

    List<Reimbursements_Sam> findAllSam();

    void submitReimbursement(Reimbursements_Sam reimbursements);

}
