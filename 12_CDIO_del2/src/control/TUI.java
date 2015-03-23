package control;

public class TUI {

	public TUI() {
		// TODO Auto-generated constructor stub
	}
	public void print_Menu(WeightData vaegtdata) {

		System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (vaegtdata.getNetto())+ " kg" );
		System.out.println("Instruktionsdisplay: " + vaegtdata.getInstruktionsdisplay1());
		System.out.println("Sekundærtdisplay: " + vaegtdata.getInstruktionsdisplay2());
		if (vaegtdata.getRm20_kommando()!=""){
			System.out.println("Fra Bruger: " + vaegtdata.getRm20_kommando());
		}
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + vaegtdata.getConnected_host() );
		System.out.println("Brutto: " + (vaegtdata.getBrutto())+ " kg" );
		System.out.println("Streng modtaget: "+ vaegtdata.getStreng_fra_bruger());
		System.out.println(" ");
		System.out.println("Denne vægt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("på kommunikationsporten. ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vægt)");
		System.out.println("Tast B for ny brutto (svarende til at belastningen på vægt ændres)");
		System.out.println("Tast C for ny port");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		if (vaegtdata.getRm20_kommando()!="") {
			System.out.println("Svar på RM20 kommando");
			System.out.print ("Tast her: ");
		}
		else {
			System.out.print ("Tast her: ");
		}

	}

}
