

package pyramid;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPyramid {
	
	Pyramid p;
	
	@BeforeAll
	public void setup() {
		p = new Pyramid();
	}
	
	@Test
	public void testPyramid() {
		Assert.assertNotNull(p);
	}
	
}
