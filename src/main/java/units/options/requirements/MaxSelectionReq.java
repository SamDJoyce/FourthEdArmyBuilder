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
	
	// TODO fix or discard
	@Override
	public RequirementResult validate(SelectionContext context) {
//		if (!context.hasChoice()) {
//			message = "MaxSelectionRequirement needs a SelectedOption";
//			return RequirementResult.failure(null);
//		}
//		
//		if (context.getChoice().getNumSelected() <= maxSelection) {
//			message = String.format(
//					"This option is selected %d times (max %d)",
//					context.getChoice().getNumSelected(),
//					maxSelection
//					);
//			return RequirementResult.success(message);
//		}
//		else {
//			message = String.format(
//					"Cannot exceed %d selections of this option (currently %d).",
//					maxSelection,
//					context.getChoice().getNumSelected()
//					);
//			return RequirementResult.failure(message);
//		}
		return null;
	}
}
