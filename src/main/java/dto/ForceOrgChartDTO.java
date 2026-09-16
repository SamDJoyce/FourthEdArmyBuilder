package dto;

public class ForceOrgChartDTO {
	private String name;
	private int[] hq, 
				  elites, 
				  troops, 
				  fastAttack, 
				  heavySupport;
	
	public ForceOrgChartDTO() {}
	
	public ForceOrgChartDTO(
			String name,
			int[] hq,
			int[] elites,
			int[] troops,
			int[] fastAttack,
			int[] heavySupport) {
		this.name 		  = name;
		this.hq 		  = hq;
		this.elites 	  = elites;
		this.troops 	  = troops;
		this.fastAttack   = fastAttack;
		this.heavySupport = heavySupport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHq(int[] hq) {
		this.hq = hq;
	}
	
	public int getHqMin() {
		return hq[0];
	}
	
	public int getHqMax() {
		return hq[1];
	}

	public void setElites(int[] elites) {
		this.elites = elites;
	}
	
	public int getElitesMin() {
		return elites[0];
	}
	
	public int getElitesMax() {
		return elites[1];
	}

	
	public int getTroopsMin() {
		return troops[0];
	}
	
	public int getTroopsMax() {
		return troops[1];
	}

	public void setTroops(int[] troops) {
		this.troops = troops;
	}


	public void setFastAttack(int[] fastAttack) {
		this.fastAttack = fastAttack;
	}
	
	public int getFastAttackMin() {
		return fastAttack[0];
	}
	
	public int getFastAttackMax() {
		return fastAttack[1];
	}

	public void setHeavySupport(int[] heavySupport) {
		this.heavySupport = heavySupport;
	}
	
	public int getHeavySupportMin() {
		return heavySupport[0];
	}
	
	public int getHeavySupportMax() {
		return heavySupport[1];
	}
	
}
