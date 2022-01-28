package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Author;
import com.revature.model.Recipe;

/*
 * Spring Data JPA provides pre-implemented CRUD methods that eliminate our need
 * to write boilerplate code. It actually provides several interfaces that allow
 * us to take advantage of this functionality. One such interface is the
 * CrudRepository. You might also see the JpaRepository.
 * 
 * Note that when you extend the JpaRepository, you should provide the type
 * that you'll be working with within your repository of the type of unique
 * identifiers used for instances.
 */
@Repository("recipeRepository")
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	List<Recipe> findAll();
	<S extends Recipe> S save(Recipe recipe);
	List<Recipe> findAllByAuthor(Author author);
	List<Recipe> findAllByCooktimeinminutesLessThan(int cooktimeinminutes);
}
