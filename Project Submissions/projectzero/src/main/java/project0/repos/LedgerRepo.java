package project0.repos;

import java.sql.Date;

import project0.models.Ledger;

public interface LedgerRepo {
	
	Ledger findById(int id);
	
	
	Ledger findByBuyer(String buyer);
	

	Ledger findByDate(Date date);
	
	
}
