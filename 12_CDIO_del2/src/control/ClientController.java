package control;

import java.io.IOException;
import java.text.DecimalFormat;

import entity.WeightData;
import boundary.TUI;

public class ClientController implements Runnable, IClientController {
	WeightData vaegtdata;
	TUI tui;
	IOController io;
	static String name = "CC-Tråd";
	Thread t;
	DecimalFormat df = new DecimalFormat("0.00"); 
	WeightController wc = new WeightController();

	public ClientController(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		tui = new TUI(vaegtdata);
		io = new IOController(vaegtdata);
	}

	private String getStringInput() {
		String input = "";
		try {
			input = tui.getResponse();
		} catch (IOException e) {
			tui.printMessage("No input detected");
			return getStringInput();
		}
		return input;
	}

	private double getNumberInput() {
		double output = Double.parseDouble(getStringInput());
		return output;
	}

	public void runMenu() throws IOException, InterruptedException {

		tui.print_Menu(vaegtdata);
		String answer = getStringInput();
		if (answer.equals("Q")) {
			closeCC();
		} else if (answer.equals("R")) {
			vaegtdata.resetWeight();
			runMenu();
		} else if (answer.equals("T")) {
			vaegtdata.setTara(vaegtdata.getBrutto());
			runMenu();
		} else if (answer.equals("B")) {
			tui.printMessage("Indtast brutto vægt:");
			double tempvaegt = getNumberInput();
			if(tempvaegt <= 6.02 && tempvaegt >= 0.00){
				vaegtdata.setBrutto(tempvaegt);
			}else{
				tui.printMessage("Du har indtastet en for høj vægt, den skal være\n"
						+ "mellem 0 og 6.02kg, prøv igen.");
				Thread.sleep(2000);
				runMenu();
			}
			
			runMenu();
		} else if (answer.equals("S")) {
			tui.printMessage("Indtast dit svar: ");
			wc.writeSocket("RM20 A " + getStringInput());
			vaegtdata.setRm20_kommando("");
			runMenu();
		} else {
			tui.printMessage("Not a known input, please try again:");
			runMenu();
		}
	}

	public void closeCC() {
		// terminate
		this.vaegtdata.setRun(false);
		io.closeServer();
		//System.exit(0);
	}

	@Override
	public void run() {
		try {
			runMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		System.out.println("Starting " + name);
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}

}
