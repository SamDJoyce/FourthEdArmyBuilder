package units.options.effects;

import units.descriptions.models.StatLine;
import units.options.SelectionContext;

public class ModifyStatEffect implements Effect {
	private final String name;
	private String stat;
	private int    modifier;
	
	public ModifyStatEffect(String name) {
		this.name = name;
	}
	
	public ModifyStatEffect(String name, String stat, int modifier) {
		this.name = name;
		this.stat = stat;
		this.modifier = modifier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public void setModifier(int modifier) {
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
