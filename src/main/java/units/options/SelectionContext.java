package units.options;

import roster.Roster;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public final class SelectionContext {
	private final Roster roster;
	private final UnitInstance unit;
	private final ModelInstance model;
	private final OptionOwner owner;
	private final OptionChoice choice;
	
	private SelectionContext(Builder builder) {
		this.roster = builder.roster;
		this.unit = builder.unit;
		this.model = builder.model;
		this.choice = (builder.choice == null)
		        		? null
		                : builder.choice;
		this.owner = builder.owner;
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
	
	public OptionChoice getChoice() {
		return this.choice;
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
		return this.choice != null;
	}
	
	public boolean hasOwner() {
		return this.owner != null;
	}
	
	// Factory methods

	public static SelectionContext forRoster(
			Roster roster,
			OptionChoice choice) {

		return new Builder()
				.setRoster(roster)
				.setChoice(choice)
				.build();
	}


	public static SelectionContext forUnit(
			UnitInstance unit,
			OptionChoice choice) {

		return new Builder()
				.setUnit(unit)
				.setOwner(unit)
				.setChoice(choice)
				.build();
	}


	public static SelectionContext forModel(
			ModelInstance model,
			OptionChoice choice) {

		return new Builder()
				.setModel(model)
				.setUnit(model.getParentUnit())
				.setOwner(model)
				.setChoice(choice)
				.build();
	}
	
	public static class Builder{
		private Roster roster;
		private UnitInstance unit;
		private ModelInstance model;
		private OptionChoice choice;
		private OptionOwner owner;
		
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
		
		public Builder setChoice(OptionChoice choice) {
			this.choice = choice;
			return this;
		}
		
		public Builder setOwner(OptionOwner owner) {
			this.owner = owner;
			return this;
		}
		
		public SelectionContext build(){
			return new SelectionContext(this);
		}
	}
}
