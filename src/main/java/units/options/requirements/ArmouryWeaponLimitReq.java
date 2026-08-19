package units.options.requirements;

import java.util.Set;

import roster.RosterResult;
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
	public RequirementResult isMet(SelectionContext context) {
		Set<WargearInstance> gear = context.getModel().getGear();
		int oneHanded = getCount(gear, WargearType.ONE_HANDED);
		int twoHanded = getCount(gear, WargearType.TWO_HANDED);
		int weaponCount = oneHanded + twoHanded;
		
		// May only have two weapons
		if (weaponCount + 1 > MAX_WEAPON_COUNT) {
			return RequirementResult.failure(String.format(
					"%s may not select another weapon", 
					context.getModel().getName()));
		}
		// May only have one two-handed weapon
		if (twoHanded  + 1 > MAX_TWO_HANDED) {
			return RequirementResult.failure(String.format(
					"%s may not select another two-handed weapon", 
					context.getModel().getName()));
		}
		return RequirementResult.success("May select another weapon");
	}
	
	@Override
	public RosterResult validate(SelectionContext context) {
		RosterResult result = RosterResult.create();
		
		Set<WargearInstance> gear = context.getModel().getGear();
		int oneHanded = getCount(gear, WargearType.ONE_HANDED);
		int twoHanded = getCount(gear, WargearType.TWO_HANDED);
		int weaponCount = oneHanded + twoHanded;
		
		// May only have two weapons
		if (weaponCount > MAX_WEAPON_COUNT) {
			result.addIssue(String.format(
					"%s has selected too many weapons (%d selected)", 
					context.getModel().getName(),
					weaponCount));
		}
		// May only have one two-handed weapon
		if (twoHanded > MAX_TWO_HANDED) {
			result.addIssue(String.format(
					"%s may have only one two-handed weapon (%d selected)", 
					context.getModel().getName(),
					twoHanded));
		}
		
		return result;
	}

	public String getName() {
		return name;
	}
	
	private int getCount(Set<WargearInstance> gear, WargearType type) {
		int count = 0;
		for (WargearInstance g : gear) {
			if (type.equals(g.getType())) {
				count++;
			}
		}
		return count;
	}

}
