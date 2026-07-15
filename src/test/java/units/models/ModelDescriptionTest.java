package units.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import units.UnitType;
import units.descriptions.models.ModelDescFactory;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.models.StatLineInfantry;
import units.wargear.WargearDescription;

class ModelDescriptionTest {

	private static String   ID = "testID";
	private static String   NAME = "testName";
	private static UnitType INFANTRY = UnitType.INFANTRY;
	private static UnitType CHARACTER = UnitType.CHARACTER;
	private static int      POINTS = 10;
	private		   UnitType[] TYPES = {INFANTRY, CHARACTER};
	//private		   WargearDescription[] GEARSET;
	private        StatLine s;
	private		   Set<UnitType> t;
	private		   Set<WargearDescription> g;
	private		   ModelDescription m;

	
	@BeforeEach
	void setUp() throws Exception {
		s = new StatLineInfantry(ID,NAME, 1,2,3,4,5,6,7,8,9);
		t = new HashSet<>(Arrays.asList(TYPES));
		g = new HashSet<>();
		m = ModelDescFactory.get(ID, NAME, POINTS, s, t, g);
	}
	
	@Test
	void testModelDescCreation() {
		assertNotNull(m);
	}
	
	@Test
	void testFieldsAreSet() {
		assertEquals(ID,m.getId());
		assertEquals(NAME,m.getName());
		assertTrue(m.isType(INFANTRY));
		assertTrue(m.isType(CHARACTER));
	}
	
}
