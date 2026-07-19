package units.options.requirements;

import roster.Roster;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectedOption;

public final class RequirementContext {

    private final Roster roster;
    private final UnitInstance unit;
    private final ModelInstance model;
    private final SelectedOption option;

    private RequirementContext(Builder builder) {
        this.roster = builder.roster;
        this.unit = builder.unit;
        this.model = builder.model;
        this.option = builder.option;
    }

    public Roster getRoster() {
        return roster;
    }

    public UnitInstance getUnit() {
        return unit;
    }

    public ModelInstance getModel() {
        return model;
    }

    public SelectedOption getOption() {
        return option;
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

    public boolean hasOption() {
        return option != null;
    }

//    public static Builder builder() {
//        return new Builder();
//    }
    
    public static RequirementContext forRoster(Roster roster) {
    	return new RequirementContext.Builder()
    								 .setRoster(roster)
    								 .build();
    }
    
    public static RequirementContext forUnit(UnitInstance unit) {
    	return new RequirementContext.Builder()
    								 .setUnit(unit)
    								 .build();
    }
    
    public static RequirementContext forModel(ModelInstance model) {
    	return new RequirementContext.Builder()
    								 .setModel(model)
    								 .build();
    }

    public static class Builder {

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

        public Builder setOption(SelectedOption option) {
            this.option = option;
            return this;
        }

        public RequirementContext build() {
            return new RequirementContext(this);
        }
    }
}