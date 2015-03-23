package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IOController implements Runnable {
	Thread t;
	String name = "IO-Tråd";
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
			System.out.println("Venter på connection på port " + portdst);
			outstream.writeBytes("Velkommen til Vægt v.1.0 " + "\r\n");
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
			System.out.println("Venter på connection på port " + portdst);
			outstream.writeBytes("Velkommen til Mettler BBK Vægt-simulator " + "\r\n");
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
			System.out.println("Venter på connection på port " + portdst);
			outstream.writeBytes("Velkommen til Mettler BBK Vægt-simulator " + "\r\n");
			if (menutrue == 1){
				outstream.writeBytes("Tryk A for at vise vægtens kommandoer. " + "\r\n");
			}
			// outstream.writeBytes("\r" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void user_Input() throws IOException {

		// System.out.println("Indtast eventuel portnummer som 1. argument");
		// System.out.println("på kommando linien for andet portnr");

		try {

			while (!(inline = instream.readLine().toUpperCase()).isEmpty()) {

				if (inline.startsWith("Ÿ")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("A")) {
					menutrue = 2;
				}
				if (menutrue == 2){
					outstream.writeBytes("Kommandoer til vægten: " + "\r\n");
					outstream.writeBytes("1: T - Tarer vægten " + "\r\n");
					outstream.writeBytes("2: S - Vis nuværende vægt " + "\r\n");
					outstream.writeBytes("3: B ... - ændre brutto vægt på vægten " + "\r\n");
					outstream.writeBytes("4: D ... - ændre display 1's tekst " + "\r\n");
					outstream.writeBytes("5: DW - nulstil display 1 " + "\r\n");
					outstream.writeBytes("6: P111 - blahblah " + "\r\n");
					outstream.writeBytes("7: RM20 8 ... - kommandoer til vægtens bruger " + "\r\n");
					outstream.writeBytes("8: Q - afslut vægt " + "\r\n");
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
						this.vaegtdata.setInstruktionsdisplay1("");
						this.vaegtdata
								.setStreng_fra_bruger(inline.substring(0));
						outstream.writeBytes("D " + "A" + "cr" + "lf" + "\r\n");
					} else {
						this.vaegtdata
								.setStreng_fra_bruger(inline.substring(0));
						this.vaegtdata.setInstruktionsdisplay1(inline
								.substring(2, inline.length()));
					}
				} else if (inline.startsWith("P111")) {
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
					// ikke på en fysisk vægt
					String temp = inline.substring(2, inline.length());
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setBrutto(Double.parseDouble(temp));
					outstream.writeBytes("B " + "ændret Brutto til: "
							+ +(vaegtdata.getBrutto()) + " kg " + "cr" + "lf"
							+ "\r\n");
				} else if ((inline.startsWith("Q"))) {
					// denne ordre findes heller ikke på den fysiske vægt.
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					System.out.println("");
					System.out.println("Program stoppet Q modtaget på com port"
							+ "\r\n");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
				} else {
					outstream.writeBytes("" + "\r\n");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception her: " + e.getMessage());
			userdisc = 0;
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
