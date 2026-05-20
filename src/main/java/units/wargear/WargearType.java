package units.wargear;

public enum WargearType {
	ONE_HANDED("one handed"),
	TWO_HANDED("two handed"),
	GEAR("gear"),
	VEHICLE_UPGRADE("vehicle upgrade"),
	RELIC("relic");
	
	private final String type;
	WargearType(String type){
		this.type = type;
	}
	
	public Boolean isType(WargearType type) {
		return this == type;
	}
	
	public Boolean isOneHanded() {
		return this == WargearType.ONE_HANDED;
	}
	
	public Boolean isTwoHanded() {
		return this == WargearType.TWO_HANDED;
	}
	
	public Boolean isGear() {
		return this == WargearType.GEAR;
	}
	
	public Boolean isVehicleUpgrade() {
		return this == WargearType.VEHICLE_UPGRADE;
	}
	
	public Boolean isRelic() {
		return this == WargearType.RELIC;
	}
	
	public static WargearType fromString(String type) {
		for (WargearType t : WargearType.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return t;
			}
		}
		// TODO What should the default be?
		return null;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
