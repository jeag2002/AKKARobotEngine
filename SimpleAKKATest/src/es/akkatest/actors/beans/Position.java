package es.akkatest.actors.beans;

public class Position {
	

	private int X;
	private int Y;
	
	public Position() {
		X = 0;
		Y = 0;
	}
	
	public Position(int _X, int _Y) {
		X = _X;
		Y = _Y;
	}
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}


}
