package units;

import java.util.HashSet;
import java.util.Set;

public enum UnitType {
	INFANTRY("Infantry"),
	JUMP("Jump"),
	CAVALRY("Cavalry"),
	BIKE("Bike"),
	ARTILLERY("Artillery"),
	MONSTROUS_CREATURE("Monstrous Creature"),
	CHARACTER("Character"),
	VEHICLE("Vehicle"),
	WALKER("Walker"),
	TRANSPORT("Transport");
	
	private final String type;
	UnitType(String type) {
		this.type = type;
	}
	
	public Boolean isType(UnitType type) {
		return this == type;
	}
	
	public static UnitType fromString(String s) {
		for (UnitType t : UnitType.values()) {
			if (t.toString().equalsIgnoreCase(s)) {
				return t;
			}
		}
		// Return Infantry by default
		return INFANTRY;
	}
	
	public static Set<UnitType> fromStrings(Set<String> typeNames){
		Set<UnitType> types = new HashSet<>();
		for (String typeName : typeNames) {
			types.add(UnitType.fromString(typeName));
		}
		return types;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
