package units.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static List<OptionChoice> get(List<String> names){
		List<OptionChoice> choices = new ArrayList<>();
		for (String name : names) {
			choices.add(get(name));
		}
		return choices;
	}
}
