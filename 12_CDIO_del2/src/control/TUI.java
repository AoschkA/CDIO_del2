package control;

public class TUI {

	public TUI() {
		// TODO Auto-generated constructor stub
	}
	public void print_Menu(int brutto, int tara, String InstruktionsDisplay, String CurrentHost, String inputFromHost) {

		System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (brutto-tara)+ " kg" );
		System.out.println("Instruktionsdisplay: " + InstruktionsDisplay );
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + CurrentHost );
		System.out.println("Brutto: " + (brutto)+ " kg" );
		System.out.println("Streng modtaget: "+ inputFromHost) ;
		System.out.println(" ");
		System.out.println("Denne vegt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("paa kommunikationsporten. ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vegt)");
		System.out.println("Tast B for ny brutto (svarende til at belastningen paa vegt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		System.out.print ("Tast her: ");
	}

}
