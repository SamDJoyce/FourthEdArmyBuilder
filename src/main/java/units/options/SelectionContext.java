package units.options;

import roster.Roster;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public final class SelectionContext {
	private final Roster roster;
	private final UnitInstance unit;
	private final ModelInstance model;
	private final OptionOwner owner;
	private final OptionChoice choice;
	private final OptionGroup group;
	private final WargearDescription gear;
	
	private SelectionContext(Builder builder) {
		this.roster = builder.roster;
		this.unit = builder.unit;
		this.model = builder.model;
		this.choice = builder.choice;
		this.group = builder.group;
		this.owner = builder.owner;
		this.gear = builder.gear;
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
	
	public OptionGroup getGroup() {
		return this.group;
	}
	
	public OptionOwner getOwner() {
		return this.owner;
	}
	
	public WargearDescription getWargear() {
		return this.gear;
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
	
	public boolean hasWargear() {
		return this.gear != null;
	}
	
	// Factory methods

	public static SelectionContext forRoster(
			Roster roster,
			OptionChoice choice) {

		return new Builder()
				.setRoster(roster)
				.setChoice(choice)
				.setGroup(choice.getParentGroup())
				.build();
	}


	public static SelectionContext forUnit(
			UnitInstance unit,
			OptionChoice choice) {

		return new Builder()
				.setUnit(unit)
				.setOwner(unit)
				.setChoice(choice)
				.setGroup(choice.getParentGroup())
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
				.setGroup(choice.getParentGroup())
				.build();
	}
	
	public static SelectionContext forGroup(
			OptionOwner owner,
			OptionGroup group) {
		if (owner.isModel()) {
			ModelInstance model = (ModelInstance) owner;
			return new Builder()
						.setModel(model)
						.setOwner(model)
						.setUnit(model.getParentUnit())
						.setGroup(group)
						.build();
		}
		UnitInstance unit = (UnitInstance) owner;
		return new Builder()
					.setGroup(group)
					.setUnit(unit)
					.setOwner(unit)
					.build();
	}
	
	public static SelectionContext create(
			OptionOwner owner,
			OptionChoice choice) {
		
		if (owner.isModel()) {
			ModelInstance model = (ModelInstance) owner;
			return forModel(model, choice);
		}
		
		UnitInstance unit = (UnitInstance) owner;
		return forUnit(unit, choice);
	}
	
	public static class Builder{
		private Roster roster;
		private UnitInstance unit;
		private ModelInstance model;
		private OptionChoice choice;
		private OptionGroup group;
		private OptionOwner owner;
		private WargearDescription gear;
		
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
		
		public Builder setGroup(OptionGroup group) {
			this.group = group;
			return this;
		}
		
		public Builder setOwner(OptionOwner owner) {
			this.owner = owner;
			return this;
		}
		
		public Builder setWargear(WargearDescription gear) {
			this.gear = gear;
			return this;
		}
		
		public SelectionContext build(){
			return new SelectionContext(this);
		}
	}
}
