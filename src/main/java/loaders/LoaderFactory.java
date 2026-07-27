package loaders;

public class LoaderFactory {

	public static WargearLoader forWargear() {
		return new WargearLoader();
	}
	
	public static StatlineLoader forStats() {
		return new StatlineLoader();
	}
	
	public static ModelLoader forModels() {
		return new ModelLoader();
	}
	
	public static UnitLoader forUnits() {
		return new UnitLoader();
	}
	
	public static RequirementLoader forReqs() {
		return new RequirementLoader();
	}
	
	public static EffectLoader forEffects() {
		return new EffectLoader();
	}
	
	public static OptionChoiceLoader forChoices() {
		return new OptionChoiceLoader();
	}
	
	public static OptionGroupLoader forOptionGroups() {
		return new OptionGroupLoader();
	}
}
