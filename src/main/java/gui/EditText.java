package gui;

public class EditText {

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
	
	public String removeGroupTag(String text) {
	    int index = text.indexOf("group");

	    if (index != -1) {
	        return text.substring(0, index).trim();
	    }

	    return text;
	}
	
}
