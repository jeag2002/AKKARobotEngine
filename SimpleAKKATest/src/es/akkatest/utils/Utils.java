package es.akkatest.utils;

public class Utils {
	
	public static int StringToInteger(String data) {
		int return_value = -1;
		
		try {
			return_value = Integer.parseInt(data);
		}catch(Exception e){
			return_value = -1;
		}
		
		return return_value;
	}

}
