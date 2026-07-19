package units.options.requirements;

import units.options.SelectionContext;

public class MaxSelectionReq implements Requirement {

	private int maxSelection;
	private String message;
	
	public MaxSelectionReq(
			int maxSelection
			) {
		this.maxSelection = maxSelection;
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		if (!context.hasOption()) {
			message = "MaxSelectionRequirement needs a SelectedOption";
			return RequirementResult.failure(null);
		}
		
		if (context.getOption().getNumSelected() <= maxSelection) {
			message = String.format(
					"This option is selected %d times (max %d)",
					context.getOption().getNumSelected(),
					maxSelection
					);
			return RequirementResult.success(message);
		}
		else {
			message = String.format(
					"Cannot exceed %d selections of this option (currently %d).",
					maxSelection,
					context.getOption().getNumSelected()
					);
			return RequirementResult.failure(message);
		}
	}
}
