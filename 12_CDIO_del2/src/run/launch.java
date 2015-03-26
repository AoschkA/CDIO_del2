package run;
import control.ClientController;
import control.IOController;
import entity.WeightData;

public class launch {
	static WeightData vaegtdata = new WeightData();
	static IOController io = new IOController(vaegtdata);
	static ClientController cc = new ClientController(vaegtdata);
	public static void main(String args[]){
		vaegtdata.setUserChoice(args);
		cc.start();
		try {
			io.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}