package run;
import control.IOController;
import control.WeightData;

public class launch {
	static WeightData vaegtdata = new WeightData();
	static IOController io;
	
	public static void main(String args[]){
		switch(args.length){
		case 0:
			io = new IOController(vaegtdata);
			io.start();
			break;
		case 1:
			io = new IOController(Integer.parseInt(args[0]), vaegtdata);
			io.start();
			break;
		case 2:
			io = new IOController(Integer.parseInt(args[0]),Integer.parseInt(args[1]), vaegtdata);
			io.start();
			break;
		default:
			io = new IOController(vaegtdata);
			io.start();
			break;	
		}
	}
}