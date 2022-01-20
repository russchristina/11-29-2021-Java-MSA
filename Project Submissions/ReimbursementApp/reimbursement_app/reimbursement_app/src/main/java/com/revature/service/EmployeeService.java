package com.revature.service;

import java.util.List;

import com.revature.model.Employee_login;
import com.revature.model.Reimbursements_Alli;
import com.revature.model.Reimbursements_Ben;
import com.revature.model.Reimbursements_Sam;
import com.revature.repository.AlliRepository;
import com.revature.repository.AlliRepositoryImpl;
import com.revature.repository.BenRepository;
import com.revature.repository.BenRepositoryImpl;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.repository.SamRepository;
import com.revature.repository.SamRepositoryImpl;

public class EmployeeService {

  AlliRepository alli = new AlliRepositoryImpl();
  BenRepository ben = new BenRepositoryImpl();
  SamRepository sam = new SamRepositoryImpl();
  EmployeeRepository employee = new EmployeeRepositoryImpl();

  public List<Reimbursements_Alli> findAllAlli() {
    return this.alli.findAllAlli();
  }

  public void submitReimbursement(Reimbursements_Alli reimbursements) {
    this.alli.submitReimbursement(reimbursements);
  }

  public List<Reimbursements_Ben> findAllBen() {
    return this.ben.findAllBen();
  }

  public void submitReimbursement(Reimbursements_Ben reimbursements) {
    this.ben.submitReimbursement(reimbursements);
  }

  public List<Reimbursements_Sam> findAllSam() {
    return this.sam.findAllSam();
  }

  public void submitReimbursement(Reimbursements_Sam reimbursements) {
    this.sam.submitReimbursement(reimbursements);
  }

  public List<Employee_login> findAllEmployeeLogin() {
    return this.employee.findAllEmployeeLogin();
  }

}
