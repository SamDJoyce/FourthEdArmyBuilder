package units.options.requirements;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import units.UnitRole;
import units.UnitType;
import units.descriptions.UnitDescFactory;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescFactory;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.models.StatLineFactory;
import units.wargear.WargearDescription;

class MinSquadSizeReqTest {

	private static final int MIN_SQUAD_SIZE = 5;
	
	private static String     UNIT_NAME = "unitName";
	private static String     MODEL_NAME = "modelName";
	private static UnitRole   TROOPS = UnitRole.TROOPS;
	private static UnitType   INFANTRY = UnitType.INFANTRY;
	private static int        POINTS = 10;
	private static int		  MIN_MODELS = 3;
	private static int		  MAX_MODELS = 10;	
	
	private UnitDescription unit;
	private ModelDescription model;
	private Requirement req;
	private StatLine stats;
	private	Set<UnitType> types;
	private	Set<WargearDescription> gear;
	private Map<ModelDescription, Integer> models;
	
	@BeforeEach
	void setUp() throws Exception {
		stats = StatLineFactory.get(MODEL_NAME, 1,2,3,4,5,6,7,8,9);
		types = new HashSet<>(Arrays.asList(INFANTRY));
		gear = new HashSet<>();
		model = ModelDescFactory.get(MODEL_NAME, POINTS, stats, types, gear);
		models = new HashMap<ModelDescription, Integer>();
		models.put(model, 5);
		
		unit = UnitDescFactory.get(UNIT_NAME, MIN_MODELS, MAX_MODELS, TROOPS, types, null, models);
		
		req = ReqFactory.get(MIN_SQUAD_SIZE, unit);
	}

	@Test
	void testIsSatisfied() {
		assertTrue(req.isSatisfied());
	}
	
	@Test
	void testIsNotSatisfied() {
		models.clear();
		models.put(model, 3);
		assertFalse(req.isSatisfied());
	}


}
