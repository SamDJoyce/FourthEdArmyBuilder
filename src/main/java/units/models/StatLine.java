package units.models;

public abstract class StatLine {
	protected String id;
	protected String name;
	protected int	 bs;
	
	public StatLine(String id, String name, int bs) {
		this.id = id;
		this.name = name;
		this.bs = bs;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}
	
	// Abstract Methods
	public abstract int getWs();
	public abstract void setWs(int ws); 
	public abstract int getS();
	public abstract void setS(int s);
	public abstract int getT();
	public abstract void setT(int t);
	public abstract int getW();
	public abstract void setW(int w);
	public abstract int getI();
	public abstract void setI(int i);
	public abstract int getA();
	public abstract void setA(int a);
	public abstract int getLd();
	public abstract void setLd(int ld);
	public abstract int getSv();
	public abstract void setSv(int sv);
	public abstract int getFront();
	public abstract void setFront(int front);
	public abstract int getSide();
	public abstract void setSide(int side);
	public abstract int getRear();
	public abstract void setRear(int rear);
}
