package tester;

import java.util.List;

public interface TestRepo {
	
	void save(TestItem testItem);

	List<TestItem> findAll();
	
}
