package units.descriptions.wargear;

import units.WargearType;

public class WargearDescription {

	private String name;
	private WargearType type;
	
	public WargearDescription(
			String name,
			WargearType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	

//	public String getBaseName() {
//		String baseName = name.replaceFirst("\\s+armoury$", "");
//		baseName = baseName.replaceFirst("\\s+aw$", "");
//		baseName = baseName.replaceFirst("\\s+mw$", "");
//		return baseName.strip();
//	}
	
	public WargearType getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
	    String wargear = String.format(
	        "%s - %s",
	        name,
	        type.toString().replace('_', ' ')
	    );
		return toTitleCase(wargear);
	}

	private String toTitleCase(String text) {
	    String[] words = text.toLowerCase().split("\\s+");
	    StringBuilder result = new StringBuilder();

	    for (String word : words) {
	        if (!word.isEmpty()) {
	            result.append(Character.toUpperCase(word.charAt(0)))
	                  .append(word.substring(1))
	                  .append(" ");
	        }
	    }

	    return result.toString().trim();
	}
}
