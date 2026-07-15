package units.options.requirements;

import java.util.List;

import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.options.OptionChoice;
import units.options.OptionGroup;

public class ReqFactory {
	public static MinSquadSizeReq get(
			int minSize, 
			UnitDescription unit) {
		return new MinSquadSizeReq(unit,minSize);
	}
	
	public static CharacterOnlyReq get(
			ModelDescription model) {
		return  new CharacterOnlyReq(model);
	}
	
	public static MutualExclusionReq get(
			List<OptionChoice> blockers,
			OptionGroup group) {
		return new MutualExclusionReq(blockers, group);
	}
	
	public static MaxSelectionReq get(
			int maxSelection,
			OptionChoice choice,
			OptionGroup group) {
		return new MaxSelectionReq(maxSelection, choice, group);
	}
	
	public static ForEachMultipleReq get(
			UnitDescription unit,
			OptionChoice choice, 
			int divisor) {
		return new ForEachMultipleReq(unit, choice, divisor);
	}
}
