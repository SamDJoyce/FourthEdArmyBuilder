package dto;

public class StatLineInfantryDTO extends StatLineDTO{
	private String name;
	private int ws, bs, s, t, w, i, a, ld, sv;
	
	public StatLineInfantryDTO(
			String type,
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
		super(type);
		this.ws = ws;
		this.bs = bs;
		this.s = s;
		this.t = t;
		this.w = w;
		this.i = i;
		this.a = a;
		this.ld = ld;
		this.sv = sv;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWs() {
		return ws;
	}

	public void setWs(int ws) {
		this.ws = ws;
	}

	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getLd() {
		return ld;
	}

	public void setLd(int ld) {
		this.ld = ld;
	}

	public int getSv() {
		return sv;
	}

	public void setSv(int sv) {
		this.sv = sv;
	}
}
