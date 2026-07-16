package units.options.effects;

import units.descriptions.wargear.WargearDescription;

public class ReplaceWargearEffect implements Effect {

	private final WargearDescription remove;
	private final WargearDescription add;
	
	public ReplaceWargearEffect(WargearDescription remove,WargearDescription add) {
		this.remove = remove;
		this.add = add;
	}
	
	@Override
	public void apply(EffectContext context) {
		context.getModel().removeGear(remove);
		context.getModel().addGear(add);
	}
	
	@Override
	public void remove(EffectContext context) {
		context.getModel().removeGear(add);
		context.getModel().addGear(remove);
	}

}
