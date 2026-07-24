package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.WargearDTO;
import units.WargearFactory;
import units.descriptions.wargear.WargearDescription;

public class WargearLoader {

	public WargearLoader() {}
	
	public WargearDescription load(WargearDTO dto) {
		return WargearFactory.createDescription(
				dto.getName(), 
				dto.getType(),
				dto.getPoints());
	}
	
	public List<WargearDescription> loadAll(List<WargearDTO> dtos) {

	    List<WargearDescription> result = new ArrayList<>();

	    for (WargearDTO dto : dtos) {
	        result.add(load(dto));
	    }

	    return result;
	}
}
