package units.descriptions.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles statline creation
 */
public class StatLineFactory {

	private static final String INFANTRY = "infantry";
	private static final String VEHICLE = "vehicle";
	private static final String WALKER = "walker";
	
	private static final Map<String, StatLine> registry = new HashMap<>();
	
	public static StatLine copy(StatLine stats) {
		if (INFANTRY.equalsIgnoreCase(stats.getType())) {
			return forInfantry(
					stats.getName(),
					stats.getWs(),
					stats.getBs(),
					stats.getS(),
					stats.getT(),
					stats.getW(),
					stats.getI(),
					stats.getA(),
					stats.getLd(),
					stats.getSv()
					);
		}
		if (VEHICLE.equalsIgnoreCase(stats.getType())) {
			return forVehicle(
					stats.getName(),
					stats.getBs(),
					stats.getFront(),
					stats.getSide(),
					stats.getRear()
					);
		}
		
		if (WALKER.equalsIgnoreCase(stats.getType())) {
			return forWalker(
					stats.getName(),
					stats.getBs(),
					stats.getWs(),
					stats.getS(),
					stats.getI(),
					stats.getA(),
					stats.getFront(),
					stats.getSide(),
					stats.getRear()
					);
		}
		return new StatLineInfantry("",0,0,0,0,0,0,0,0,0);
	}
	
	/**
	 * Create a standard infantry statline
	 * 
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
	public static StatLine forInfantry(
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
		return  registry.computeIfAbsent(name,
	            key -> new StatLineInfantry(name,ws,bs,s,t,w,i,a,ld,sv));
	}

	/**
	 * Create a vehicle statline
	 * 
	 * @param name
	 * @param bs
	 * @param front
	 * @param side
	 * @param rear
	 * @return completed vehicle statline
	 */
	public static StatLine forVehicle(
			String name,
			int bs,
			int front,
			int side,
			int rear) {
		return registry.computeIfAbsent(name,
	            key -> new StatLineVehicle(name, bs, front, side, rear));
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
	public static StatLine forWalker(
			String name,
			int bs,
			int ws,
			int s,
			int i,
			int a,
			int front,
			int side,
			int rear) {
		return registry.computeIfAbsent(name,
	            key -> new StatLineWalker(name, bs, ws, s, i, a, front, side, rear));
	}
	
	/**
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public static StatLine get(String name) {
		return registry.get(name);
	}
}
