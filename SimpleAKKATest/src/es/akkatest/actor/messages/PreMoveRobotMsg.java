package es.akkatest.actor.messages;

import es.akkatest.actors.beans.Position;

public class PreMoveRobotMsg {
	
	public static final String PREMOVEROBOTMSG = "PREMOVEROBOTMSG";
	
	private Position pre;
	private Position post;
	private int indexRobot;
	
	public PreMoveRobotMsg(Position _pre, Position _post, int _indexRobot) {
		pre = _pre;
		post = _post;
		indexRobot = _indexRobot;
	}
	
	
	public Position getPre() {
		return pre;
	}

	public void setPre(Position pre) {
		this.pre = pre;
	}

	public Position getPost() {
		return post;
	}

	public void setPost(Position post) {
		this.post = post;
	}

	public int getIndexRobot() {
		return indexRobot;
	}

	public void setIndexRobot(int indexRobot) {
		this.indexRobot = indexRobot;
	}
	
	
	

}
