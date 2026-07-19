package units.options.effects;

import units.options.SelectionContext;

public interface Effect {
	void apply(SelectionContext context);
	void remove(SelectionContext context);
}
