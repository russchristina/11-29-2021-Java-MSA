package models;

public class RepayForm {
	private int pKey;
	private int fKey;
	private float amount;
	private String comment;
	private boolean manager;
	
	public RepayForm() {
		super();
	}
	
	public RepayForm(int requestId, int employeeId, float amount, String comment) {
		super();
		this.pKey = requestId;
		this.fKey = employeeId;
		this.amount = amount;
		this.comment = comment;
	}

	public RepayForm(int requestId, int employeeId, float amount, String comment, boolean manager) {
		super();
		this.pKey = requestId;
		this.fKey = employeeId;
		this.amount = amount;
		this.comment = comment;
		this.manager = manager;
	}

	protected int getRequestId() {
		return pKey;
	}



	public void setRequestId(int requestId) {
		this.pKey = requestId;
	}



	public int getEmployeeId() {
		return fKey;
	}



	public void setEmployeeId(int employeeId) {
		this.fKey = employeeId;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	protected boolean isManager() {
		return manager;
	}



	public void setManager(boolean manager) {
		this.manager = manager;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + fKey;
		result = prime * result + (manager ? 1231 : 1237);
		result = prime * result + pKey;
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
		RepayForm other = (RepayForm) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (fKey != other.fKey)
			return false;
		if (manager != other.manager)
			return false;
		if (pKey != other.pKey)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RepayForm [requestId=" + pKey + ", employeeId=" + fKey + ", amount=" + amount + ", comment="
				+ comment + ", manager=" + manager + "]";
	}
}
