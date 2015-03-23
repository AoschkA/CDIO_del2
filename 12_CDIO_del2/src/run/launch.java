package run;

import java.io.IOException;

import control.IOController;

public class launch {
	public static void main(String args[]){
		
		if(args.length > 0) {
			int temp = Integer.parseInt(args[0]);
			IOController io = new IOController(temp);
			io.start();
		}else{
			IOController io = new IOController();
			io.start();
		}
	}
}