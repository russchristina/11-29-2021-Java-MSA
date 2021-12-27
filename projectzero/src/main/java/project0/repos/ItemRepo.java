package project0.repos;

import java.util.List;

import project0.models.Item;

public interface ItemRepo {

Item findById(int id);


Item findByName(String name);


Item findByType(String type);


Item findByMaterial(String material);


List<Item> findAll();



	
	
}
