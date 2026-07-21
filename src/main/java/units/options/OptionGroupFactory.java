package units.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.options.requirements.Requirement;

public class OptionGroupFactory {
	
	private static final Map<String, OptionGroup> registry = new HashMap<>();
	
	public static OptionGroup get(
			String name,
			List<OptionChoice> choices,
			List<Requirement> requirements,
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
	
	public static List<OptionGroup> get(List<String> names){
		List<OptionGroup> groups = new ArrayList<>();
		for (String name : names) {
			groups.add(get(name));
		}
		return groups;
	}
}
