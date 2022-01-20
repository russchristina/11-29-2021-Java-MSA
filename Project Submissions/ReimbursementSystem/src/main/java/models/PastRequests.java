package models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="past_requests")
public class PastRequests implements Serializable {
	@Id
	@Column(name="pKey")
	@GeneratedValue(generator="pKey_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, sequenceName = "pKey_seq", name = "pKey_seq")
	private int pKey;
	@Column(name="fKey")
	private int fKey;
	@Column(name="approval")
	private boolean approval;
	
	public PastRequests() {
		super();
	}

	public PastRequests(int pKey, int fKey, boolean approval) {
		super();
		this.pKey = pKey;
		this.fKey = fKey;
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

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	@Override
	public int hashCode() {
		return Objects.hash(approval, fKey, pKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PastRequests other = (PastRequests) obj;
		return approval == other.approval && fKey == other.fKey && pKey == other.pKey;
	}

	@Override
	public String toString() {
		return "PastRequests [pKey=" + pKey + ", fKey=" + fKey + ", approval=" + approval + "]";
	}
	
	
}
