package units.options.requirements;

import roster.ValidationResult;
import units.UnitType;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class MustHaveTypeReq implements Requirement {

	private final String name;
	private UnitType requiredType;
	
	public MustHaveTypeReq(String name, UnitType requiredType) {
		this.name = name;
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredType(UnitType requiredType) {
		this.requiredType = requiredType;
	}

	@Override
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		ModelInstance model = context.getModel();
		if (!model.isType(requiredType)) {
			result.addIssue(String.format(
					"Model lacks the required Type: %s.", 
					requiredType.toString()));
		}
		return result;
	}

	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}
	
}
