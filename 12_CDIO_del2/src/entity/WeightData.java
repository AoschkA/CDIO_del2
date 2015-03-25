package entity;

import java.net.InetAddress;

public class WeightData {

	static double tara = 0;
	static double brutto = 0;
	static String instruktionsdisplay1 = "";
	static String instruktionsdisplay2 = "";
	static String streng_fra_bruger = "";
	static InetAddress connected_host = null;
	static String rm20_kommando = "";
	static boolean run = true;
	
	public synchronized boolean isRun() {
		return run;
	}

	public synchronized void setRun(boolean run) {
		WeightData.run = run;
	}

	public synchronized String getRm20_kommando() {
		return rm20_kommando;
	}

	public synchronized void setRm20_kommando(String rm20_kommando) {
		this.rm20_kommando = rm20_kommando;
	}

	public synchronized InetAddress getConnected_host() {
		return connected_host;
	}

	public synchronized void setConnected_host(InetAddress inetAddress) {
		this.connected_host = inetAddress;
	}

	public synchronized double getTara() {
		return tara;
	}

	public synchronized void setTara(double tara) {
		this.tara = tara;
	}

	public synchronized double getBrutto() {
		return brutto;
	}

	public synchronized void setBrutto(double brutto) {
		this.brutto = brutto;
	}

	public synchronized double getNetto() {
		return this.brutto - this.tara;
	}


	public synchronized String getInstruktionsdisplay1() {
		return instruktionsdisplay1;
	}

	public synchronized void setInstruktionsdisplay1(String instruktionsdisplay1) {
		this.instruktionsdisplay1 = instruktionsdisplay1;
	}

	public synchronized String getInstruktionsdisplay2() {
		return instruktionsdisplay2;
	}

	public synchronized void setInstruktionsdisplay2(String instruktionsdisplay2) {
		this.instruktionsdisplay2 = instruktionsdisplay2;
	}

	public synchronized String getStreng_fra_bruger() {
		return streng_fra_bruger;
	}

	public synchronized void setStreng_fra_bruger(String streng_fra_bruger) {
		this.streng_fra_bruger = streng_fra_bruger;
	}
	
	public synchronized void taraWeight() {
		tara = brutto;
		brutto = 0;
	}
}
