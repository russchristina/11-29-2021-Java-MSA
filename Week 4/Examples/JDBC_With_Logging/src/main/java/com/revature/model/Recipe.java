package com.revature.model;

import java.util.List;

public class Recipe {

	private int id;
	private String name;
	private int cookTimeInMinutes;
	private Author author;
	private List<Ingredient> ingredients;

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(int id, String name, int cookTimeInMinutes) {
		super();
		this.id = id;
		this.name = name;
		this.cookTimeInMinutes = cookTimeInMinutes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCookTimeInMinutes() {
		return cookTimeInMinutes;
	}

	public void setCookTimeInMinutes(int cookTimeInMinutes) {
		this.cookTimeInMinutes = cookTimeInMinutes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cookTimeInMinutes;
		result = prime * result + id;
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
		Recipe other = (Recipe) obj;
		if (cookTimeInMinutes != other.cookTimeInMinutes)
			return false;
		if (id != other.id)
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
		return "Recipe [id=" + id + ", name=" + name + ", cookTimeInMinutes=" + cookTimeInMinutes + "]";
	}
}
