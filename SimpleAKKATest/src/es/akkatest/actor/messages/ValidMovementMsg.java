package es.akkatest.actor.messages;

import es.akkatest.actors.beans.Position;

public class ValidMovementMsg {
	
	public static final String VALIDMOVEMENTMSG = "VALIDMOVEMENTMSG";
	
	private Position pos;

	public ValidMovementMsg(Position _pos) {
		pos = _pos;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	
}
