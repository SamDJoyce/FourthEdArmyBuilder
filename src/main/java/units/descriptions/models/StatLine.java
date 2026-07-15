package units.descriptions.models;

public interface StatLine {
	
	String getName();
	void setName(String name);
	
	String getType();
	
	int getWs();
	void setWs(int ws); 
	
	int getBs();
	void setBs(int bs);
	
	int getS();
	void setS(int s);
	
	int getT();
	void setT(int t);
	
	int getW();
	void setW(int w);
	
	int getI();
	void setI(int i);
	
	int getA();
	void setA(int a);
	
	int getLd();
	void setLd(int ld);
	
	int getSv();
	void setSv(int sv);
	
	int getFront();
	void setFront(int front);
	
	int getSide();
	void setSide(int side);
	
	int getRear();
	void setRear(int rear);
	
	String statsToString();
}
