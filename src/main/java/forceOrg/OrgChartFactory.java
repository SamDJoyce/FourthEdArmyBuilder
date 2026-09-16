package forceOrg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.ForceOrgChartDTO;
import units.UnitRole;

public class OrgChartFactory {
	private static Map<String,ForceOrgChart> registry = new HashMap<>();
	
	// Standard
		public static ForceOrgChart createStandard() {
			String name = "Standard";
			Map<UnitRole, ForceOrgLimit> limits = new HashMap<>();
			limits.put(UnitRole.HQ,ForceOrgLimit.forHQ(1, 2));
			limits.put(UnitRole.ELITES,ForceOrgLimit.forElites(0, 3));
			limits.put(UnitRole.TROOPS,ForceOrgLimit.forTroops(2, 6));
			limits.put(UnitRole.FAST_ATTACK,ForceOrgLimit.forFastAttack(0, 3));
			limits.put(UnitRole.HEAVY_SUPPORT,ForceOrgLimit.forHeavySupport(0, 3));
			return new ForceOrgChart(name,limits);
		}
		
		public static ForceOrgChart createBattleAttacker() {
			String name = "Battle Attacker";
			Map<UnitRole, ForceOrgLimit> limits = new HashMap<>();
			limits.put(UnitRole.HQ,ForceOrgLimit.forHQ(1, 2));
			limits.put(UnitRole.ELITES,ForceOrgLimit.forElites(0, 3));
			limits.put(UnitRole.TROOPS,ForceOrgLimit.forTroops(1, 6));
			limits.put(UnitRole.FAST_ATTACK,ForceOrgLimit.forFastAttack(0, 3));
			limits.put(UnitRole.HEAVY_SUPPORT,ForceOrgLimit.forHeavySupport(1, 3));
			return new ForceOrgChart(name,limits);
		}
		
		public static ForceOrgChart createBattleDefender() {
			String name = "Battle Defender";
			Map<UnitRole, ForceOrgLimit> limits = new HashMap<>();
			limits.put(UnitRole.HQ,ForceOrgLimit.forHQ(1, 1));
			limits.put(UnitRole.ELITES,ForceOrgLimit.forElites(0, 2));
			limits.put(UnitRole.TROOPS,ForceOrgLimit.forTroops(2, 6));
			limits.put(UnitRole.FAST_ATTACK,ForceOrgLimit.forFastAttack(0, 1));
			limits.put(UnitRole.HEAVY_SUPPORT,ForceOrgLimit.forHeavySupport(0, 3));
			return new ForceOrgChart(name,limits);
		}
		
		public static ForceOrgChart create(
				String name,
				Map<UnitRole, ForceOrgLimit> limits) {
			
			return registry.computeIfAbsent(name, 
					key -> new ForceOrgChart(
									name,
									limits));
		}
		
		
		public static ForceOrgChart get(String name) {
			return registry.get(name);
		}
		
		public static ForceOrgChart createEmpty() {
			return new ForceOrgChart("New Chart", new HashMap<>());
		}
		
		public static void clearRegistry() {
			registry.clear();
		}
		
		
}
