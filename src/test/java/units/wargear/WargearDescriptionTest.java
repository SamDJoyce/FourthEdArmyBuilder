package units.wargear;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WargearDescriptionTest {

	private static final String NAME = "testName";
	private static final String NEW_NAME = "new name";
	private static final WargearType TYPE = WargearType.ONE_HANDED;
	private static final WargearType NEW_TYPE = WargearType.RELIC;
	private static final int POINTS = 10;
	private static final int NEW_POINTS = 99;
	
	private WargearDescription wg;
	
	@BeforeEach
	void setUp(){
		wg = new WargearDescription(NAME,TYPE, POINTS);
	}

	@Test
	void testGearCreatedSuccessfully() {
		assertNotNull(wg);
	}
	
	@Test
	void testFieldsSetCorrectly() {
		assertEquals(NAME, wg.getName());
		assertEquals(TYPE, wg.getType());
		assertEquals(POINTS, wg.getPoints());
	}
	
	@Test
	void testNoPointsConstructor() {
		wg = new WargearDescription(NAME, TYPE);
		assertNotNull(wg);
		assertEquals(NAME, wg.getName());
		assertEquals(TYPE, wg.getType());
		assertEquals(0, wg.getPoints());
	}
	
	@Test
	void testNameFunctions() {
		wg.setName(NEW_NAME);
		assertNotEquals(NAME, wg.getName());
		assertEquals(NEW_NAME, wg.getName());
	}

	@Test
	void testTypeFunctions() {
		wg.setType(NEW_TYPE);
		assertNotEquals(TYPE, wg.getType());
		assertEquals(NEW_TYPE, wg.getType());
	}
	
	@Test
	void testPointsFunctions() {
		wg.setPoints(NEW_POINTS);
		assertNotEquals(POINTS, wg.getPoints());
		assertEquals(NEW_POINTS, wg.getPoints());
	}
	
}
