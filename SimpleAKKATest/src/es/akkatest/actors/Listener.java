package es.akkatest.actors;

import akka.actor.UntypedActor;
import es.akkatest.actor.messages.PrintWorldMsg;
import es.akkatest.actors.beans.Table;
import es.akkatest.graphics.DrawCanvas;

public class Listener extends UntypedActor {

	private Table table;
	private DrawCanvas canvas;
	
	public Listener(Table _table, DrawCanvas _canvas) {
		table = _table;
		canvas = _canvas;
	}
	
	
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof PrintWorldMsg) {
			
			PrintWorldMsg pWM = (PrintWorldMsg)arg0;
			//System.out.println("[LISTENER] MSG " + pWM.PRINTWORLDMSG);
			
			table = pWM.getTab();
			canvas.setTable(table);
			canvas.repaint();
			
		}
		
		
	}

}
