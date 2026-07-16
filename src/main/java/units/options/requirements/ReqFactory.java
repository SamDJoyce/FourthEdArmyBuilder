package units.options.requirements;

import java.util.List;

import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.options.OptionChoice;

public class ReqFactory {
	
	public static CharacterOnlyReq get() {
		return  new CharacterOnlyReq();
	}
	
	public static MutualExclusionReq get(List<OptionChoice> excluded) {
		return new MutualExclusionReq(excluded);
	}
	
	public static MaxSelectionReq get(int maxSelection) {
		return new MaxSelectionReq(maxSelection);
	}
	
	public static MaxPerModelCountReq get(
			ModelDescription model,
			int rate) {
		return new MaxPerModelCountReq(model,rate);
	}
	
	public static ModelCountReq get(
			ModelDescription model,
			int minimum,
			int maximum
			) {
		return new ModelCountReq(
				model,
				minimum,
				maximum);
	}
	
	public static MustHaveTypeReq get(UnitType type){
		return new MustHaveTypeReq(type);
	}
}
