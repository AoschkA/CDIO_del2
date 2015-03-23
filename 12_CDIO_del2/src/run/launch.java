package run;

import java.io.IOException;

import control.IOController;

public class launch {
	public static void main(String args[]){
		
		if(args.length > 0) {
			String temp = args[0];
			int tempint = Integer.parseInt(temp);
			IOController io = new IOController(tempint);
			io.start();
		}else{
			IOController io = new IOController();
			io.start();
		}
	}
}