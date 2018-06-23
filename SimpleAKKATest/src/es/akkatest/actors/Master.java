package es.akkatest.actors;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import es.akkatest.actor.messages.ActWorldMsg;
import es.akkatest.actor.messages.IniRobotMsg;
import es.akkatest.actor.messages.InitWorldMsg;
import es.akkatest.actor.messages.MoveRobotMsg;
import es.akkatest.actor.messages.PreMoveRobotMsg;
import es.akkatest.actor.messages.PrintWorldMsg;
import es.akkatest.actor.messages.ValidMovementMsg;
import es.akkatest.actors.beans.Position;
import es.akkatest.actors.beans.Table;



public class Master extends UntypedActor {
	
	private Table table;
	private ArrayList<Position> posArray; 
	private ActorRef workerRouter;
	private ActorRef listener;
	
	private int countData;
	
	private static final int NUM_ACT = 50;
	
	public Master(Table _table, ArrayList<Position> _posArray, ActorRef _listener) {
		
		table = _table;
		
		posArray = new ArrayList<Position>();
		posArray.addAll(_posArray);
		workerRouter =
				this.getContext().actorOf(new Props(Worker.class).withRouter(new RoundRobinRouter(posArray.size())), "workerRouter");
		
		listener = _listener;
		
		countData = NUM_ACT;
		
	}
	
	
	private boolean isValidMovement(Position oldPosition, Position newPosition) {
		boolean isValid = false;
		
		int x = newPosition.getX();
		int y = newPosition.getY();
		
		if ((x < 0) || (x >= table.getX())) {
			//System.out.println("[MASTER] coordinates out of limits (" + x + "," + y + ")");
			isValid = false;
		}else if ((y < 0) || (y >= table.getY())) {
			//System.out.println("[MASTER] coordinates out of limits (" + x + "," + y + ")");
			isValid = false;
		}else if (!table.getTable()[x][y].equals("")) {
			//System.out.println("[MASTER] cell full table[" + x + "," + y + "] = " + table.getTable()[x][y]);
			isValid = false;
		}else {
			table.getTable()[x][y] = "x";
			table.getTable()[oldPosition.getX()][oldPosition.getY()] = "";
			isValid = true;
		}
		return isValid;
	}
	
	
	
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		//--> initWorldMsg
		if (arg0 instanceof InitWorldMsg) {
			
			InitWorldMsg iWM = (InitWorldMsg)arg0;
			
			//System.out.println("[MASTER] -- MESSAGE (" + iWM.INITWORLDMSG + ")");
			for(int i=0; i<posArray.size(); i++) {
				workerRouter.tell(new IniRobotMsg(posArray.get(i),i+1),getSelf());
			}
		
		}else if (arg0 instanceof ActWorldMsg) {
			
			ActWorldMsg aWM = (ActWorldMsg)arg0;
			//System.out.println("[MASTER] -- MESSAGE (" + aWM.ACTWORLDMSG + ")");
			
			for (int i=0; i<posArray.size(); i++) {
				workerRouter.tell(new MoveRobotMsg(),getSelf());
			}
			
			//System.out.println("[MASTER] -- MESSAGE PRINT WORLD");
			listener.tell(new PrintWorldMsg(table),getSelf());
			
		}else if (arg0 instanceof PreMoveRobotMsg) {
			
			PreMoveRobotMsg pMRM = (PreMoveRobotMsg)arg0;
			//System.out.println("[MASTER] -- MESSAGE (" + pMRM.PREMOVEROBOTMSG + ") from index (" + pMRM.getIndexRobot() + ")");
			
			boolean isValidMovement = false;
			
			isValidMovement = isValidMovement(pMRM.getPre(), pMRM.getPost());
			
			if (isValidMovement) {
				//System.out.println("[MASTER] valid new position for index (" + pMRM.getIndexRobot() + ") -- (" + pMRM.getPost().getX() + "," + pMRM.getPost().getY() + ")");
				getSender().tell(new ValidMovementMsg(pMRM.getPost()),getSelf());
			}	
			
			
		}else {
	        unhandled(arg0);
	    }
		
		
	}

}
