package dto;

public class ModifyStatEffectDTO extends EffectDTO {

	private String name;
	private String stat;
	private int modifier;
	
	public ModifyStatEffectDTO() {}
	
	public ModifyStatEffectDTO(String name, String stat, int modifier) {
		super("modify_stat");
		this.name = name;
		this.stat = stat;
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public String getStat() {
		return stat;
	}

	public int getModifier() {
		return modifier;
	}

}
