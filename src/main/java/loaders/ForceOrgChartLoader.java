package loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ForceOrgChartDTO;
import forceOrg.ForceOrgChart;
import forceOrg.ForceOrgLimit;
import forceOrg.OrgChartFactory;
import units.UnitRole;

public class ForceOrgChartLoader {
	public ForceOrgChartLoader() {}
	
	public List<ForceOrgChart> createAll(List<ForceOrgChartDTO> dtos){
		List<ForceOrgChart> charts = new ArrayList<>();
		
		for (ForceOrgChartDTO dto : dtos) {
			charts.add(create(dto));
		}
		
		return charts;
	}
	
	public ForceOrgChart create(ForceOrgChartDTO dto) {

		return OrgChartFactory.create(
								dto.getName(),
								createLimits(dto));
	}
	
	public Map<UnitRole, ForceOrgLimit> createLimits(ForceOrgChartDTO dto){
		Map<UnitRole, ForceOrgLimit> limits = new HashMap<>();
		
		limits.put(UnitRole.HQ, ForceOrgLimit.forHQ(
				dto.getHqMin(),
				dto.getHqMax()
		));
		limits.put(UnitRole.ELITES, ForceOrgLimit.forElites(
				dto.getElitesMin(),
				dto.getElitesMax()
		));
		limits.put(UnitRole.TROOPS, ForceOrgLimit.forTroops(
				dto.getTroopsMin(),
				dto.getTroopsMax()
		));
		limits.put(UnitRole.FAST_ATTACK, ForceOrgLimit.forFastAttack(
				dto.getFastAttackMin(),
				dto.getFastAttackMax()
		));
		limits.put(UnitRole.HEAVY_SUPPORT, ForceOrgLimit.forHeavySupport(
				dto.getHeavySupportMin(),
				dto.getHeavySupportMax()
		));
		
		return limits;
	}
	
	public List<ForceOrgChart> load(String orgChartFile) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		InputStream input =
	            ResourceLoader.getResource(orgChartFile);
		List<ForceOrgChartDTO> dtos = mapper.readValue(
				input, 
				new TypeReference<List<ForceOrgChartDTO>>() {}
				);
		return createAll(dtos);
	}
}
