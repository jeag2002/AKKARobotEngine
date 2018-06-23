package es.akkatest.actor.messages;

import es.akkatest.actors.beans.Position;

public class IniRobotMsg {
	
	public static final String INITROBOTMSG = "INITROBOTMSG";
	
	private Position pos;
	private int indexRobot;
	
	public IniRobotMsg(Position _pos, int _indexRobot) {
		pos = _pos;
		indexRobot = _indexRobot;
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getIndexRobot() {
		return indexRobot;
	}

	public void setIndexRobot(int indexRobot) {
		this.indexRobot = indexRobot;
	}

}
