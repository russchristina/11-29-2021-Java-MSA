package tester;

import java.util.List;


public class TestService {
	
	private TestRepo testRepo;
	
	public TestService() {
		this.testRepo = new testImpl();
	}
	
	public void save(TestItem testItem) {
		this.testRepo.save(testItem);
	}
	
	public List<TestItem> findAll(){
		return this.testRepo.findAll();
	}
	

}
