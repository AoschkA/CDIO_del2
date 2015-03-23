package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TUI {

	WeightData vaegtdata = new WeightData();
	private BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	public TUI(){
		vaegtdata.setInstruktionsdisplay1("Standard Display 1");
		vaegtdata.setBrutto(0);
		vaegtdata.setRm20_kommando("");
		try {
			vaegtdata.setConnected_host(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vaegtdata.setInstruktionsdisplay2("Standard Display 2");
	}
	
	public void printMessage(String message){
		System.out.println(message);
	}
	public String getResponse() throws IOException{
		return inFromUser.readLine();
	}
	
	public void print_Menu(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (this.vaegtdata.getNetto())+ " kg" );
		System.out.println("Instruktionsdisplay: " + this.vaegtdata.getInstruktionsdisplay1());
		System.out.println("Sekundærtdisplay: " + this.vaegtdata.getInstruktionsdisplay2());
		if (vaegtdata.getRm20_kommando()!=""){
			System.out.println("Fra Bruger: " + this.vaegtdata.getRm20_kommando());
		}
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + this.vaegtdata.getConnected_host() );
		System.out.println("Brutto: " + (this.vaegtdata.getBrutto())+ " kg" );
		System.out.println("Streng modtaget: "+ this.vaegtdata.getStreng_fra_bruger());
		System.out.println(" ");
		System.out.println("Denne vægt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("på kommunikationsporten. ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vægt)");
		System.out.println("Tast B for ny brutto (svarende til at belastningen på vægt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		if (this.vaegtdata.getRm20_kommando()!="") {
			System.out.println("Svar på RM20 kommando");
			System.out.print ("Tast her: ");
		}
		else {
			System.out.print ("Tast her: ");
		}

	}

}
