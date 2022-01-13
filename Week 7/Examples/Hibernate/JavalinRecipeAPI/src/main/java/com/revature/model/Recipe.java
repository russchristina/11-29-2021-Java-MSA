package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name = "hibernate_recipe")
public class Recipe {

	@Id
	@Column
	@GeneratedValue(generator = "recipe_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "recipe_id_seq", sequenceName = "recipe_id_seq")
	private int id;
	@Column
	private String name;
	private int cookTimeInMinutes;
	/*
	 * If you wish to create a foreign key, you need only specify the type directly
	 * here and use the ManyToOne annotation.
	 */
	@ManyToOne
	private Author author;
	/*
	 * This should be a many-to-many relationship between recipe and ingredient.
	 * There is a many-to-many annotation for this.
	 * 
	 * Note that we have specified the fetch type as "eager". This means that Hibernate will
	 * automatically pull all of the associated Ingredients when a recipe is pulled from
	 * the DB. It should be noted that single associations use eager fetching by default
	 * but collections use lazy loading by default.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	/*
	 * We also want Hibernate to create a bridge table between these two entities.
	 * In order to do so, we can use the @JoinTable annotation. As a general note, Hibernate
	 * much prefers Set to List for nested/embedded collections.
	 */
	@JoinTable(joinColumns = {@JoinColumn(name = "recipe_id")}, 
	inverseJoinColumns = {@JoinColumn(name= "ingredient_id")})
	private Set<Ingredient> ingredients;

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(int id, String name, int cookTimeInMinutes, Author author, Set<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.cookTimeInMinutes = cookTimeInMinutes;
		this.author = author;
		this.ingredients = ingredients;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + cookTimeInMinutes;
		result = prime * result + id;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (cookTimeInMinutes != other.cookTimeInMinutes)
			return false;
		if (id != other.id)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
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
		return "Recipe [id=" + id + ", name=" + name + ", cookTimeInMinutes=" + cookTimeInMinutes + ", author=" + author
				+ ", ingredients=" + ingredients + "]";
	}
}
