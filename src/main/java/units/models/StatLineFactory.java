package units.models;

/**
 * Handles statline creation
 */
public class StatLineFactory {
	private StatLineFactory() {}
	

	/**
	 * Create a standard infantry statline
	 * 
	 * @param id
	 * @param name
	 * @param ws
	 * @param bs
	 * @param s
	 * @param t
	 * @param w
	 * @param i
	 * @param a
	 * @param ld
	 * @param sv
	 * @return completed infantry statline
	 */
	public static StatLine get(
			String id,
			String name,
			int ws,
			int bs,
			int s,
			int t, 
			int w, 
			int i, 
			int a, 
			int ld, 
			int sv) {
		return new StatLineInfantry(id,name,ws,bs,s,t,w,i,a,ld,sv);
	}
	

	/**
	 * Create a vehicle statline
	 * 
	 * @param id
	 * @param name
	 * @param bs
	 * @param front
	 * @param side
	 * @param rear
	 * @return completed vehicle statline
	 */
	public static StatLine get(
			String id,
			String name,
			int bs,
			int front,
			int side,
			int rear) {
		return new StatLineVehicle(id, name, bs, front, side, rear);
	}
	
	/**
	 * Create walker vehicle statline
	 * 
	 * @param id
	 * @param name
	 * @param bs
	 * @param ws
	 * @param s
	 * @param i
	 * @param a
	 * @param front
	 * @param side
	 * @param rear
	 * @return completed walker vehicle statline
	 */
	public static StatLine get(
			String id,
			String name,
			int bs,
			int ws,
			int s,
			int i,
			int a,
			int front,
			int side,
			int rear) {
		return new StatLineWalker(id, name, bs, ws, s, i, a, front, side, rear);
	}
}
