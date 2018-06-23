package es.akkatest.engine;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import es.akkatest.actor.messages.ActWorldMsg;
import es.akkatest.actor.messages.InitWorldMsg;
import es.akkatest.actors.Listener;
import es.akkatest.actors.Master;
import es.akkatest.actors.beans.Position;
import es.akkatest.actors.beans.Table;
import es.akkatest.graphics.CGTemplate;
import es.akkatest.graphics.DrawCanvas;

public class AntWorld {
	
	private int x;
	private int y;
	private int numrobots;
	
	private ArrayList<Position> posArray;
	private Table table;
	
	private ActorSystem system;
	private ActorRef master;
	private ActorRef listenerAct;
	
	private CGTemplate cG;
	private DrawCanvas dC;
	
	
	private static final int NUM = 3;
	private static final int DELAY = 500;
	
	public AntWorld(int _x, int _y, int _numrobots) {
		x = _x;
		y = _y;
		numrobots = _numrobots;	
		posArray = new ArrayList<Position>();
		table = new Table(x,y);
		
		
		dC = new DrawCanvas();
		dC.setTable(table);
		
		cG = new CGTemplate(dC);
		
	}
	
	
	//--> initialize Graphics
	private void initializeGraphics() throws Exception{
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				cG.start();
			}
		});
		
		
	}
	
	
	//--> initialize global parameters
	private void initializeWorld() throws Exception{
		
		Random r = new Random();
		
		int flag = NUM;
		
		for(int i=0; i<numrobots; i++) {
			flag = NUM;
			while (flag >=0) {
				int x_pos = r.nextInt(x-1);
				int y_pos = r.nextInt(y-1);
				if (table.getTable()[x_pos][y_pos].equalsIgnoreCase("")) {
					table.getTable()[x_pos][y_pos] = "x";
					posArray.add(new Position(x_pos,y_pos));
					break;			
				}
				flag--;
			}
		}
		
	}
	
	//--> initialize AKKA System
	private void initializeAKKA() throws Exception{
		 system = ActorSystem.create("AntSystem");
		 listenerAct = system.actorOf(new Props(new UntypedActorFactory() {public UntypedActor create() {return new Listener(table, dC);}}), "listener");
		 master = system.actorOf(new Props(new UntypedActorFactory() {public UntypedActor create() {return new Master(table,posArray,listenerAct);}}), "master");
	}
	
	//--> create World
	public void createWorld() throws Exception {
		initializeWorld();
		initializeGraphics();
		initializeAKKA();
	}
	
	//--> run process
	public void run() throws Exception{
		master.tell(new InitWorldMsg());
		while(true) {
			Thread.sleep(DELAY);
			master.tell(new ActWorldMsg());
		}
	}
	
	
}
