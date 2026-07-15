package units.descriptions.models;

import java.util.Set;

import units.UnitType;
import units.descriptions.wargear.WargearDescription;

public class ModelDescFactory {
	private ModelDescFactory() {}
	
	
	public static ModelDescription get(
						String id, 
						String name,
						int basePoints,
						StatLine stats,
						Set<UnitType> types,
						Set<WargearDescription> gear) {
		
		return new ModelDescription(
					name,
					basePoints,
					stats,
					types,
					gear);
	}
	
	public static ModelDescription get(
			String name,
			int basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearDescription> gear) {

		return new ModelDescription(
				name,
				basePoints,
				stats,
				types,
				gear);
	}
	
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
						Set<UnitType> types,
						Set<WargearDescription> gear) {
		return new ModelDescription( 
									name, 
									basePoints, 
									StatLineFactory.get(name, ws, bs,s,t,w,i,a,ld,sv), 
									types,
									gear);
	}
	
	/**
	 * Create an Infantry/standard ModelDescription
	 * 
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
						Set<UnitType> types,
						Set<WargearDescription> gear) {
		return new ModelDescription(
									name, 
									basePoints, 
									StatLineFactory.get(name, ws, bs,s,t,w,i,a,ld,sv), 
									types,
									gear);
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
						Set<UnitType> types,
						Set<WargearDescription> gear) {
		return new ModelDescription(
									name,
									basePoints,
									StatLineFactory.get(name, bs, front, side, rear),
									types,
									gear);
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
						Set<UnitType> types,
						Set<WargearDescription> gear) {
		return new ModelDescription(
									name,
									basePoints,
									StatLineFactory.get(name, bs, ws, s, i, a, front, side, rear),
									types,
									gear);
	}
}
