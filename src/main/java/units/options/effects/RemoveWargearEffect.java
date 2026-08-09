package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.options.SelectionContext;

public class RemoveWargearEffect implements Effect {

	private final String name;
	private WargearDescription remove;
	
	public RemoveWargearEffect(
			String name,
			WargearDescription remove) {
		this.name = name;
		this.remove = remove;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getRemove() {
		return remove;
	}
	
	public void setRemove(WargearDescription remove) {
		this.remove = remove;
	}
	
	@Override
	public void apply(SelectionContext context) {
		context.getModel().removeGear(remove);

	}

	@Override
	public void remove(SelectionContext context) {
		context.getModel().addGear(remove);

	}

}
