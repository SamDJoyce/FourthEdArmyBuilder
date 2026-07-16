package units.descriptions.models;

public class StatLineVehicle implements StatLine {

	private final static String type = "vehicle";
	
	private String name;
	private int bs;
	private int front;
	private int side;
	private int rear;
	
	public StatLineVehicle( 
			String name, 
			int bs,
			int front,
			int side,
			int rear) {
		this.name  = name;
		this.bs    = bs;
		this.front = front;
		this.side  = side;
		this.rear  = rear;
	}
	
	public void modify(String stat, int modifier) {

		if ("bs".equalsIgnoreCase(stat)) {
			bs += modifier;
		}
		if ("front".equalsIgnoreCase(stat)) {
			front += modifier;
		}
		if ("side".equalsIgnoreCase(stat)) {
			side += modifier;
		}
		if ("rear".equalsIgnoreCase(stat)) {
			rear += modifier;
		}
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	// Vehicle Stats
	public int getBs() {
		return bs;
	}
	
	public void setBs(int bs) {
		this.bs = bs;
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
	
	public String statsToString() {
		return  "Name = " + this.name + " \n " +
				"BS = " + this.bs + " \n " +
				"Front = " + this.front + " \n " +
				"Side = " + this.side + " \n " +
				"Rear = " + this.rear + " \n ";
	}
	
	// Other Stats
	public int getWs() {
		return 0;
	}

	public void setWs(int ws) {
		return;
	}

	public int getS() {
		return 0;
	}

	public void setS(int s) {
		return;
	}

	public int getT() {
		return 0;
	}

	public void setT(int t) {
		return;
	}

	public int getW() {
		return 0;
	}

	public void setW(int w) {
		return;
	}

	public int getI() {
		return 0;
	}

	public void setI(int i) {
		return;
	}

	public int getA() {
		return 0;
	}

	public void setA(int a) {
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
