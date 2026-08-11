package units.options.requirements;

import roster.RosterResult;
import units.options.SelectionContext;

public interface Requirement {

	RequirementResult isMet(SelectionContext context);
	RosterResult validate(SelectionContext context);
	
}
