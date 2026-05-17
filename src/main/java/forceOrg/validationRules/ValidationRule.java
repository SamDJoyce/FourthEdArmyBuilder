package forceOrg.validationRules;

import roster.Roster;

public interface ValidationRule {
	ValidationResult validate(Roster roster);
}
