package control;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientController {
	private TUI TUI = new TUI();
	private static DataOutputStream outstream;
	private WeightData vaegtdata;

	public ClientController(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
	}

	private String getStringInput() {
		String input = "";
		try {
			input = TUI.getResponse();
		} catch (IOException e) {
			TUI.printMessage("No input detected");
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
			TUI.printMessage("Couldn't recognize the input");
			return getIntInput();
		}

		return output;
	}

	public void runMenu() throws InterruptedException {
		TUI.print_Menu(vaegtdata);
		boolean run = true;
		while (run) {
			Thread.sleep(1000);
			String answer = getStringInput();
			if (answer.equals("Q")) {
				run = false;
				TUI.printMessage("Du har afsluttet programmet");
			} else if (answer.equals("T")) {
				vaegtdata.taraWeight();
			} else if (answer.equals("B")) {
				TUI.printMessage("Indtast brutto vægt:");
				vaegtdata.setBrutto(getIntInput());
			} else {
				run = false;
				TUI.printMessage("Not a known input, please try again:");
				break;
			}
		}
	}

}
