package es.akkatest.actor.messages;

import es.akkatest.actors.beans.Table;

public class ShowWorldMsg {
	
	public static final String SHOWWORLDMSG = "SHOWWORLDMSG";
	private Table table;

	public ShowWorldMsg(Table _table) {
		table = _table;
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
}
