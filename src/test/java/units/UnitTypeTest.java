package units;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTypeTest {

	private static final String INFANTRY 		   = "infantry";
	private static final String JUMP 		  	   = "jump";
	private static final String CAVALRY 		   = "cavalry";
	private static final String BIKE   			   = "bike";
	private static final String ARTILLERY 		   = "artillery";
	private static final String MONSTROUS_CREATURE = "monstrous creature";
	private static final String CHARACTER 		   = "character";
	private static final String VEHICLE 		   = "vehicle";
	private static final String VEHICLE_WALKER 	   = "walker";
	private static final String VEHICLE_TRANSPORT  = "transport";
	
	private UnitType type;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFromString() {
		assertEquals(UnitType.fromString(INFANTRY), UnitType.INFANTRY);
		assertEquals(UnitType.fromString(JUMP), UnitType.JUMP);
		assertEquals(UnitType.fromString(CAVALRY), UnitType.CAVALRY);
		assertEquals(UnitType.fromString(BIKE), UnitType.BIKE);
		assertEquals(UnitType.fromString(ARTILLERY), UnitType.ARTILLERY);
		assertEquals(UnitType.fromString(MONSTROUS_CREATURE), UnitType.MONSTROUS_CREATURE);
		assertEquals(UnitType.fromString(CHARACTER), UnitType.CHARACTER);
		assertEquals(UnitType.fromString(VEHICLE), UnitType.VEHICLE);
		assertEquals(UnitType.fromString(VEHICLE_WALKER), UnitType.WALKER);
		assertEquals(UnitType.fromString(VEHICLE_TRANSPORT), UnitType.TRANSPORT);
	}
	
	@Test
	void testIsType() {
		type = UnitType.INFANTRY;
		assertTrue(type.isType(UnitType.INFANTRY));
		assertTrue(type.isInfantry());
		
		type = UnitType.JUMP;
		assertTrue(type.isJump());
		
		type = UnitType.CAVALRY;
		assertTrue(type.isCavalry());
		
		type = UnitType.BIKE;
		assertTrue(type.isBike());
		
		type = UnitType.ARTILLERY;
		assertTrue(type.isArtillery());
		
		type = UnitType.MONSTROUS_CREATURE;
		assertTrue(type.isMonstrousCreature());
		
		type = UnitType.CHARACTER;
		assertTrue(type.isCharacter());
		
		type = UnitType.VEHICLE;
		assertTrue(type.isVehicle());
		
		type = UnitType.WALKER;
		assertTrue(type.isWalker());
		
		type = UnitType.TRANSPORT;
		assertTrue(type.isTransport());
	}

}
