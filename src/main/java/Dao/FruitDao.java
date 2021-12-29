package Dao;

import java.util.List;

import com.revature.model.Fruit;

public interface FruitDao {
	List<Fruit> findAll();

	void save(Fruit item);

	Fruit findById(int id);

	Fruit findByName(String username);

	void update(Fruit item);
	
	void delete(Fruit fruit);
}
