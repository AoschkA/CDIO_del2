package boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import entity.WeightData;

public class TUI implements IUI{
	WeightData vaegtdata;
	private BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));

	public TUI(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		vaegtdata.setInstruktionsdisplay1("");
		vaegtdata.setBrutto(0);
		vaegtdata.setRm20_kommando("");
		vaegtdata.setInstruktionsdisplay2("");
	}

	public TUI() {
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public String getResponse() throws IOException {
		return inFromUser.readLine();
	}

	public void print_Weight() {
		System.out.println("*************************************************");
		if (this.vaegtdata.getRm20_kommando() != "") {
			System.out.println("Instruktion: "
					+ this.vaegtdata.getRm20_kommando());
		}
		if (this.vaegtdata.getInstruktionsdisplay1() == "") {
			System.out.println("Netto: " + (this.vaegtdata.getNetto()) + " kg");
		} else if (this.vaegtdata.getInstruktionsdisplay1() != "") {
			System.out.println("Instruktionsdisplay: "
					+ this.vaegtdata.getInstruktionsdisplay1());
		}
		if (this.vaegtdata.getInstruktionsdisplay2() != "") {
			System.out.println("Sekundærtdisplay: "
					+ this.vaegtdata.getInstruktionsdisplay2());
		}
		System.out.println("*************************************************");
	}

	public void print_Debug() {
		System.out.println("Debug info: ");
		System.out
				.println("Hooked up to " + this.vaegtdata.getConnected_host());
		System.out.println("Brutto: " + (this.vaegtdata.getBrutto()) + " kg");
		System.out.println("Streng modtaget: "
				+ this.vaegtdata.getStreng_fra_bruger());
		System.out.println(" ");
		System.out.println("Denne vægt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("på kommunikationsporten. ");
		System.out.println("*************************************************");
	}

	public void print_UserFeedBack() {
		System.out.println("Tast B for ny brutto \n\t(svarende til at belastningen på vægt ændres)");
		System.out.println("Tast T for tara \n\t(svarende til knaptryk paa vægt)");
		System.out.println("Tast R for at reset vægt-værdier \n\t(svarende til at fjerne belastningen og tara)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (B/T/R/Q for: \n\t"
				+ "(B)rutto Ændring / (T)arer Vægt / (R)Nultil Vægt / (Q)Afslut Vægt)");
		if (this.vaegtdata.getRm20_kommando() != "") {
			System.out.println("Tast S for at svare på RM20 kommando");
			System.out.print("Tast her: ");
		} else {
			System.out.print("Tast her: ");
		}
	}

	public void print_Menu(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		clearScreen();
		if (this.vaegtdata.getUserChoice().length == 0) {
			print_Weight();
			print_Debug();
			print_UserFeedBack();
		} else if (this.vaegtdata.getUserChoice().length > 1) {
			if (Integer.parseInt(this.vaegtdata.getUserChoice()[1]) == 0) {
				print_Weight();
			}
			else if (Integer.parseInt(this.vaegtdata.getUserChoice()[1]) == 1) {
				print_Weight();
				print_UserFeedBack();
			}
			else if (Integer.parseInt(this.vaegtdata.getUserChoice()[1]) == 2) {
				print_Weight();
				print_Debug();
				print_UserFeedBack();
			} else {
				System.out.println("Indtast en værdi mellem 0 og 2!");
				System.exit(0);
			}
		}
	}

	public void clearScreen() {
		// Udskriv 25 tomme linjer
		for(int i = 0;i < 30;i++){
			System.out.println("");
		}

	}
}
