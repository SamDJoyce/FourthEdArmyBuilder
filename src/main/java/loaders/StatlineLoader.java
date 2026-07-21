package loaders;

import dto.StatLineDTO;
import dto.StatLineInfantryDTO;
import dto.StatLineVehicleDTO;
import dto.StatLineWalkerDTO;
import units.descriptions.models.StatLine;
import units.descriptions.models.StatLineFactory;

public class StatlineLoader {
	public StatlineLoader() {}
	

	
	private static final String INFANTRY = "infantry";
	private static final String VEHICLE = "vehicle";
	private static final String WALKER = "walker";
	
	public StatLine load(StatLineDTO dto) {
		switch(dto.getType()) {
			case INFANTRY:
				StatLineInfantryDTO i = (StatLineInfantryDTO) dto;
				return StatLineFactory.forInfantry(
						i.getName(), 
						i.getWs(), 
						i.getBs(), 
						i.getS(), 
						i.getT(), 
						i.getW(), 
						i.getI(), 
						i.getA(), 
						i.getLd(),
						i.getSv());
			case VEHICLE:
				StatLineVehicleDTO v = (StatLineVehicleDTO) dto;
				return StatLineFactory.forVehicle(
						v.getName(), 
						v.getBs(), 
						v.getFront(), 
						v.getSide(), 
						v.getRear());
			case WALKER:
				StatLineWalkerDTO w = (StatLineWalkerDTO) dto;
				return StatLineFactory.forWalker(
						w.getName(), 
						w.getBs(), 
						w.getWs(),
						w.getS(),
						w.getI(), 
						w.getA(),
						w.getFront(), 
						w.getSide(), 
						w.getRear());
		}
		return null;	
	}
}
