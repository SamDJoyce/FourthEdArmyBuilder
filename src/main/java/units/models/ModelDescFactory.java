package units.models;

import java.util.Set;

import units.UnitType;

public class ModelDescFactory {
	private ModelDescFactory() {}
	
	
	/**
	 * Create an Infantry/standard ModelDescription
	 * 
	 * @param id
	 * @param name
	 * @param basePoints
	 * @param ws
	 * @param bs
	 * @param s
	 * @param t
	 * @param w
	 * @param i
	 * @param a
	 * @param ld
	 * @param sv
	 * @param types
	 * @return
	 */
	public static ModelDescription get(
						String id,
						String name,
						int basePoints,
						int ws,
						int bs,
						int s,
						int t, 
						int w, 
						int i, 
						int a, 
						int ld, 
						int sv,
						Set<UnitType> types) {
		return new ModelDescription(id, 
									name, 
									basePoints, 
									StatLineFactory.get(id, name, ws, bs,s,t,w,i,a,ld,sv), 
									types);
	}
	
	
	/**
	 * Create a Vehicle ModelDescription
	 * 
	 * @param id
	 * @param name
	 * @param basePoints
	 * @param bs
	 * @param front
	 * @param side
	 * @param rear
	 * @param types
	 * @return
	 */
	public static ModelDescription get(
						String id,
						String name,
						int basePoints,
						int bs,
						int front,
						int side,
						int rear,
						Set<UnitType> types) {
		return new ModelDescription(id,
									name,
									basePoints,
									StatLineFactory.get(id, name, bs, front, side, rear),
									types);
	}
	
	/**
	 * Create a Walker ModelDescription
	 * 
	 * @param id
	 * @param name
	 * @param basePoints
	 * @param bs
	 * @param ws
	 * @param s
	 * @param i
	 * @param a
	 * @param front
	 * @param side
	 * @param rear
	 * @param types
	 * @return
	 */
	public static ModelDescription get(
						String id,
						String name,
						int basePoints,
						int bs,
						int ws,
						int s,
						int i,
						int a,
						int front,
						int side,
						int rear,
						Set<UnitType> types) {
		return new ModelDescription(id,
									name,
									basePoints,
									StatLineFactory.get(id, name, bs, ws, s, i, a, front, side, rear),
									types);
	}
}
