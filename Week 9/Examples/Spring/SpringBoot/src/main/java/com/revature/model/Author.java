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

@Entity

@Table(name = "hibernate_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	@Id
	@Column
	@GeneratedValue(generator = "author_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "author_id_seq")
	private int id;
	@Column
	private String name;
	@Column
	private String specialty;

}
