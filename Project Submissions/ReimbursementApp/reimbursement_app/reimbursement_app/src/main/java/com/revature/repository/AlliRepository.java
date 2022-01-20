package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursements_Alli;

public interface AlliRepository {

    List<Reimbursements_Alli> findAllAlli();

    void submitReimbursement(Reimbursements_Alli reimbursements);

}
