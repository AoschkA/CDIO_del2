package entity;

import java.net.InetAddress;

public interface IWeightData {
	public double getBrutto();
	public double getTara();
	public double getNetto();
	public String getRm20_kommando();
	public String getInstruktionsdisplay1();
	public String getInstruktionsdisplay2();
	public InetAddress getConnected_host();
	public String getStreng_fra_bruger();
	public String[] getUserChoice();
	
	public void setBrutto(double brutto);
	public void setTara(double tara);
	public void setRm20_kommando(String rm20_kommando);
	public void setInstruktionsdisplay1(String instruktionsdisplay1);
	public void setInstruktionsdisplay2(String instruktionsdisplay2);
	public void setConnected_host(InetAddress inetAddress);
	public void setStreng_fra_bruger(String streng_fra_bruger);
	public void setUserChoice(String[] s_array);

	public boolean isRun();
	public void setRun(boolean run);
	
	public void resetWeight();
}
