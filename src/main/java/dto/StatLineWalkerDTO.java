package dto;

public class StatLineWalkerDTO extends StatLineDTO {

	String name;
	int bs, ws, s, i ,a, front, side, rear;
	
	public StatLineWalkerDTO(
			String type, 
			String name, 
			int bs, 
			int ws, 
			int s,
			int i, 
			int a, 
			int front, 
			int side,
			int rear) {
		super(type);
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



}
