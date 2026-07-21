package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.options.SelectionContext;

public class ReplaceWargearEffect implements Effect {

	private final String name;
	private final WargearDescription remove;
	private final WargearDescription add;
	
	public ReplaceWargearEffect(
			String name,
			WargearDescription remove,
			WargearDescription add) {
		this.name = name;
		this.remove = remove;
		this.add = add;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getRemove() {
		return remove;
	}

	public WargearDescription getAdd() {
		return add;
	}

	@Override
	public void apply(SelectionContext context) {
		context.getModel().removeGear(remove);
		context.getModel().addGear(add);
	}
	
	@Override
	public void remove(SelectionContext context) {
		context.getModel().removeGear(add);
		context.getModel().addGear(remove);
	}

}
