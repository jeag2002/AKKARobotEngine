package es.akkatest.actors;

import java.util.Random;

import akka.actor.UntypedActor;
import es.akkatest.actor.messages.IniRobotMsg;
import es.akkatest.actor.messages.MoveRobotMsg;
import es.akkatest.actor.messages.PreMoveRobotMsg;
import es.akkatest.actor.messages.ValidMovementMsg;
import es.akkatest.actors.beans.Position;

public class Worker extends UntypedActor {
	
	
	private Position oldPos;
	private Position pos;
	private int indexWorker;
	private Random rm;
	
	private static final int MIN_DELAY = 100;
	private static final int MAX_DELAY = 500;
	
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	
	
	private Position processNewPosition() {
		
		int randomPosition = rm.nextInt(RIGHT-UP)+UP;
		
		Position pos_new = new Position(pos.getX(), pos.getY());
		
		switch(randomPosition) {
			case  UP: 		pos_new.setY(pos_new.getY()-1); break;
			case  DOWN:		pos_new.setY(pos_new.getY()+1); break;
			case  LEFT:		pos_new.setX(pos_new.getX()-1); break;
			case  RIGHT:	pos_new.setX(pos_new.getX()+1); break;
			default: break;
		}
		
		return pos_new;
	}
	

	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if (arg0 instanceof IniRobotMsg) {
			
			IniRobotMsg iRobotMsg = (IniRobotMsg)arg0;
			//System.out.println("[WORKER] INI pos (" + iRobotMsg.getPos().getX() + "," + iRobotMsg.getPos().getY() + ") INDEX (" + iRobotMsg.getIndexRobot() + ")") ;
			
			oldPos = new Position();
			pos = new Position(iRobotMsg.getPos().getX(), iRobotMsg.getPos().getY());
			indexWorker = iRobotMsg.getIndexRobot();
			
			rm = new Random();
			
		}else if (arg0 instanceof MoveRobotMsg) {
			
			MoveRobotMsg mRobotMsg = (MoveRobotMsg)arg0;
			//System.out.println("[WORKER " + indexWorker + "] " +  mRobotMsg.MOVEROBOTMSG);
			
			int delay = rm.nextInt(MAX_DELAY-MIN_DELAY) + MIN_DELAY;
			Thread.sleep(delay);
			
			getSender().tell(new PreMoveRobotMsg( pos, processNewPosition(), indexWorker),getSelf());
			
		}else if (arg0 instanceof ValidMovementMsg) {
			
			ValidMovementMsg vMM = (ValidMovementMsg)arg0;
			//System.out.println("[WORKER " + indexWorker + "] " + vMM.VALIDMOVEMENTMSG + " NEW POS (" + vMM.getPos().getX() + "," +  vMM.getPos().getY() + ")");
			oldPos = pos;
			pos = vMM.getPos();
				
		}else {
	        unhandled(arg0);
	    }
	
	
	}

}
