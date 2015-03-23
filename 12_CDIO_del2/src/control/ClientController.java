package control;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientController {
	private TUI TUI = new TUI();
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

	public boolean runMenu() {
		TUI.print_Menu(vaegtdata);
		String answer = getStringInput();
			if (answer.equals("Q")) {
				// terminate
				TUI.printMessage("Du har afsluttet programmet");
				return true;
			} else if (answer.equals("T")) {
				vaegtdata.taraWeight();
				return false;
			} else if (answer.equals("B")) {
				TUI.printMessage("Indtast brutto vægt:");
				vaegtdata.setBrutto(getIntInput());
				return false;
			}
			else {
				TUI.printMessage("Not a known input, please try again:");
				return runMenu();
			}
		}

}
