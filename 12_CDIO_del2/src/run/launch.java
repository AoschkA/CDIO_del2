package run;

import java.io.IOException;
import control.IOController;

public class launch {
	public static void main(String args[]){
		
		IOController io = new IOController();
		io.start();
	}
}