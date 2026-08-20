package units.options.requirements;

import roster.ValidationResult;
import units.options.SelectionContext;

public interface Requirement {

	ValidationResult isMet(SelectionContext context);
	ValidationResult validate(SelectionContext context);
	
}
