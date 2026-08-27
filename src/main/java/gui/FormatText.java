package gui;

public class FormatText {
	private static String CHOICE_TAG = "select ";
	private static String GROUP_TAG = "group";
	
	public String toTitleCase(String text) {
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
	
	public String removeGroupTag(String groupName) {
	    int index = groupName.indexOf(GROUP_TAG);
	    if (index == -1) {
	       return groupName; 
	    }
	    return groupName.substring(0, index).trim();
	    
	}
	
	public String removeChoiceTag(String choiceName) {
		int index = choiceName.indexOf(CHOICE_TAG) + CHOICE_TAG.length();
		if (index == -1) {
			return choiceName;
		}
		return choiceName.substring(index, choiceName.length()).trim();
	}
	
}
