package units.options.effects;

import units.descriptions.models.StatLine;
import units.options.SelectionContext;

public class ModifyStatEffect implements Effect {

	private final String stat;
	private final int modifier;
	
	public ModifyStatEffect(String stat, int modifier) {
		this.stat = stat;
		this.modifier = modifier;
	}
	
	@Override
	public void apply(SelectionContext context) {
		StatLine stats = context.getModel().getStats();
		stats.modify(stat,modifier);
	}
	
	@Override
	public void remove(SelectionContext context) {
		StatLine stats = context.getModel().getStats();
		stats.modify(stat,modifier*-1);
	}

}
