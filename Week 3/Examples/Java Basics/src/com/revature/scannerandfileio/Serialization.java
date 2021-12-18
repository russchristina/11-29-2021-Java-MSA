package com.revature.scannerandfileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/*
 * What is serialization? Serialization in this case refers to writing a Java object as
 * Java Byte Code. When you write an object as Java byte code, the state is preserved,
 * meaning that when you read it back in, the fields will still have their values.
 */
public class Serialization {

	/*
	 * So let's write an object as Java byte code!
	 */
	public static void main(String[] args) {
		
		/*
		 * Let's create an instance of Polkaman so that we can serialize it.
		 */
				
		Polkaman pekachu = new Polkaman(1, true, "Pekachu");
		
		/*
		 * For serialization, there are special types of input and output streams
		 * just for Java objects:
		 * 
		 * - ObjectOutputStream - writes objects as Java byte code
		 * - ObjectInputStream - reads Java byte into a Java byte code; this is called deserialization
		 * 
		 * Of course, we still need a file to write to and we need a FileOutputStream:
		 */
		
		File myFile = new File("polkaman.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(myFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pekachu);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Let's read Pekachu back out of the file:
		 */

		try {
			FileInputStream fis = new FileInputStream(myFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Polkaman retrievedPekachu = (Polkaman) ois.readObject();
			System.out.println(retrievedPekachu);
			fis.close();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

/*
 * If you wish to serialize an object, its type must be Serializable. This means that
 * the class you've created an instance of must implement the Serializable interface.
 * 
 * As a fun fact, Serializable is a completely empty interface. It is considered a
 * "Marker Interface" that only marks types that implement it for serialization.
 */
class Polkaman implements Serializable{
	private int age;
	private boolean isShiny;
	/*
	 * As a general, the "transient" keyword prevents a field from being serialized. Its
	 * value is not written if you choose to serialize an instance of this class.
	 */
	private transient String name;

	public Polkaman() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Polkaman(int age, boolean isShiny, String name) {
		super();
		this.age = age;
		this.isShiny = isShiny;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isShiny() {
		return isShiny;
	}

	public void setShiny(boolean isShiny) {
		this.isShiny = isShiny;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (isShiny ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Polkaman other = (Polkaman) obj;
		if (age != other.age)
			return false;
		if (isShiny != other.isShiny)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Polkaman [age=" + age + ", isShiny=" + isShiny + ", name=" + name + "]";
	}
}
