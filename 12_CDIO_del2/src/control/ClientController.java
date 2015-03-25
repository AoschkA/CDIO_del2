package control;

import java.io.IOException;

import entity.WeightData;
import boundary.TUI;

public class ClientController implements Runnable {
	private WeightData vaegtdata;
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
			// terminate
			tui.printMessage("Du har afsluttet programmet");
			vaegtdata.setRun(false);
		} else if (answer.equals("T")) {
			vaegtdata.setTara(vaegtdata.getBrutto());
		} else if (answer.equals("B")) {
			tui.printMessage("Indtast brutto vægt:");
			vaegtdata.setBrutto(getIntInput());
			System.out.println(vaegtdata.getBrutto());
		} else if (answer.equals("S")) {
			tui.printMessage("Indtast dit svar: ");
			io.writeSocket("RM20 A " + getStringInput() + " crlf");
			vaegtdata.setRm20_kommando("");
		} else {
			tui.printMessage("Not a known input, please try again:");
			runMenu();
		}
	}

	@Override
	public void run() {
		while (vaegtdata.isRun()) {
			runMenu();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
