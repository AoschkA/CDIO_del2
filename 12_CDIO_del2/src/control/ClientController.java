package control;

import java.io.IOException;

import entity.WeightData;
import boundary.TUI;

public class ClientController implements Runnable {
	WeightData vaegtdata;
	TUI tui;
	IOController io;
	static String name = "CC-Tråd";
	Thread t;

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

	private int getIntInput() {
		int output = 0;
		String input = getStringInput();
		try {
			output = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			tui.printMessage("Couldn't recognize the input");
			return getIntInput();
		}
		return output;
	}

	public void runMenu() {

		tui.print_Menu(vaegtdata);
		String answer = getStringInput();
		if (answer.equals("Q")) {
			closeCC();
		} else if (answer.equals("T")) {
			vaegtdata.setTara(vaegtdata.getBrutto());
			runMenu();
		} else if (answer.equals("B")) {
			tui.printMessage("Indtast brutto vægt:");
			vaegtdata.setBrutto(getIntInput());
			runMenu();
		} else if (answer.equals("S")) {
			tui.printMessage("Indtast dit svar: ");
			io.writeSocket("RM20 A " + getStringInput() + " crlf");
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
		System.exit(0);
	}

	@Override
	public void run() {
		runMenu();
	}

	public void start() {
		System.out.println("Starting " + name);
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}

}
