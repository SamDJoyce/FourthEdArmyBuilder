package units.options;

import roster.Roster;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public final class ValidationContext {

    private final Roster roster;
    private final UnitInstance unit;
    private final ModelInstance model;
    private final SelectedOption option;

    private ValidationContext(Builder builder) {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Roster roster;
        private UnitInstance unit;
        private ModelInstance model;
        private SelectedOption option;

        public Builder roster(Roster roster) {
            this.roster = roster;
            return this;
        }

        public Builder unit(UnitInstance unit) {
            this.unit = unit;
            return this;
        }

        public Builder model(ModelInstance model) {
            this.model = model;
            return this;
        }

        public Builder option(SelectedOption option) {
            this.option = option;
            return this;
        }

        public ValidationContext build() {
            return new ValidationContext(this);
        }
    }
}