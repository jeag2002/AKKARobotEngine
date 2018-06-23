package es.akkatest;

import es.akkatest.engine.AntWorld;
import es.akkatest.utils.Utils;

public class Main {

	public static void main(String[] args) throws Exception{

		AntWorld aW;
		
		if (args.length >= 3) {
			
			int x = Utils.StringToInteger(args[0]);
			int y = Utils.StringToInteger(args[1]);
			int num_ants = Utils.StringToInteger(args[2]);
			
			System.out.println("[Create ANT] x::= " + x +  " y::= " + y +  " num_ants::= " + num_ants);
			
			aW = new AntWorld(x,y,num_ants);
			aW.createWorld();
			aW.run();
			
		}
		
	}

}
