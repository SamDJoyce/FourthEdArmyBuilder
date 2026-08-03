package roster;

public class RosterResult {
	boolean valid;
	String message;
	
	public RosterResult(
			boolean valid,
			String message) {
		
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static RosterResult valid(String message) {
		return new RosterResult(true,message);
	}
	
	public static RosterResult invalid(String message) {
		return new RosterResult(false, message);
	}
}
