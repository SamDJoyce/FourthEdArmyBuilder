package units.models;

public class StatLineVehicle extends StatLine {

	private final static String type = "vehicle";
	
	private int front;
	private int side;
	private int rear;
	
	public StatLineVehicle(
			String id, 
			String name, 
			int bs,
			int front,
			int side,
			int rear) {
		super(id, name, bs);
		this.front = front;
		this.side = side;
		this.rear = rear;
	}
	
	public String getType() {
		return type;
	}
	
	// Vehicle Stats
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
