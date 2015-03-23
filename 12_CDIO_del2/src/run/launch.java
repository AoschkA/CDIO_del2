package run;
import control.IOController;

public class launch {
	public static void main(String args[]){
		
		if(args.length == 1) {
			IOController io = new IOController(Integer.parseInt(args[0]));
			io.start();
		}
		if(args.length == 2) {
			IOController io = new IOController(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			io.start();
		}else{
			IOController io = new IOController();
			io.start();
		}
	}
}