package units.options.effects;

import roster.Roster;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectedOption;

public final class EffectContext {
	private final Roster roster;
	private final UnitInstance unit;
	private final ModelInstance model;
	private final SelectedOption option;
	
	private EffectContext(Builder builder) {
		this.roster = builder.roster;
		this.unit = builder.unit;
		this.model = builder.model;
		this.option = builder.option;
	}
	
	public Roster getRoster() {
		return this.roster;
	}
	
	public UnitInstance getUnit() {
		return this.unit;
	}
	
	public ModelInstance getModel() {
		return this.model;
	}
	
	public SelectedOption getChoice() {
		return this.option;
	}
	
	public boolean hasRoster() {
		return roster != null;
	}
	
	public boolean hasUnit() {
		return unit != null;
	}
	
	public boolean hasModel() {
		return model != null;
	}
	
	public boolean hasChoice() {
		return option != null;
	}
	
	public static EffectContext forRoster(Roster roster) {
		return new Builder()
					.setRoster(roster)
					.build();
	}
	
	public static EffectContext forUnit(UnitInstance unit) {
		return new Builder()
					.setUnit(unit)
					.build();
	}
	
	public static EffectContext forModel(ModelInstance model) {
		return new Builder()
					.setModel(model)
					.build();
	}
	
	public static EffectContext forModel(
			UnitInstance unit,
			ModelInstance model){
		return new Builder()
					.setUnit(unit)
					.setModel(model)
					.build();
	}
	
	public static class Builder{
		private Roster roster;
		private UnitInstance unit;
		private ModelInstance model;
		private SelectedOption option;
		
		public Builder setRoster(Roster roster) {
			this.roster = roster;
			return this;
		}
		
		public Builder setUnit(UnitInstance unit) {
			this.unit = unit;
			return this;
		}
		
		public Builder setModel(ModelInstance model) {
			this.model = model;
			return this;
		}
		
		public Builder setOptionChoice(SelectedOption option) {
			this.option = option;
			return this;
		}
		
		public EffectContext build(){
			return new EffectContext(this);
		}
	}
}
