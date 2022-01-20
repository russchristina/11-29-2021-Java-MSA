package com.revature.service;

import java.util.List;

import com.revature.model.Manager_login;
import com.revature.model.Reimbursements_Alli;
import com.revature.model.Reimbursements_Ben;
import com.revature.model.Reimbursements_Sam;
import com.revature.repository.ManagerRepository;
import com.revature.repository.ManagerRepositoryImpl;

public class ManagerService {

  private ManagerRepository managerRepository = new ManagerRepositoryImpl();

  // public List<Reimbursements> findAllReimburse() {
  // return this.managerRepository.findAllReimburse();
  // }

  // public List<Reimbursements> retrievePending() {
  // return this.managerRepository.retrievePending();
  // }

  public Reimbursements_Alli getAlliPendingOrPast(int id) {
    return this.managerRepository.getAlliPendingOrPast(id);
  }

  public void updateAlliApproveOrNotApprove(Reimbursements_Alli reimbursements_Alli) {
    managerRepository.updateAlliApproveOrNotApprove(reimbursements_Alli);
  }

  public Reimbursements_Ben getBenPendingOrPast(int id) {
    return this.managerRepository.getBenPendingOrPast(id);
  }

  public void updateBenApproveOrNotApprove(Reimbursements_Ben reimbursements_Ben) {
    managerRepository.updateBenApproveOrNotApprove(reimbursements_Ben);
  }

  public Reimbursements_Sam getSamPendingOrPast(int id) {
    return this.managerRepository.getSamPendingOrPast(id);
  }

  public void updateSamApproveOrNotApprove(Reimbursements_Sam reimbursements_Sam) {
    managerRepository.updateSamApproveOrNotApprove(reimbursements_Sam);
  }

  public List<Manager_login> findManagerLogin() {
    return this.managerRepository.findManagerLogin();
  }

}
