package entity;

import java.net.InetAddress;

public class WeightData implements IWeightData {

	static double tara = 0;
	static double brutto = 0;
	static String instruktionsdisplay1 = "";
	static String instruktionsdisplay2 = "";
	static String streng_fra_bruger = "";
	static InetAddress connected_host = null;
	static String rm20_kommando = "";
	static boolean run = true;
	static String userchoice[] = null;

	public synchronized void resetWeight() {
		WeightData.tara = 0;
		WeightData.brutto = 0;
	}

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
		WeightData.rm20_kommando = rm20_kommando;
	}

	public synchronized InetAddress getConnected_host() {
		return connected_host;
	}

	public synchronized void setConnected_host(InetAddress inetAddress) {
		WeightData.connected_host = inetAddress;
	}

	public synchronized double getTara() {
		return tara;
	}

	public synchronized void setTara(double tara) {
		WeightData.tara += tara;
	}

	public synchronized double getBrutto() {
		return brutto;
	}

	public synchronized void setBrutto(double brutto) {
		WeightData.brutto += brutto;
	}

	public synchronized double getNetto() {
		return WeightData.brutto - WeightData.tara;
	}

	public synchronized String getInstruktionsdisplay1() {
		return instruktionsdisplay1;
	}

	public synchronized void setInstruktionsdisplay1(String instruktionsdisplay1) {
		WeightData.instruktionsdisplay1 = instruktionsdisplay1;
	}

	public synchronized String getInstruktionsdisplay2() {
		return instruktionsdisplay2;
	}

	public synchronized void setInstruktionsdisplay2(String instruktionsdisplay2) {
		WeightData.instruktionsdisplay2 = instruktionsdisplay2;
	}

	public synchronized String getStreng_fra_bruger() {
		return streng_fra_bruger;
	}

	public synchronized void setStreng_fra_bruger(String streng_fra_bruger) {
		WeightData.streng_fra_bruger = streng_fra_bruger;
	}

	public synchronized String[] getUserChoice() {
		return WeightData.userchoice;
	}

	public synchronized void setUserChoice(String[] s_array) {
		WeightData.userchoice = s_array;
	}
}
