package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * If you want Hibernate to associate a Java model with a table in your DB, you should
 * annotate your Java models with annotations provided by the JPA (Java Persistence API).
 * The Java Persistence API provides an interface for object relational mapping (ORM) frameworks
 * such as Hibernate.
 * 
 * Remember that Hibernate is designed to help us fix a "paradigm mismatch" between SQL
 * and Java. As such, we don't have to write any SQL in order to persist records. We can
 * instead let Hibernate take care of generating the queries. In order for this to happen,
 * we have to provide some "mappings" to Hibernate so that the ORM framework knows how a 
 * Java model correlates to a table in our DB. 
 */

/*
 * This annotation marks this class as an "entity", meaning that we intend to map this class
 * to a table in our DB.
 */
@Entity

/*
 * The Table annotation allows us to specify information about the table we want associated
 * with our model. You can, for instance, specify the table's name (DB table). That said,
 * you don't have to specify the table name as Hibernate will just use the model's name
 * as the table name if you don't specify a name.
 */
@Table(name = "hibernate_ingredient")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

	/*
	 * The Id annotation denotes that we wish to use this field as the primary on the
	 * table that is generated.
	 */
	@Id
	/*
	 * The Column annotation denotes that this field should be a column on my table.
	 */
	@Column(name = "ingredient_id")
	/*
	 * Because our primary keys are generated for us by the serial data type's underlying
	 * sequences, we also want to specify that this column is a generated value.
	 */
	@GeneratedValue(generator = "ingredient_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "ingredient_id_seq", sequenceName = "ingredient_id_seq")
	private int id;
	@Column
	private String name;
	/**
	 * Sweet, spicy, umami, salty
	 */
	@Column
	private String flavor;
	@Column
	private float cost;
}
