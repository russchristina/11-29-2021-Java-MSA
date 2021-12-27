package project0.models;

import java.sql.Date;
import java.util.List;

public class Ledger {
	
	private int id;
	private String buyer;
	private List<String> invoice;
	private int charge;
	private Date date;
	
	
	public Ledger() {
		super();
	}


	public Ledger(int id, String buyer, List<String> invoice, int charge, Date date) {
		super();
		this.id = id;
		this.buyer = buyer;
		this.invoice = invoice;
		this.charge = charge;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public List<String> getInvoice() {
		return invoice;
	}


	public void setInvoice(List<String> invoice) {
		this.invoice = invoice;
	}


	public int getCharge() {
		return charge;
	}


	public void setCharge(int charge) {
		this.charge = charge;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + charge;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ledger other = (Ledger) obj;
		if (buyer == null) {
			if (other.buyer != null)
				return false;
		} else if (!buyer.equals(other.buyer))
			return false;
		if (charge != other.charge)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", buyer=" + buyer + ", invoice=" + invoice + ", charge=" + charge + ", date="
				+ date + "]";
	}

	
}
