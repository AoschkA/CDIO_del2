package run;
import control.ClientController;
import control.IOController;
import entity.WeightData;

public class launch {
	static WeightData vaegtdata = new WeightData();
	static IOController io;
	static ClientController cc;
	public static void main(String args[]){
		vaegtdata.setUserChoice(args);
		cc = new ClientController(vaegtdata);
		io = new IOController(vaegtdata);
		cc.start();
		try {
			io.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}