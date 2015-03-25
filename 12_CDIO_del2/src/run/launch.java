package run;
import control.ClientController;
import control.IOController;
import entity.WeightData;

public class launch {
	static WeightData vaegtdata = new WeightData();
	static IOController io;
	static ClientController cc = new ClientController(vaegtdata);
	public static void main(String args[]){
		switch(args.length){
		case 0:
			io = new IOController(vaegtdata);
			break;
		case 1:
			io = new IOController(Integer.parseInt(args[0]), vaegtdata);
			break;
			//Åbner op for flere muligheder i kommandolinjen, kun implementeret den ovenstående denne gang.
//		case 2:
//			try {
//				io = new IOController(Integer.parseInt(args[0]),Integer.parseInt(args[1]), vaegtdata);
//			} catch (NumberFormatException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			break;
		default:
			io = new IOController(vaegtdata);
			break;	
		}
		cc.start();
		try {
			io.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}