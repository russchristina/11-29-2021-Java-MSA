package tester;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table
public class TestItem {

	@Id
	@Column
	private String name;

	public TestItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestItem(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestItem other = (TestItem) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "TestItem [name=" + name + "]";
	}
	
	
}
