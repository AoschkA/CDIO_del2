package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IOController {
	static ServerSocket listener;
	private static String inline;
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	
	public void user_Input(WeightData vaegtdata) throws IOException{
		listener = new ServerSocket(portdst);
		System.out.println("Venter på connection på port " + portdst );
		System.out.println("Indtast eventuel portnummer som 1. argument");
		System.out.println("på kommando linien for andet portnr");
		sock = listener.accept();
		vaegtdata.setConnected_host(sock.getInetAddress());
		instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		outstream = new DataOutputStream(sock.getOutputStream());
		try{
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()){
				if (inline.startsWith("RM20")){
					vaegtdata.setRm20_kommando(inline.substring(2, inline.length()));
					outstream.writeBytes("RM20 " + "B");
				}
				else if (inline.startsWith("DW")) {
					vaegtdata.setInstruktionsdisplay1("");
					outstream.writeBytes("DW " + "A" + "cr" + "lf");
				}
				else if (inline.startsWith("D")){
					if (inline.equals("D")) {
						vaegtdata.setInstruktionsdisplay1("");
						outstream.writeBytes("D " + "A" + "cr" + "lf");
					}
					else
						vaegtdata.setInstruktionsdisplay1(inline.substring(2, inline.length()));
				}
				else if (inline.startsWith("P111")){
					vaegtdata.setInstruktionsdisplay2(inline.substring(2, inline.length()));
					outstream.writeBytes("P111 " + "A" + "cr" + "lf");
				}
				else if (inline.startsWith("T")){
					vaegtdata.setTara(vaegtdata.getBrutto());
					outstream.writeBytes("T " + "S " + "    " + (vaegtdata.getTara()) + " kg "+"cr" + "lf");
				}
				else if (inline.startsWith("S")){
					outstream.writeBytes("S " + "S " + "    " + (vaegtdata.getNetto()) + " kg "+"cr" + "lf");
				}
				else if (inline.startsWith("B")){ 
					// denne ordre findes
					//ikke på en fysisk vægt
					String temp= inline.substring(2,inline.length());
					vaegtdata.setBrutto(Double.parseDouble(temp));
				}
				else if ((inline.startsWith("Q"))){
					System.out.println("");
					System.out.println("Program stoppet Q modtaget paa com port");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
				}
			}
		}
		catch (Exception e){
			System.out.println("Exception: "+e.getMessage());
		}
	}
}
