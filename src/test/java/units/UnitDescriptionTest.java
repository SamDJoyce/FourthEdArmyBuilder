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
	private static int        POINTS = 10;
	private static int		  MIN = 5;
	private static int		  MAX = 10;	
	private static UnitType[] TYPES = {INFANTRY, CHARACTER};
	private static UnitRole   ROLE = UnitRole.HQ;
	
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
		models.put(m, 1);
		
		u = new UnitDescription.Builder()
							.setId(UNIT_ID)
							.setName(UNIT_NAME)
							.setMinSize(MIN)
							.setMaxSize(MAX)
							.setRole(ROLE)
							.setModels(models)
							.build();
	}

	@Test
	void testUnitCreatedSuccessfully() {
		assertNotNull(m);
	}
	
	@Test
	void testFieldsAreSet() {
		
	}

}
