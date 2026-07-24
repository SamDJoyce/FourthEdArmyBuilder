package units.options;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import units.options.requirements.Requirement;

public class OptionGroupFactory {
	
	private static final Map<String, OptionGroup> registry = new HashMap<>();
	
	public static OptionGroup create(
			String name,
			Set<OptionChoice> choices,
			Set<Requirement> requirements,
			int minSelections,
			int maxSelections ) {
		return registry.computeIfAbsent(name,
	            key -> new OptionGroup(
	            			name, 
	            			choices, 
	            			requirements, 
	            			minSelections, 
	            			maxSelections));
	}
	
	public static OptionGroup get(String name) {
		return registry.get(name);
	}
	
	public static Set<OptionGroup> get(Set<String> names){
		Set<OptionGroup> groups = new HashSet<>();
		for (String name : names) {
			groups.add(get(name));
		}
		return groups;
	}
}
