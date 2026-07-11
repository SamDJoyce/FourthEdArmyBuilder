package units.wargear;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WargearTypeTest {

	private static final String ONE_HANDED = "one handed";
	private static final String TWO_HANDED = "two handed";
	private static final String GEAR = "gear";
	private static final String VEHICLE_UPGRADE = "vehicle upgrade";
	private static final String RELIC = "relic";
	
	private WargearType type;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFromString() {
		assertEquals(WargearType.fromString(ONE_HANDED), WargearType.ONE_HANDED);
		assertEquals(WargearType.fromString(TWO_HANDED), WargearType.TWO_HANDED);
		assertEquals(WargearType.fromString(GEAR), WargearType.GEAR);
		assertEquals(WargearType.fromString(VEHICLE_UPGRADE), WargearType.VEHICLE_UPGRADE);
		assertEquals(WargearType.fromString(RELIC), WargearType.RELIC);
	}
	
	@Test
	void testIsType() {
		type = WargearType.RELIC;
		assertTrue(type.isType(WargearType.RELIC));
		assertTrue(type.isRelic());
		
		type = WargearType.GEAR;
		assertTrue(type.isGear());
		
		type = WargearType.ONE_HANDED;
		assertTrue(type.isOneHanded());
		
		type = WargearType.TWO_HANDED;
		assertTrue(type.isTwoHanded());
		
		type = WargearType.VEHICLE_UPGRADE;
		assertTrue(type.isVehicleUpgrade());
	}

}
