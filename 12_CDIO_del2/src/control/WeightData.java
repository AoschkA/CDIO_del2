package control;

import java.net.InetAddress;

public class WeightData {

	static double tara = 0;
	static double brutto = 0;
	static String instruktionsdisplay1 = "";
	static String instruktionsdisplay2 = "";
	static String streng_fra_bruger = "";
	static InetAddress connected_host = null;
	static String rm20_kommando = "";
	
	public String getRm20_kommando() {
		return rm20_kommando;
	}

	public void setRm20_kommando(String rm20_kommando) {
		WeightData.rm20_kommando = rm20_kommando;
	}

	public InetAddress getConnected_host() {
		return connected_host;
	}

	public void setConnected_host(InetAddress inetAddress) {
		WeightData.connected_host = inetAddress;
	}

	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		WeightData.tara = tara;
	}

	public double getBrutto() {
		return brutto;
	}

	public void setBrutto(double brutto) {
		WeightData.brutto = brutto;
	}

	public double getNetto() {
		return WeightData.brutto - WeightData.tara;
	}


	public String getInstruktionsdisplay1() {
		return instruktionsdisplay1;
	}

	public void setInstruktionsdisplay1(String instruktionsdisplay1) {
		WeightData.instruktionsdisplay1 = instruktionsdisplay1;
	}

	public String getInstruktionsdisplay2() {
		return instruktionsdisplay2;
	}

	public void setInstruktionsdisplay2(String instruktionsdisplay2) {
		WeightData.instruktionsdisplay2 = instruktionsdisplay2;
	}

	public String getStreng_fra_bruger() {
		return streng_fra_bruger;
	}

	public void setStreng_fra_bruger(String streng_fra_bruger) {
		WeightData.streng_fra_bruger = streng_fra_bruger;
	}
	
	public void taraWeight() {
		tara = brutto;
		brutto = 0;
	}

	public WeightData() {
		// TODO Auto-generated constructor stub
	}

	

}
