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
	static ServerSocket listener;
	private static boolean running = true;
	private static String inline;
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	WeightData vaegtdata = new WeightData();

	public IOController() throws IOException{
		
	}
	public void user_Input() throws IOException {
		listener = new ServerSocket(portdst);
		System.out.println("Venter på connection på port " + portdst);
		// System.out.println("Indtast eventuel portnummer som 1. argument");
		// System.out.println("på kommando linien for andet portnr");
		sock = listener.accept();
		this.vaegtdata.setConnected_host(sock.getInetAddress());
		instream = new BufferedReader(new InputStreamReader(
				sock.getInputStream()));
		outstream = new DataOutputStream(sock.getOutputStream());
		try {
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()) {
				if (inline.startsWith("RM20")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setRm20_kommando(inline.substring(2,
							inline.length()));
					outstream.writeBytes("RM20 " + "B");
				} else if (inline.startsWith("DW")) {
					this.vaegtdata.setInstruktionsdisplay1("");
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("DW " + "A" + "cr" + "lf");
				} else if (inline.startsWith("D")) {
					if (inline.equals("D")) {
						this.vaegtdata.setInstruktionsdisplay1("");
						this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
						outstream.writeBytes("D " + "A" + "cr" + "lf");
					} else {
						this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
						this.vaegtdata.setInstruktionsdisplay1(inline.substring(2,
								inline.length()));
					}
				} else if (inline.startsWith("P111")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setInstruktionsdisplay2(inline.substring(2,
							inline.length()));
					outstream.writeBytes("P111 " + "A" + "cr" + "lf");
				} else if (inline.startsWith("T")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setTara(vaegtdata.getBrutto());
					outstream.writeBytes("T " + "S " + "    "
							+ (vaegtdata.getTara()) + " kg " + "cr" + "lf");
				} else if (inline.equals("S")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("S " + "S " + "    "
							+ (vaegtdata.getNetto()) + " kg " + "cr" + "lf");
				} else if (inline.startsWith("B")) {
					// denne ordre findes
					// ikke på en fysisk vægt
					String temp = inline.substring(2, inline.length());
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setBrutto(Double.parseDouble(temp));
				} else if ((inline.startsWith("Q"))) {
					// denne ordre findes heller ikke på den fysiske vægt.
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					System.out.println("");
					System.out
							.println("Program stoppet Q modtaget på com port");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Exception her: " + e.getMessage());
		}
	}

	@Override
	public void run() {

		while (running) {
			try {
				user_Input();
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
