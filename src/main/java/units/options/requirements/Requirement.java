package units.options.requirements;

import roster.ValidationResult;
import units.options.SelectionContext;

public interface Requirement {

	String getName();
	ValidationResult isMet(SelectionContext context);
	ValidationResult validate(SelectionContext context);
	
}
