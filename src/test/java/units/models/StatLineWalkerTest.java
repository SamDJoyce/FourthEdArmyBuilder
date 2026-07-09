package units.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatLineWalkerTest {
	private static String   ID = "testID";
	private static String   NAME = "testName";
	private static String   WALKER = "walker";
	private 	   String   testString;
	private        StatLine s;
	@BeforeEach
	void setUp() throws Exception {
		s = new StatLineWalker(ID, NAME, 1, 2, 3, 4, 5, 10, 11, 12);
		testString = new String();
	}

	@Test
	void testStatLineCreation() {
		assertNotNull(s);
	}
	
	@Test
	void testStatOutput() {
		//System.out.print(s.statsToString());
		testString = s.statsToString();
		assertNotNull(testString);
	}
	
	@Test
	void testUnusedStatsAreZero() {
		assertEquals(0, s.getT());
		assertEquals(0, s.getLd());
		assertEquals(0, s.getSv());
	}

	@Test
	void testStatsAreValid() {
		assertEquals(ID,s.getId());
		assertEquals(NAME,s.getName());
		assertEquals(1, s.getWs());
		assertEquals(2, s.getBs());
		assertEquals(3, s.getS());
		assertEquals(4, s.getI());
		assertEquals(5, s.getA());
		assertEquals(10,s.getFront());
		assertEquals(11,s.getSide());
		assertEquals(12,s.getRear());
	}
	
}
