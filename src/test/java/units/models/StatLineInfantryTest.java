package units.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatLineInfantryTest {
	private static String   ID = "testID";
	private static String   NAME = "testName";
	private static String   INFANTRY = "infantry";
	private 	   String   testString;
	private        StatLine s;

	@BeforeEach
	void setUp() throws Exception {
		s = new StatLineInfantry(ID,NAME, 1,2,3,4,5,6,7,8,9);
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
		assertEquals(0, s.getFront());
		assertEquals(0, s.getSide());
		assertEquals(0, s.getRear());
	}
	
	@Test
	void testUsedStatsAreValid() {
		assertEquals(ID,s.getId());
		assertEquals(NAME,s.getName());
		assertEquals(1,s.getWs());
		assertEquals(2,s.getBs());
		assertEquals(3,s.getS());
		assertEquals(4,s.getT());
		assertEquals(5,s.getW());
		assertEquals(6,s.getI());
		assertEquals(7,s.getA());
		assertEquals(8,s.getLd());
		assertEquals(9,s.getSv());
	}
	
	@Test
	void testTypeIsInfantry() {
		assertEquals(INFANTRY, s.getType());
	}

}
