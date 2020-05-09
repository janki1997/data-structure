package lab4;

import lab4.PairInt;

public class PairInt {

	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		this.y = y;
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
		 
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		if (p instanceof PairInt) {
			PairInt t1 = (PairInt) p;
			return (this.x == t1.getX() && this.y == t1.getY());
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}
}
