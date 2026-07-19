package units.options.requirements;

import units.options.SelectionContext;

public interface Requirement {

	RequirementResult validate(SelectionContext context);
}
