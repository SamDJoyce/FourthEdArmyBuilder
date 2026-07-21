package dto;

public class StatLineVehicleDTO extends StatLineDTO {
	int bs, front, side, rear;
	String name;
	
	public StatLineVehicleDTO(
			String type,
			String name,
			int bs,
			int front,
			int side,
			int rear) {
		super(type);
		this.name = name;
		this.bs = bs;
		this.front = front;
		this.side = side;
		this.rear = rear;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
