package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import exceptions.InputLengthException;

public class IOController implements Runnable {
	Thread t;
	String name = "IO-Tr�d";
	int userdisc = 1;
	static ServerSocket listener;
	private static int menutrue = 0;
	private static boolean running = true;
	private static String inline;
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	WeightData vaegtdata = new WeightData();

	public IOController() {
		try {
			listener = new ServerSocket(portdst);
			sock = listener.accept();
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
			this.vaegtdata.setConnected_host(sock.getInetAddress());
			System.out.println("Venter p� connection p� port " + portdst);
			outstream.writeBytes("Velkommen til V�gt v.1.0 " + "\r\n");
			// outstream.writeBytes("\r" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public IOController(int port) {
		try {
			portdst = port;
			listener = new ServerSocket(portdst);
			sock = listener.accept();
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
			this.vaegtdata.setConnected_host(sock.getInetAddress());
			System.out.println("Venter p� connection p� port " + portdst);
			outstream.writeBytes("Velkommen til Mettler BBK V�gt-simulator " + "\r\n");
			// outstream.writeBytes("\r" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public IOController(int port, int medmenu) {
		try {
			menutrue = medmenu;
			portdst = port;
			listener = new ServerSocket(portdst);
			sock = listener.accept();
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
			this.vaegtdata.setConnected_host(sock.getInetAddress());
			System.out.println("Venter p� connection p� port " + portdst);
			outstream.writeBytes("Velkommen til Mettler BBK V�gt-simulator " + "\r\n");
			if (menutrue == 1){
				outstream.writeBytes("Tryk A for at vise v�gtens kommandoer. " + "\r\n");
			}
			// outstream.writeBytes("\r" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void user_Input() throws IOException {

		// System.out.println("Indtast eventuel portnummer som 1. argument");
		// System.out.println("p� kommando linien for andet portnr");

		try {

			while (!(inline = instream.readLine().toUpperCase()).isEmpty()) {

				if (inline.startsWith("�")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("A")) {
					menutrue = 2;
				}
				if (menutrue == 2){
					outstream.writeBytes("Kommandoer til v�gten: " + "\r\n");
					outstream.writeBytes("1: T - Tarer v�gten " + "\r\n");
					outstream.writeBytes("2: S - Vis nuv�rende v�gt " + "\r\n");
					outstream.writeBytes("3: B ... - �ndre brutto v�gt p� v�gten " + "\r\n");
					outstream.writeBytes("4: D ... - �ndre display 1's tekst " + "\r\n");
					outstream.writeBytes("5: DW - nulstil display 1 " + "\r\n");
					outstream.writeBytes("6: P111 - blahblah " + "\r\n");
					outstream.writeBytes("7: RM20 8 ... - kommandoer til v�gtens bruger " + "\r\n");
					outstream.writeBytes("8: Q - afslut v�gt " + "\r\n");
					menutrue = 1;
				}
				if (inline.startsWith("RM20")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setRm20_kommando(inline.substring(2,
							inline.length()));
					outstream.writeBytes("RM20 " + "B" + "\r\n");
				} else if (inline.startsWith("DW")) {
					this.vaegtdata.setInstruktionsdisplay1("");
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("DW " + "A" + "cr" + "lf" + "\r\n");
				} else if (inline.startsWith("D")) {
					if (inline.equals("D")) {
						inline = instream.readLine();
						if (inline.length() >7)
							throw new InputLengthException();
					} else {
						if (inline.length() >9)
							throw new InputLengthException();
					}
					this.vaegtdata.setInstruktionsdisplay1(inline);
					this.vaegtdata
							.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("D " + "A" + "cr" + "lf" + "\r\n");
				} else if (inline.startsWith("P111")) {
					if (inline.equals("P111")) {
						inline = instream.readLine();
						if (inline.length() > 30)
							throw new InputLengthException();
					} else {
					if (inline.length() > 35)
						throw new InputLengthException();
					} 
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setInstruktionsdisplay2(inline.substring(2,
							inline.length()));
					outstream.writeBytes("P111 " + "A" + "cr" + "lf" + "\r\n");
				} else if (inline.startsWith("T")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setTara(vaegtdata.getBrutto());
					outstream.writeBytes("T " + "S " + "    "
							+ (vaegtdata.getTara()) + " kg " + "cr" + "lf"
							+ "\r\n");
				} else if (inline.equals("S")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("S " + "S " + "    "
							+ (vaegtdata.getNetto()) + " kg " + "cr" + "lf"
							+ "\r\n");
				} else if (inline.startsWith("B")) {
					// denne ordre findes
					// ikke p� en fysisk v�gt
					String temp = inline.substring(2, inline.length());
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setBrutto(Double.parseDouble(temp));
					outstream.writeBytes("B " + "�ndret Brutto til: "
							+ +(vaegtdata.getBrutto()) + " kg " + "cr" + "lf"
							+ "\r\n");
				} else if ((inline.startsWith("Q"))) {
					// denne ordre findes heller ikke p� den fysiske v�gt.
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					System.out.println("");
					System.out.println("Program stoppet Q modtaget p� com port"
							+ "\r\n");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
				} else {
					outstream.writeBytes("" + "\r\n");
				}
				System.out.println("ingen fejl");
			}
		}  catch (InputLengthException e) {
			outstream.writeBytes("ES"+"\r\n");
			System.out.println("fejl!");
			user_Input();
		}
	}

	@Override
	public void run() {

		while (running) {
			try {
				if (userdisc != 0) {
					user_Input();
				}
				if (userdisc == 0) {
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void start() {
		System.out.println("Starting " + name);
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}
}
