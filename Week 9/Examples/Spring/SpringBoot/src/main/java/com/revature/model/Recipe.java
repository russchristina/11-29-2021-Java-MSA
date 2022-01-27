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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * As a bonus tool, you can use Lombok to automatically generate boilerplate
 * code at runtime. This boilerplate code includes getters, setters, constructors,
 * toString methods, equals and hashCode methods. Basically your entire JavaBean.
 * 
 * All you need is to 1)install support for Lombok in your IDE and 2) annotate
 * your class with Lombok annotations.
 */

@Entity

@Table(name = "hibernate_recipe")

//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString

/*
 * Note that @Data is a combination of @Getter, Setter, @ToString, @EqualsAndHashCode,
 * and @RequiredArgsConstructor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
