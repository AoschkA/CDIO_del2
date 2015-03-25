package entity;

import java.net.InetAddress;

public class WeightData {

	double tara = 0;
	double brutto = 0;
	String instruktionsdisplay1 = "";
	String instruktionsdisplay2 = "";
	String streng_fra_bruger = "";
	InetAddress connected_host = null;
	String rm20_kommando = "";
	static boolean run = true;
	
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		WeightData.run = run;
	}

	public String getRm20_kommando() {
		return rm20_kommando;
	}

	public void setRm20_kommando(String rm20_kommando) {
		this.rm20_kommando = rm20_kommando;
	}

	public InetAddress getConnected_host() {
		return connected_host;
	}

	public void setConnected_host(InetAddress inetAddress) {
		this.connected_host = inetAddress;
	}

	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		this.tara = tara;
	}

	public double getBrutto() {
		return brutto;
	}

	public void setBrutto(double brutto) {
		this.brutto = brutto;
	}

	public double getNetto() {
		return this.brutto - this.tara;
	}


	public String getInstruktionsdisplay1() {
		return instruktionsdisplay1;
	}

	public void setInstruktionsdisplay1(String instruktionsdisplay1) {
		this.instruktionsdisplay1 = instruktionsdisplay1;
	}

	public String getInstruktionsdisplay2() {
		return instruktionsdisplay2;
	}

	public void setInstruktionsdisplay2(String instruktionsdisplay2) {
		this.instruktionsdisplay2 = instruktionsdisplay2;
	}

	public String getStreng_fra_bruger() {
		return streng_fra_bruger;
	}

	public void setStreng_fra_bruger(String streng_fra_bruger) {
		this.streng_fra_bruger = streng_fra_bruger;
	}
	
	public void taraWeight() {
		tara = brutto;
		brutto = 0;
	}

	public WeightData() {
		// TODO Auto-generated constructor stub
	}

	

}
