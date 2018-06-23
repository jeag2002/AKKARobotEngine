package es.akkatest.actor.messages;

import es.akkatest.actors.beans.Table;

public class PrintWorldMsg {
	
	public static final String PRINTWORLDMSG = "PRINTWORLDMSG";
	
	private Table tab;

	public PrintWorldMsg(Table _tab) {
		tab = _tab;
	}
	
	public Table getTab() {
		return tab;
	}

	public void setTab(Table tab) {
		this.tab = tab;
	}
	

}
