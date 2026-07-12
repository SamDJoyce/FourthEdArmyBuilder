package units.models;

public class StatLineWalker implements StatLine {

	private static final String type = "walker";
	
	private String id;
	private String name;
	private int bs;
	private int ws;
	private int s;
	private int i;
	private int a;
	private int front;
	private int side;
	private int rear;
	
	public StatLineWalker(
				String id, 
				String name, 
				int ws,
				int bs,
				int s,
				int i,
				int a,
				int front,
				int side,
				int rear) {
		this.id = id;
		this.name = name;
		this.bs = bs;
		this.ws = ws;
		this.s = s;
		this.i = i;
		this.a = a;
		this.front = front;
		this.side = side;
		this.rear = rear;
	}
	
	public StatLineWalker(
			String name, 
			int ws,
			int bs,
			int s,
			int i,
			int a,
			int front,
			int side,
			int rear) {
		this.name = name;
		this.bs = bs;
		this.ws = ws;
		this.s = s;
		this.i = i;
		this.a = a;
		this.front = front;
		this.side = side;
		this.rear = rear;
	}
	
	public String getType() {
		return type;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
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



	public int getW() {
		return 0;
	}

	public void setW(int w) {
		return;

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
	
	public String statsToString() {
		return " ID = " + this.id + " \n " +
				"Name = " + this.name + " \n " +
				"WS = " + this.ws + " \n " +
				"BS = " + this.bs + " \n " +
				"S = " + this.s + " \n " +
				"I = " + this.i + " \n " +
				"A = " + this.a + " \n " +
				"Front = " + this.front + " \n " +
				"Side = " + this.side + " \n " +
				"Rear = " + this.rear + " \n ";
	}
	

	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;

	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;

	}

	public int getRear() {
		return rear;
	}

	public void setRear(int rear) {
		this.rear = rear;
	}
	
	// Unused or not present stats

	public int getT() {
		return 0;
	}

	public void setT(int t) {
		return;

	}
	
	public int getLd() {
		return 0;
	}

	public void setLd(int ld) {
		return;

	}

	public int getSv() {
		return 0;
	}

	public void setSv(int sv) {
		return;

	}
}
