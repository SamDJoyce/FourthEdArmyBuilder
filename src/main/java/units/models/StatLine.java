package units.models;

public interface StatLine {
	
	public String getId();
	public void setId(String id);

	public String getName();
	public void setName(String name);
	
	public int getBs();
	public void setBs(int bs);
	
	public String getType();
	
	public int getWs();
	public void setWs(int ws); 
	
	public int getS();
	public void setS(int s);
	
	public int getT();
	public void setT(int t);
	
	public int getW();
	public void setW(int w);
	
	public int getI();
	public void setI(int i);
	
	public int getA();
	public void setA(int a);
	
	public int getLd();
	public void setLd(int ld);
	
	public int getSv();
	public void setSv(int sv);
	
	public int getFront();
	public void setFront(int front);
	
	public int getSide();
	public void setSide(int side);
	
	public int getRear();
	public void setRear(int rear);
}
