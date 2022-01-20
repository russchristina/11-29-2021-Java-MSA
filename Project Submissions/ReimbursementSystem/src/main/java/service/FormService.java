package service;

import java.util.List;

import db.DBConn;

import models.RepayForm;

public class FormService {
	DBConn conn = new DBConn();
	
	public void save(RepayForm form) {
		conn.save(form);
	}
	
	public List<RepayForm> selectAllRequests(){
		return conn.selectAllRequests();
	}
	
	public List<RepayForm> selectRequestsById(int empId){
		return conn.selectRequestsById(empId);
	}
	
	public List<RepayForm> selectAllNullRequests(){
		return conn.selectAllNullRequests();
	}
	
	RepayForm selectForm(int formId) {
		return conn.selectForm(formId);
	}
	
	public void merge(RepayForm form) {
		conn.merge(form);
	}
}
