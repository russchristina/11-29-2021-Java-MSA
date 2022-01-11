package db;

import java.util.ArrayList;

import models.RepayForm;

public interface FormRepository {
	
	ArrayList<Integer> selectRequestsById(int empId);
	
	ArrayList<RepayForm> selectAllNullRequests();
	
	RepayForm selectForm(int formId);
	
	void insertForm(RepayForm form);
	
	void updateFormStatus(int formId, boolean approval);
}
