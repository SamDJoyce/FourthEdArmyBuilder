package units.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatLineVehicleTest {
	private static String   ID = "testID";
	private static String   NAME = "testName";
	private static String   VEHICLE = "vehicle";
	private 	   String   testString;
	private        StatLine s;
	
	@BeforeEach
	void setUp() {
		s = new StatLineVehicle(ID, NAME, 1, 10, 11, 12);
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
		s.setWs(99);
		assertEquals(0, s.getWs());
		s.setS(88);
		assertEquals(0, s.getS());
		s.setT(77);
		assertEquals(0, s.getT());
		s.setW(66);
		assertEquals(0, s.getW());
		s.setI(55);
		assertEquals(0, s.getI());
		s.setA(44);
		assertEquals(0, s.getA());
		s.setLd(33);
		assertEquals(0, s.getLd());
		s.setSv(22);
		assertEquals(0, s.getSv());
	}
	
	@Test
	void testStatsAreSet() {
		assertEquals(ID,s.getId());
		assertEquals(NAME,s.getName());
		assertEquals(1,s.getBs());
		assertEquals(10,s.getFront());
		assertEquals(11,s.getSide());
		assertEquals(12,s.getRear());
	}

	@Test
	void testGettersAndSetters() {
		String newName = "New Name";
		s.setName(newName);
		assertEquals(newName, s.getName());
		
		String newID = "New ID";
		s.setId(newID);
		assertEquals(newID, s.getId());
		
		int newBs = 99;
		s.setBs(newBs);
		assertEquals(newBs, s.getBs());
		
		int newFront = 88;
		s.setFront(newFront);
		assertEquals(newFront, s.getFront());
		
		int newSide = 77;
		s.setSide(newSide);
		assertEquals(newSide, s.getSide());
		
		int newRear = 66;
		s.setRear(newRear);
		assertEquals(newRear, s.getRear());


	}
	
}
