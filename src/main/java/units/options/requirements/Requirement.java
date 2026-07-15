package units.options.requirements;

import units.options.ValidationContext;

public interface Requirement {

	ValidationResult validate(ValidationContext context);
}
