package service;

import db.DBConn;
import models.PastRequests;


public class PRService {
	private DBConn conn = new DBConn();
	
	public void save(PastRequests pr) {
		this.conn.save(pr);
	}
}
