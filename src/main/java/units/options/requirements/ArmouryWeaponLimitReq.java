package units.options.requirements;

import java.util.Set;

import units.WargearType;
import units.instances.WargearInstance;
import units.options.SelectionContext;

public class ArmouryWeaponLimitReq implements Requirement {

	
	private final static int MAX_WEAPON_COUNT = 2;
	private final static int MAX_TWO_HANDED   = 1;
	
	private final String name;
	
	public ArmouryWeaponLimitReq(String name) {
		this.name = name;
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		Set<WargearInstance> gear = context.getModel().getGear();
		int oneHanded = 0;
		int twoHanded = 0;
		int weaponCount = oneHanded + twoHanded;
		
		for (WargearInstance g : gear) {
			if (WargearType.ONE_HANDED.equals(g.getType())) {
				oneHanded++;
			}
			if (WargearType.TWO_HANDED.equals(g.getType())) {
				twoHanded++;
			}
		}
		// May only have two weapons
		if (weaponCount > MAX_WEAPON_COUNT) {
			return RequirementResult.failure(String.format(
					"May only select 2 weapons (currently %d)", 
					weaponCount));
		}
		// May only have one two-handed weapon
		if (twoHanded > MAX_TWO_HANDED) {
			return RequirementResult.failure(String.format(
					"May only select 1 two-handed weapon (currently %d)", 
					twoHanded));
		}
		return RequirementResult.success("valid");
	}

	public String getName() {
		return name;
	}

}
