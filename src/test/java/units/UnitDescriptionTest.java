package units;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import units.models.ModelDescFactory;
import units.models.ModelDescription;
import units.models.StatLine;
import units.models.StatLineInfantry;
import units.wargear.WargearDescription;

class UnitDescriptionTest {
	private static String     UNIT_ID = "unitID";
	private static String     UNIT_NAME = "unitName";
	private static String     MODEL_ID = "modelID";
	private static String     MODEL_NAME = "modelName";
	private static UnitType   INFANTRY = UnitType.INFANTRY;
	private static UnitType   CHARACTER = UnitType.CHARACTER;
	private static UnitType   VEHICLE = UnitType.VEHICLE;
	private static int        POINTS = 10;
	private static int		  MIN = 1;
	private static int		  MAX = 3;	
	private static UnitType[] TYPES = {INFANTRY, CHARACTER};
	private static UnitRole   HQ = UnitRole.HQ;
	
	private StatLine s;
	private	Set<UnitType> t;
	private	Set<WargearDescription> g;
	private ModelDescription m;
	private UnitDescription u;
	
	@BeforeEach
	void setUp() {
		s = new StatLineInfantry(MODEL_ID,MODEL_NAME, 1,2,3,4,5,6,7,8,9);
		t = new HashSet<>(Arrays.asList(TYPES));
		g = new HashSet<>();
		m = ModelDescFactory.get(MODEL_ID, MODEL_NAME, POINTS, s, t, g);
		Map<ModelDescription, Integer> models = new HashMap<>();
		models.put(m, MIN);
		
		u = new UnitDescription.Builder()
							.setId(UNIT_ID)
							.setName(UNIT_NAME)
							.setMinSize(MIN)
							.setMaxSize(MAX)
							.setRole(HQ)
							.setModels(models)
							.build();
	}

	@Test
	void testUnitCreatedSuccessfully() {
		assertNotNull(m);
	}
	
	@Test
	void testFieldsAreSet() {
		assertEquals(UNIT_ID,u.getId());
		assertEquals(UNIT_NAME,u.getName());
		assertEquals(HQ, u.getRole());
		assertEquals(MAX,u.getMaxSize());
		assertEquals(MIN,u.getMinSize());
		assertEquals(MIN,u.getCurrentSize());
		assertTrue(u.isType(INFANTRY));
		assertTrue(u.isType(CHARACTER));
		assertFalse(u.isType(VEHICLE));
	}
	
	@Test
	void testSizeFunctions() {
		assertTrue(u.sizeIsValid());
		assertTrue(u.canAddModel());
		assertFalse(u.canRemoveModel());
	}
	
	@Test
	void testAddRemoveSameModel() {
		assertTrue(u.containsModel(m));
		assertEquals(MIN, u.getCurrentSize());

		try {
			u.addModel(m);
		} catch (Exception e) {}
		assertEquals(MIN + 1, u.getCurrentSize());
		assertNotEquals(MIN, u.getCurrentSize());
		try {
			u.removeModel(m);
		} catch (Exception e) {}
		assertEquals(MIN, u.getCurrentSize());
		assertNotEquals(MIN + 1, u.getCurrentSize());
		

	}
	
	@Test
	void testAddRemoveDifferentModel() {
		ModelDescription m2 = ModelDescFactory.get(
				MODEL_ID + "2", 
				MODEL_NAME + "2", 
				POINTS, 
				s, 
				t, 
				g);
		try {
			u.addModel(m2);
		} catch (Exception e) {}
		assertEquals(MIN + 1, u.getCurrentSize());
		assertNotEquals(MIN, u.getCurrentSize());
		try {
			u.removeModel(m2);
		} catch (Exception e) {}
		assertEquals(MIN, u.getCurrentSize());
		assertNotEquals(MIN + 1, u.getCurrentSize());
	}
	
	@Test
	void testPointCalc() {
		assertEquals(POINTS, u.getPoints());
	}

}
