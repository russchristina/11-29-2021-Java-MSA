package db;

import java.util.List;

import models.RepayForm;

public interface FormRepository {
	
	List<RepayForm> selectRequestsById(int empId);
	
	List<RepayForm> selectAllNullRequests();
	
	List<RepayForm> selectAllRequests();
	
	RepayForm selectForm(int formId);
	
	void save(RepayForm form);
	
	void merge(RepayForm form);
}
