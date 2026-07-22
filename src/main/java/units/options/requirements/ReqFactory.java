package units.options.requirements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;

public class ReqFactory {
	
	private static final Map<String, Requirement> registry = new HashMap<>();
	
	public static Requirement charactersOnly(String name) {
		return registry.computeIfAbsent(name,
	            key -> new CharacterOnlyReq(name));
	}
	
	public static Requirement mutualExclusion(String name, Set<OptionChoice> excluded) {
		return registry.computeIfAbsent(name,
	            key -> new MutualExclusionReq(name,excluded));
	}
	
	public static Requirement mutualExclusion(String name, OptionChoice excluded) {
		return registry.computeIfAbsent(name,
	            key -> new MutualExclusionReq(name, Set.of(excluded)));
	}
	
	public static Requirement maxSelection(String name, int maxSelection) {
		return registry.computeIfAbsent(name,
	            key -> new MaxSelectionReq(name, maxSelection));
	}
	
	public static Requirement maxPerModelCount(
			String name,
			ModelDescription model,
			int rate) {
		return registry.computeIfAbsent(name,
	            key -> new MaxPerModelCountReq(name, model,rate));
	}
	
	public static Requirement modelCount(
			String name,
			ModelDescription model,
			int minimum,
			int maximum
			) {
		return registry.computeIfAbsent(name,
	            key -> new ModelCountReq(
				name,
				model,
				minimum,
				maximum));
	}
	
	public static Requirement mustHaveType(String name, UnitType type){
		return registry.computeIfAbsent(name,
	            key -> new MustHaveTypeReq(name,type));
	}
	
	public static Requirement mustHaveGear(String name, WargearDescription requiredGear) {
		return registry.computeIfAbsent(name,
	            key -> new MustHaveGearReq(name, requiredGear));
	}
	
	public static Requirement get(String name) {
		return registry.get(name);
	}
	
	public static Set<Requirement> get(Set<String> names){
		Set<Requirement> reqs = new HashSet<>();
		for (String name : names) {
			reqs.add(get(name));
		}
		return reqs;
	}
}
