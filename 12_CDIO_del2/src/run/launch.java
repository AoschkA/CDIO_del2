package run;

import java.io.IOException;
import control.IOController;

public class launch {
	public static void main(String args[]){
		
		try {
			IOController io = new IOController();
			io.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
