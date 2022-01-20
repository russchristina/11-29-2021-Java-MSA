package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class RepayForm implements Serializable {
	@Id
	@Column(name="pKey")
	@GeneratedValue(generator="pKey_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, sequenceName = "pKey_seq", name = "pKey_seq")
	private int pKey;
	@Column(name="fKey")
	private int fKey;
	@Column(name="amount")
	private float amount;
	@Column(name="memo")
	private String memo;
	@Column(name="approval")
	private boolean approval;
	
	public RepayForm() {
		super();
	}

	public RepayForm(int pKey, int fKey, float amount, String memo, boolean approval) {
		super();
		this.pKey = pKey;
		this.fKey = fKey;
		this.amount = amount;
		this.memo = memo;
		this.approval = approval;
	}



	public int getpKey() {
		return pKey;
	}



	public void setpKey(int pKey) {
		this.pKey = pKey;
	}



	public int getfKey() {
		return fKey;
	}



	public void setfKey(int fKey) {
		this.fKey = fKey;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}



	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
	}



	public boolean isApproval() {
		return approval;
	}



	public void setApproval(boolean approval) {
		this.approval = approval;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + fKey;
		result = prime * result + (approval ? 1231 : 1237);
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
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (fKey != other.fKey)
			return false;
		if (approval != other.approval)
			return false;
		if (pKey != other.pKey)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RepayForm [requestId=" + pKey + ", employeeId=" + fKey + ", amount=" + amount + ", comment="
				+ memo + ", manager=" + approval + "]";
	}
}
