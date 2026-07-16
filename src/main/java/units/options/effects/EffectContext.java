package units.options.effects;

import roster.Roster;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public final class EffectContext {
	private final Roster roster;
	private final UnitInstance unit;
	private final ModelInstance model;
	
	private EffectContext(Builder builder) {
		this.roster = builder.roster;
		this.unit = builder.unit;
		this.model = builder.model;
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
	
	public boolean hasRoster() {
		return roster != null;
	}
	
	public boolean hasUnit() {
		return unit != null;
	}
	
	public boolean hasModel() {
		return model != null;
	}
	
	public static class Builder{
		private Roster roster;
		private UnitInstance unit;
		private ModelInstance model;
		
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
	}
}
