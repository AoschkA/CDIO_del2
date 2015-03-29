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

	@Override
	public synchronized void resetWeight() {
		WeightData.tara = 0;
		WeightData.brutto = 0;
	}
	@Override
	public synchronized boolean isRun() {
		return run;
	}
	@Override
	public synchronized void setRun(boolean run) {
		WeightData.run = run;
	}
	@Override
	public synchronized String getRm20_kommando() {
		return rm20_kommando;
	}
	@Override
	public synchronized void setRm20_kommando(String rm20_kommando) {
		WeightData.rm20_kommando = rm20_kommando;
	}
	@Override
	public synchronized InetAddress getConnected_host() {
		return connected_host;
	}
	@Override
	public synchronized void setConnected_host(InetAddress inetAddress) {
		WeightData.connected_host = inetAddress;
	}
	@Override
	public synchronized double getTara() {
		return tara;
	}
	@Override
	public synchronized void setTara(double tara) {
		WeightData.tara += tara;
	}
	@Override
	public synchronized double getBrutto() {
		return brutto;
	}
	@Override
	public synchronized void setBrutto(double brutto) {
		WeightData.brutto += brutto;
	}
	@Override
	public synchronized double getNetto() {
		return WeightData.brutto - WeightData.tara;
	}
	@Override
	public synchronized String getInstruktionsdisplay1() {
		return instruktionsdisplay1;
	}
	@Override
	public synchronized void setInstruktionsdisplay1(String instruktionsdisplay1) {
		WeightData.instruktionsdisplay1 = instruktionsdisplay1;
	}
	@Override
	public synchronized String getInstruktionsdisplay2() {
		return instruktionsdisplay2;
	}
	@Override
	public synchronized void setInstruktionsdisplay2(String instruktionsdisplay2) {
		WeightData.instruktionsdisplay2 = instruktionsdisplay2;
	}
	@Override
	public synchronized String getStreng_fra_bruger() {
		return streng_fra_bruger;
	}
	@Override
	public synchronized void setStreng_fra_bruger(String streng_fra_bruger) {
		WeightData.streng_fra_bruger = streng_fra_bruger;
	}
	@Override
	public synchronized String[] getUserChoice() {
		return WeightData.userchoice;
	}
	@Override
	public synchronized void setUserChoice(String[] s_array) {
		WeightData.userchoice = s_array;
	}
}
