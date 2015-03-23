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
	String name = "IO-Tråd";
	int userdisc = 1;
	static ServerSocket listener;
	private static int menutrue = 0;
	private static String inline;
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	WeightData vaegtdata;

	// Jar fil kørt uden argumenter
	public IOController(WeightData vaegtdata) {
			this.vaegtdata = vaegtdata;
	}
	// Jar fil kørt med 1 argument (port)
	public IOController(int port, WeightData vaegtdata) {
			this.vaegtdata = vaegtdata;
			portdst = port;
	}
	// Jar fil kørt med 2 argumenter (port og menu)
	public IOController(int port, int medmenu, WeightData vaegtdata) throws IOException {
			this.vaegtdata = vaegtdata;
			menutrue = medmenu;
			portdst = port;
			if (menutrue == 1) {
				outstream.writeBytes("Tryk A for at vise vægtens kommandoer. "
						+ "\r\n");
			}
			// outstream.writeBytes("\r" + "\n");
	}
	public void user_Input() throws IOException {
		try {
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()
					&& !vaegtdata.isRun()) {
				listener = new ServerSocket(portdst);
				sock = listener.accept();
				instream = new BufferedReader(new InputStreamReader(
						sock.getInputStream()));
				outstream = new DataOutputStream(sock.getOutputStream());
				this.vaegtdata.setConnected_host(sock.getInetAddress());
				System.out.println("Currently connected: "
						+ this.vaegtdata.getConnected_host() + " Port: " + portdst);
				outstream.writeBytes("Velkommen til Mettler BBK Vægt-simulator "
						+ "\r\n");
				System.out.println(inline);
				if (inline.startsWith("Ÿ")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("�")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("A")) {
					menutrue = 2;
				}
				if (menutrue == 2) {
					outstream.writeBytes("Kommandoer til vægten: " + "\r\n");
					outstream.writeBytes("1: T - Tarer vægten " + "\r\n");
					outstream.writeBytes("2: S - Vis nuværende vægt " + "\r\n");
					outstream
							.writeBytes("3: B ... - ændre brutto vægt på vægten "
									+ "\r\n");
					outstream.writeBytes("4: D ... - ændre display 1's tekst "
							+ "\r\n");
					outstream.writeBytes("5: DW - nulstil display 1 " + "\r\n");
					outstream.writeBytes("6: P111 - blahblah " + "\r\n");
					outstream
							.writeBytes("7: RM20 8 ... - kommandoer til vægtens bruger "
									+ "\r\n");
					outstream.writeBytes("8: Q - afslut vægt " + "\r\n");
					menutrue = 1;
				}
				if (inline.startsWith("RM20")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setRm20_kommando(inline.substring(2,
							inline.length()));
					outstream.writeBytes("RM20 " + "B" + "crlf\r\n");
				} else if (inline.startsWith("DW")) {
					this.vaegtdata.setInstruktionsdisplay1("");
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("DW " + "A" + "crlf" + "\r\n");
				} else if (inline.startsWith("D")) {
					if (inline.equals("D")) {
						inline = instream.readLine();
						if (inline.length() > 7)
							throw new InputLengthException();
					} else {
						if (inline.length() > 9)
							throw new InputLengthException();
					}
					this.vaegtdata.setInstruktionsdisplay1(inline);
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("D " + "A" + "crlf" + "\r\n");
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
					outstream.writeBytes("T " + "S " + "     "
							+ (vaegtdata.getTara()) + " kg " + "cr" + "lf"
							+ "\r\n");
				} else if (inline.equals("S")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					if (vaegtdata.getNetto() >= 0) {
						outstream.writeBytes("S " + "S" + "      "
								+ (vaegtdata.getNetto()) + " kg " + "cr" + "lf"
								+ "\r\n");
					} else if (vaegtdata.getNetto() < 0) {
						outstream.writeBytes("S " + "S" + "     "
								+ (vaegtdata.getNetto()) + " kg " + "cr" + "lf"
								+ "\r\n");
					}
				} else if (inline.startsWith("B")) {
					// denne ordre findes
					// ikke p� en fysisk v�gt
					if (inline.equalsIgnoreCase("B")) {
						outstream.writeBytes("Værdi ikke tilføjet! \r\n");
					} else {
						String temp = inline.substring(2, inline.length());
						this.vaegtdata
								.setStreng_fra_bruger(inline.substring(0));
						this.vaegtdata.setBrutto(Double.parseDouble(temp));
						outstream.writeBytes("B " + "ændret Brutto til: "
								+ +(vaegtdata.getBrutto()) + " kg " + "cr"
								+ "lf" + "\r\n");
					}
				} else if ((inline.startsWith("Q"))) {
					// denne ordre findes heller ikke p� den fysiske v�gt.
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					outstream.writeBytes("Vægt lukkes \r\n");
					System.out.println("Program stoppet Q modtaget på com port"
							+ "\r\n");
					userdisc = 0;
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
				} else {
					outstream.writeBytes("" + "\r\n");
				}
				Thread.sleep(100);
			}
		} catch (InputLengthException e) {
			outstream.writeBytes("ES" + "\r\n");
			user_Input();
		} catch (Exception e) {
			userdisc = 0;
			System.out.println("fejl");
		}
	}

	@Override
	public void run() {
		while(vaegtdata.isRun()) {
			try {
				user_Input();
				if (userdisc == 0 || vaegtdata.isRun()) {
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("FUCK AF");
				e.printStackTrace();
			}
		}
	}
	public void start() throws InterruptedException {
		System.out.println("Starting " + name);
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}
}
