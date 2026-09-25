package units.options.effects;

import units.UnitType;
import units.options.SelectionContext;

public class AddModelTypeEffect implements Effect {

	private final String   name;
	private 	  UnitType type;
	
	public AddModelTypeEffect(String name) {
		this.name = name;
	}
	
	public AddModelTypeEffect(
			String name,
			UnitType type
			) {
		this.name = name;
		this.type = type;
	}
	
	
	
	@Override
	public void apply(SelectionContext context) {
		context.getModel().addType(type);
	}

	@Override
	public void remove(SelectionContext context) {
		context.getModel().removeType(type);
	}

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

}
