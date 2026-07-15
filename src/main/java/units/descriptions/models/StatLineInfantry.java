package units.descriptions.models;

public class StatLineInfantry implements StatLine {
	private final static String type = "infantry";
	
	private String name;
	private int bs;
	private int ws;
	private int s;
	private int t;
	private int w;
	private int i;
	private int a;
	private int ld;
	private int sv;

	 
	public StatLineInfantry(
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
		this.name = name;
		this.bs = bs;
		this.ws = ws;
		this.s = s;
		this.t = t;
		this.w = w;
		this.i = i;
		this.a = a;
		this.ld = ld;
		this.sv = sv;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public String statsToString() {
		return  "Name = " + this.name + " \n " +
				"WS = " + this.ws + " \n " +
				"BS = " + this.bs + " \n " +
				"S = " + this.s + " \n " +
				"T = " + this.t + " \n " +
				"W = " + this.w + " \n " +
				"I = " + this.i + " \n " +
				"A = " + this.a + " \n " +
				"LD = " + this.ld + " \n " +
				"SV = " + this.sv + "+";
	}
	
	// Infantry Stats
	
	public int getBs() {
		return bs;
	}
	
	public void setBs(int bs) {
		this.bs = bs;
	}
	
	public int getWs() {
		return ws;
	}

	public void setWs(int ws) {
		this.ws = ws;
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
	
	// Other Stats not used for Infantry
	public int getFront() {
		return 0;
	}
	
	public void setFront(int front) {
		return;
	}
	
	public int getSide() {
		return 0;
	}
	
	public void setSide(int side) {
		return;
	}
	
	public int getRear() {
		return 0;
	}
	
	public void setRear(int rear) {
		return;
	}
}
