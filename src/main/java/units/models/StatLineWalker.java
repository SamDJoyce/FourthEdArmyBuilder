package units.models;

public class StatLineWalker extends StatLine {

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
				int bs,
				int ws,
				int s,
				int i,
				int a,
				int front,
				int side,
				int rear) {
		super(id, name, bs);
		this.ws = ws;
		this.s = s;
		this.i = i;
		this.a = a;
		this.front = front;
		this.side = side;
		this.rear = rear;
	}

	@Override
	public int getWs() {
		return ws;
	}

	@Override
	public void setWs(int ws) {
		this.ws = ws;

	}

	@Override
	public int getS() {
		return s;
	}

	@Override
	public void setS(int s) {
		this.s = s;

	}

	@Override
	public int getT() {
		return 0;
	}

	@Override
	public void setT(int t) {
		return;

	}

	@Override
	public int getW() {
		return 0;
	}

	@Override
	public void setW(int w) {
		return;

	}

	@Override
	public int getI() {
		return i;
	}

	@Override
	public void setI(int i) {
		this.i = i;

	}

	@Override
	public int getA() {
		return a;
	}

	@Override
	public void setA(int a) {
		this.a = a;

	}

	@Override
	public int getLd() {
		return 0;
	}

	@Override
	public void setLd(int ld) {
		return;

	}

	@Override
	public int getSv() {
		return 0;
	}

	@Override
	public void setSv(int sv) {
		return;

	}

	@Override
	public int getFront() {
		return front;
	}

	@Override
	public void setFront(int front) {
		this.front = front;

	}

	@Override
	public int getSide() {
		return side;
	}

	@Override
	public void setSide(int side) {
		this.side = side;

	}

	@Override
	public int getRear() {
		return rear;
	}

	@Override
	public void setRear(int rear) {
		this.rear = rear;
	}

}
