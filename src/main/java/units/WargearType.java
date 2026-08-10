package units;

public enum WargearType {
	ONE_HANDED("one handed"),
	TWO_HANDED("two handed"),
	GEAR("gear"),
	VEHICLE_UPGRADE("vehicle upgrade"),
	RELIC("relic"),
	PSYCHIC("psychic");
	
	private final String type;
	WargearType(String type){
		this.type = type;
	}
	
	public Boolean isType(WargearType type) {
		return this == type;
	}
	
	public static WargearType fromString(String type) {
		for (WargearType t : WargearType.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return t;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
