package units.options.requirements;

import java.util.Set;

import roster.ValidationResult;
import units.instances.ModelInstance;
import units.options.OptionGroup;
import units.options.SelectedOption;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private final String name;
	private OptionGroup excludedGroup;
	
	public MutualExclusionReq(String name){
		this.name = name;
	}
	
	public MutualExclusionReq(
			String name,
			OptionGroup excludedGroup){
		this.name = name;
		this.excludedGroup = excludedGroup;
	}
	
	public String getName() {
		return name;
	}

	public OptionGroup getExcluded() {
		return excludedGroup;
	}

	public void setExcludedGroup(OptionGroup excludedGroup) {
		this.excludedGroup = excludedGroup;
	}

	@Override
	public ValidationResult isMet(SelectionContext context) {
		
		ValidationResult result = ValidationResult.create();
		if (!context.hasModel()) {
			result.addIssue("MutualExclusionReq requires a model instance.");
			return result;
		}
		
	    ModelInstance model = context.getModel();
	    Set<SelectedOption> selections = model.getSelectedOptions();
	    for (SelectedOption o : selections) {
	    	if (excludedGroup.containsChoice(o.getChoice())) {
	    		result.addIssue("Current selections exclude " + excludedGroup.getName());
	    	}
	    }
	    return result;
	}
	
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}

}
