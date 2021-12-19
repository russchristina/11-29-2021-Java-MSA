package com.revature.designpatterns;

import java.io.Serializable;

/*
 * A JavaBean is a Java class that follows a standard design pattern. A design pattern is simply
 * convention; note that frameworks that you rely on (as well as other developers) generally expect
 * you to follow convention.
 * 
 * The design features of a Java Bean include:
 * 
 * - private fields
 * - getter and setter methods (accessors and mutators)
 * - a no args constructor
 * - a constructor using the fields
 * - hashCode and equals methods (overridden from the Object class)
 * - a toString method (overridden from the Object class)
 * - technically speaking, a Java Bean should implement the Serializable interface, but many
 * 		developers skip this because Serialization is not often used by most developers.
 */
public class JavaBean implements Serializable{

	private int fieldOne;
	private boolean fieldTwo;
	
	public JavaBean() {
		super();
	}
	
	public JavaBean(int fieldOne, boolean fieldTwo) {
		this.fieldOne = fieldOne;
		this.fieldTwo = fieldTwo;
	}
	
	/*
	 * Yes, you should name your getters and setters appropriately according to convention, though
	 * you don't have to.
	 */
	public int getFieldOne() {
		return fieldOne;
	}
	
	public void setFieldOne(int fieldOne) {
		/*
		 * You typically want to abstract this sanitization of the data out of this method, 
		 * but the point still remains: setters and getters allow for indirect access. This is
		 * safer than direct access as you can see below as it's not possible to ever set fieldOne
		 * to a value that is less than 0.
		 */
		if(fieldOne < 0) return;
		this.fieldOne = fieldOne;
	}

	public boolean isFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(boolean fieldTwo) {
		this.fieldTwo = fieldTwo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fieldOne;
		result = prime * result + (fieldTwo ? 1231 : 1237);
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
		JavaBean other = (JavaBean) obj;
		if (fieldOne != other.fieldOne)
			return false;
		if (fieldTwo != other.fieldTwo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JavaBean [fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo + "]";
	}
}

/*
 * We've made many POJOs (Plain Old Java Objects) over the last couple of weeks.
 */
class POJO{
	
	private int fieldOne;
	private boolean fieldTwo;
	
	public POJO(){
		
	}
}
