package es.akkatest.actors.beans;

import scala.io.BytePickle.Ref;

public class Table {
	
	private String[][] cells;
	private int length_x;
	private int length_y;

	
	public Table(int _length_x, int _length_y) {
		
		length_x = _length_x;
		length_y = _length_y;
		cells = new String[length_x][length_y];
		for(int i=0; i<length_x; i++) {for(int j=0; j<length_y; j++) {cells[i][j] = "";}}
	}
	
	
	public String[][] getTable(){
		return cells;
	}
	
	
	public int getX() {
		return length_x;
	}
	
	public int getY() {
		return length_y;
	}

}
