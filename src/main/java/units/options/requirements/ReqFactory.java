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
	            key -> new CharactersOnlyReq(name));
	}
	
	public static Requirement mutualExclusion(String name) {
		return registry.computeIfAbsent(name,
	            key -> new MutualExclusionReq(name));
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
			int rate) {
		return registry.computeIfAbsent(name,
	            key -> new MaxPerModelCountReq(name, rate));
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
			int minimum,
			int maximum
			) {
		return registry.computeIfAbsent(name,
	            key -> new ModelCountReq(
				name,
				minimum,
				maximum));
	}
	
	public static Requirement mustHaveType(String name, UnitType type){
		return registry.computeIfAbsent(name,
	            key -> new MustHaveTypeReq(name,type));
	}
	
	public static Requirement mustHaveGear(String name) {
		return registry.computeIfAbsent(name,
	            key -> new MustHaveGearReq(name));
	}
	
	public static Requirement mustHaveGear(String name, WargearDescription requiredGear) {
		return registry.computeIfAbsent(name,
	            key -> new MustHaveGearReq(name, requiredGear));
	}
	
	public static Requirement cannotHaveGear(String name) {
		return registry.computeIfAbsent(name,
	            key -> new CannotHaveGearReq(name));
	}
	
	public static Requirement cannotHaveGear(String name, WargearDescription blockingGear) {
		return registry.computeIfAbsent(name,
	            key -> new CannotHaveGearReq(name, blockingGear));
	}
	
	public static Requirement armouryPointsLimit(String name, int limit) {
		return registry.computeIfAbsent(name,
				key -> new ArmouryPointsLimitReq(name, limit));
	}
	
	public static Requirement get(String name) {
		return registry.get(name);
	}
	
	public static Set<Requirement> getAll(Set<String> names){
		Set<Requirement> reqs = new HashSet<>();
		for (String name : names) {
			reqs.add(get(name));
		}
		return reqs;
	}
	
	public static Map<String, Requirement> getRegistry(){
		return registry;
	}
	
	public static void clearRegistry() {
		registry.clear();
	}
}
