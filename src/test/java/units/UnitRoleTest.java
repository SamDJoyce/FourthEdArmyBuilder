package units;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitRoleTest {
	
	private static final String HQ 			  = "hq";
	private static final String ELITES 		  = "elites";
	private static final String TROOPS 		  = "troops";
	private static final String FAST_ATTACK   = "fast attack";
	private static final String HEAVY_SUPPORT = "heavy support";
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFromString() {
		assertEquals(UnitRole.fromString(HQ), UnitRole.HQ);
		assertEquals(UnitRole.fromString(ELITES), UnitRole.ELITES);
		assertEquals(UnitRole.fromString(TROOPS), UnitRole.TROOPS);
		assertEquals(UnitRole.fromString(FAST_ATTACK), UnitRole.FAST_ATTACK);
		assertEquals(UnitRole.fromString(HEAVY_SUPPORT), UnitRole.HEAVY_SUPPORT);
	}
	
	@Test
	void testIsRole() {
		UnitRole role = UnitRole.HQ;
		assertTrue(role.isRole(UnitRole.HQ));
		assertTrue(role.isHq());
		
		role = UnitRole.ELITES;
		assertTrue(role.isElites());
		
		role = UnitRole.TROOPS;
		assertTrue(role.isTroops());
		
		role = UnitRole.FAST_ATTACK;
		assertTrue(role.isFastAttack());
		
		role = UnitRole.HEAVY_SUPPORT;
		assertTrue(role.isHeavySupport());
	}

}
