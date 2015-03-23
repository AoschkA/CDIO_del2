package control;

import java.io.IOException;

public class ClientController {
	private WeightData vaegtdata;
	TUI tui;

	public ClientController(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		tui = new TUI(vaegtdata);
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

	public boolean runMenu() {
		tui.print_Menu(vaegtdata);
		String answer = getStringInput();
			if (answer.equals("Q")) {
				// terminate
				tui.printMessage("Du har afsluttet programmet");
				return true;
			} else if (answer.equals("T")) {
				vaegtdata.taraWeight();
				return false;
			} else if (answer.equals("B")) {
				tui.printMessage("Indtast brutto vægt:");
				vaegtdata.setBrutto(getIntInput());
				return false;
			}
			else {
				tui.printMessage("Not a known input, please try again:");
				return runMenu();
			}
		}

}
