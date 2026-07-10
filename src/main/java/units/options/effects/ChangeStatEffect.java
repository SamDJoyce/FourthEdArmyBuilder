package units.options.effects;

import units.models.ModelDescription;
import units.models.StatLine;

public class ChangeStatEffect implements Effect {

	private static String WS = "ws";
	private static String BS = "bs";
	private static String S  = "s";
	private static String T  = "t";
	private static String W  = "w";
	private static String I  = "i";
	private static String A  = "a";
	private static String LD = "ld";
	private static String SV = "sv";
	

	private ModelDescription model;
	private String 			 changedStat;
	private int 			 changeValue;
	
	public ChangeStatEffect(
			ModelDescription model, 
			String changedStat, 
			int changeValue) {
		this.model = model;
		this.changedStat = changedStat;
		this.changeValue = changeValue;
	}

	
	@Override
	public void doEffect() {
		StatLine stats = model.getStats();
		
		if (WS.equalsIgnoreCase(changedStat)) {
			stats.setWs(stats.getWs() + changeValue);
		}
		
		else if (BS.equalsIgnoreCase(changedStat)) {
			stats.setBs(stats.getBs() + changeValue);
		}
		
		else if (S.equalsIgnoreCase(changedStat)) {
			stats.setS(stats.getS() + changeValue);
		}
		
		else if (T.equalsIgnoreCase(changedStat)) {
			stats.setT(stats.getT() + changeValue);
		}
		
		else if (W.equalsIgnoreCase(changedStat)) {
			stats.setW(stats.getW() + changeValue);
		}
		
		else if (I.equalsIgnoreCase(changedStat)) {
			stats.setI(stats.getI() + changeValue);
		}
		
		else if (A.equalsIgnoreCase(changedStat)) {
			stats.setA(stats.getA() + changeValue);
		}
		
		else if (LD.equalsIgnoreCase(changedStat)) {
			stats.setLd(stats.getLd() + changeValue);
		}
		
		else if (SV.equalsIgnoreCase(changedStat)) {
			stats.setSv(stats.getSv() + changeValue);
		}

		model.setStats(stats);
	}

	@Override
	public void undoEffect() {
StatLine stats = model.getStats();
		
		if (WS.equalsIgnoreCase(changedStat)) {
			stats.setWs(stats.getWs() - changeValue);
		}
		
		else if (BS.equalsIgnoreCase(changedStat)) {
			stats.setBs(stats.getBs() - changeValue);
		}
		
		else if (S.equalsIgnoreCase(changedStat)) {
			stats.setS(stats.getS() - changeValue);
		}
		
		else if (T.equalsIgnoreCase(changedStat)) {
			stats.setT(stats.getT() - changeValue);
		}
		
		else if (W.equalsIgnoreCase(changedStat)) {
			stats.setW(stats.getW() - changeValue);
		}
		
		else if (I.equalsIgnoreCase(changedStat)) {
			stats.setI(stats.getI() - changeValue);
		}
		
		else if (A.equalsIgnoreCase(changedStat)) {
			stats.setA(stats.getA() - changeValue);
		}
		
		else if (LD.equalsIgnoreCase(changedStat)) {
			stats.setLd(stats.getLd() - changeValue);
		}
		
		else if (SV.equalsIgnoreCase(changedStat)) {
			stats.setSv(stats.getSv() - changeValue);
		}

		model.setStats(stats);
	}

}
