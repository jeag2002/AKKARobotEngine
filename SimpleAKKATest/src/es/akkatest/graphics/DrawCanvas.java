package es.akkatest.graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import es.akkatest.actors.beans.Table;

public class DrawCanvas extends JPanel{
	
	private Table table;
	
	private static final int OFFSET_X = 20;
	private static final int OFFSET_Y = 20;
	
	private static final int RADIUS = 10;
	
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//
		int cols = table.getX();
		int rows = table.getY();
		
		
		int width = getSize().width - (OFFSET_X);
		int height = getSize().height - (OFFSET_Y);
		
		int wdOfRow = width / cols;
		int htOfRow = height / rows;
		
		for (int k = 0; k < rows; k++) {
	          g.drawLine(OFFSET_X, (k * htOfRow) + OFFSET_Y  , width, (k * htOfRow) + OFFSET_Y );
		}
		
		g.drawLine(OFFSET_X, getSize().height-OFFSET_Y, width, getSize().height-OFFSET_Y);
		
		
		for (int k = 0; k < cols; k++) {
	          g.drawLine((k*wdOfRow) + OFFSET_X , OFFSET_Y, (k*wdOfRow) + OFFSET_X , height);
		}
		
		g.drawLine(getSize().width - OFFSET_X, OFFSET_Y, getSize().width - OFFSET_X, getSize().height -OFFSET_Y);
		
		
		for(int i=0; i<table.getX(); i++) {
			for(int j=0; j<table.getY(); j++) {
				if (!table.getTable()[i][j].equalsIgnoreCase("")) {
					
					int x = (i *  wdOfRow) + OFFSET_X;
					int y = (j *  htOfRow) + OFFSET_Y;
					
					g.setColor(Color.RED);
					g.fillOval(x, y, RADIUS, RADIUS);
					
				}
			}
		}
		
		
		
	}
	
	
	
	
	
	

}
