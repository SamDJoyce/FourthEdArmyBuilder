package units.options;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptionChoiceFactory {
	private static final Map<String, OptionChoice> registry = new HashMap<>();
	
	public static OptionChoice get(String name, int points) {
		return registry.computeIfAbsent(name,
	            key -> new OptionChoice(
	            			name, points
	            			));
	}
	
	public static OptionChoice get(String name) {
		return registry.get(name);
	}
	
	public static Set<OptionChoice> get(Set<String> names){
		Set<OptionChoice> choices = new HashSet<>();
		for (String name : names) {
			choices.add(get(name));
		}
		return choices;
	}
}
