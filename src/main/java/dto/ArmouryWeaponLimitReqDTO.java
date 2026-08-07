package dto;

public class ArmouryWeaponLimitReqDTO extends RequirementDTO {
	String name;
	
	public ArmouryWeaponLimitReqDTO() {}
	
	public ArmouryWeaponLimitReqDTO(String name) {
		super("armoury_weapon_limit");
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
