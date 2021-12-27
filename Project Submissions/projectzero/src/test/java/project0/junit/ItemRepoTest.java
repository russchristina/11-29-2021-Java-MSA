package project0.junit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import project0.implement.ItemRepoImpl;
import project0.models.Item;
import project0.repos.ItemRepo;

@TestInstance(Lifecycle.PER_CLASS)
public class ItemRepoTest {

	private ItemRepo itemRepo;
	
	
	@BeforeAll
	public void setup() {
		itemRepo = new ItemRepoImpl();
	}
	@Test
	public void testFindAllItems() {
		List<Item> retrievedItems = itemRepo.findAll();
		Assertions.assertNotNull(retrievedItems);
		Assertions.assertEquals(30, retrievedItems.size());
	}
}
