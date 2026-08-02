package roster;

public class RosterResponse {
	boolean valid;
	String message;
	
	public RosterResponse(
			boolean valid,
			String message) {
		
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public static RosterResponse valid(String message) {
		return new RosterResponse(true,message);
	}
	
	public static RosterResponse invalid(String message) {
		return new RosterResponse(false, message);
	}
}
