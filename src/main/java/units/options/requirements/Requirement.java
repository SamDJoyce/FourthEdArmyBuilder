package units.options.requirements;

public interface Requirement {

	RequirementResult validate(RequirementContext context);
}
