package Com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	Main ob =  new Main();
	Main obj =  new Main();
	Customer obj1 = new Main();
	@Test
	void MainAddToList()
	{
		ob.Gbag.add("Farcry5");
		ob.Gbag.add("COD13");
		ob.Gbag.add("Perfect Dark");
		assertEquals("Adding 3 Games to list", 3, ob.Gbag.size());  
		ob.Gbag.clear();
	}
    @Test 
	void TestRemoveFromList()
	{obj.Gbag.add("Farcry5");
	 obj.Gbag.add("COD13");
	 obj.Gbag.remove(1);
	 assertEquals("Removing one game out of Game bag", 1, obj.Gbag.size()); 
	}
    

}
