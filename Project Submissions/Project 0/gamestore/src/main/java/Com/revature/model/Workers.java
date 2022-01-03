package Com.revature.model;

public class Workers {

	private int workID;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + workID;
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
		Workers other = (Workers) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (workID != other.workID)
			return false;
		return true;
	}

	public int getWorkID() {
		return workID;
	}

	public void setWorkID(int workID) {
		this.workID = workID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	private String firstName;
	@Override
	public String toString() {
		return "Workers [workID=" + workID + ", firstName=" + firstName + ", city=" + city + ", task=" + task + "]";
	}

	public Workers() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String city;
	private String task;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
