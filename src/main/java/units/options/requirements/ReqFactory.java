package units.options.requirements;

import java.util.List;

import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;

public class ReqFactory {
	
	public static CharacterOnlyReq get() {
		return  new CharacterOnlyReq();
	}
	
	public static MutualExclusionReq mutualExclusion(List<OptionChoice> excluded) {
		return new MutualExclusionReq(excluded);
	}
	
	public static MaxSelectionReq maxSelection(int maxSelection) {
		return new MaxSelectionReq(maxSelection);
	}
	
	public static MaxPerModelCountReq maxPerModelCount(
			ModelDescription model,
			int rate) {
		return new MaxPerModelCountReq(model,rate);
	}
	
	public static ModelCountReq modelCount(
			ModelDescription model,
			int minimum,
			int maximum
			) {
		return new ModelCountReq(
				model,
				minimum,
				maximum);
	}
	
	public static MustHaveTypeReq mustHaveType(UnitType type){
		return new MustHaveTypeReq(type);
	}
	
	public static MustHaveGearReq mustHaveGear(WargearDescription requiredGear) {
		return new MustHaveGearReq(requiredGear);
	}
}
