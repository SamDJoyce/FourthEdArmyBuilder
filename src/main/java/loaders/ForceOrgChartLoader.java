package loaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.ForceOrgChartDTO;
import forceOrg.ForceOrgChart;
import forceOrg.ForceOrgLimit;
import forceOrg.OrgChartFactory;
import units.UnitRole;

public class ForceOrgChartLoader {
	public ForceOrgChartLoader() {}
	
	public List<ForceOrgChart> loadAll(List<ForceOrgChartDTO> dtos){
		List<ForceOrgChart> charts = new ArrayList<>();
		
		for (ForceOrgChartDTO dto : dtos) {
			charts.add(load(dto));
		}
		
		return charts;
	}
	
	public ForceOrgChart load(ForceOrgChartDTO dto) {

		return OrgChartFactory.create(
								dto.getName(),
								loadLimits(dto));
	}
	
	public Map<UnitRole, ForceOrgLimit> loadLimits(ForceOrgChartDTO dto){
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
}
