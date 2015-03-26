package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import boundary.TUI;
import entity.WeightData;
import exceptions.InputLengthException;
import exceptions.UnknownInputException;

public class IOController implements Runnable, IIOController {
	Thread t;
	String name = "IO-Tråd";
	static ServerSocket listener;
	static String inline;
	static int portdst = 8000;
	static Socket sock;
	static BufferedReader instream;
	static DataOutputStream outstream;
	TUI tui = new TUI();
	WeightData vaegtdata = new WeightData();
	ClientController cc;

	// Konstruktør - standard
	public IOController(WeightData vaegtdata) {
		this.vaegtdata = vaegtdata;
		if (this.vaegtdata.getUserChoice().length == 0) {
		} else if (this.vaegtdata.getUserChoice().length > 1) {
			if(this.vaegtdata.getUserChoice().length > 0){
				portdst = Integer.parseInt(this.vaegtdata.getUserChoice()[0]);
			}
		}
		this.vaegtdata.setRun(true);
		
	}

	public void user_Input() throws IOException {
		try {
			listener = new ServerSocket(portdst);
			sock = listener.accept();
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
			this.vaegtdata.setConnected_host(sock.getInetAddress());
			tui.print_Menu(this.vaegtdata);
			outstream.flush();
			outstream.writeUTF("Velkommen til Mettler BBK Vægt-simulator "
					+ "\r\n");
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()
					&& this.vaegtdata.isRun()) {

				if (inline.startsWith("Ÿ")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("�")) {
					inline = inline.substring(21, inline.length());
				}
				if (inline.startsWith("RM20")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					this.vaegtdata.setRm20_kommando(inline.substring(7,
							inline.length()));
					writeSocket("RM20 " + "B");
					tui.print_Menu(this.vaegtdata);
				} else if (inline.startsWith("DW")) {
					this.vaegtdata.setInstruktionsdisplay1("");
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					writeSocket("DW " + "A");
					tui.print_Menu(this.vaegtdata);
				} else if (inline.startsWith("D")) {
					if (inline.equals("D")) {
						throw new InputLengthException();
						// TODO Auto-generated catch block
					} else if (inline.length() > 9) {
						throw new InputLengthException();
						// TODO Auto-generated catch block
					}
					this.vaegtdata.setInstruktionsdisplay1(inline);
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					writeSocket("D " + "A");
					tui.print_Menu(this.vaegtdata);
				} else if (inline.startsWith("P111")) {
					if (inline.equals("P111")) {
						this.vaegtdata.setStreng_fra_bruger(inline);
						this.vaegtdata.setInstruktionsdisplay2("");
						tui.print_Menu(this.vaegtdata);
					} else if (inline.length() > 35) {
						throw new InputLengthException();
						// TODO Auto-generated catch block
					} else {
						this.vaegtdata.setStreng_fra_bruger(inline);
						this.vaegtdata.setInstruktionsdisplay2(inline
								.substring(2, inline.length()));
						writeSocket("P111 " + "A");
						tui.print_Menu(this.vaegtdata);
					}
				} else if (inline.startsWith("T")) {
					this.vaegtdata.setStreng_fra_bruger(inline);
					this.vaegtdata.setTara(vaegtdata.getBrutto());
					writeSocket("T " + "S " + "     "
							+ (vaegtdata.getTara()) + " kg ");
					tui.print_Menu(this.vaegtdata);
				} else if (inline.equals("S")) {
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					if (vaegtdata.getNetto() >= 0) {
						writeSocket("S " + "S" + "      "
								+ (vaegtdata.getNetto()) + " kg ");
					} else if (vaegtdata.getNetto() < 0) {
						writeSocket("S " + "S" + "     "
								+ (vaegtdata.getNetto()) + " kg ");
					}
				} else if (inline.startsWith("B")) {
					// Ikke eksisterende på den rigtige vægt
					if (inline.equalsIgnoreCase("B")) {
						throw new InputLengthException();
						// TODO Auto-generated catch block
					} else {
						String temp = inline.substring(2, inline.length());
						this.vaegtdata
								.setStreng_fra_bruger(inline.substring(0));
						if (Double.parseDouble(temp) <= 6.02 && Double.parseDouble(temp) >= 0) {
							this.vaegtdata
									.setBrutto(Double.parseDouble(temp));
							writeSocket("DB ");
							tui.print_Menu(this.vaegtdata);
						} else {
							throw new InputLengthException();
							// TODO Auto-generated catch block
						}
					}
				} else if ((inline.startsWith("Q"))) {
					// denne ordre findes heller ikke p� den fysiske v�gt.
					this.vaegtdata.setStreng_fra_bruger(inline.substring(0));
					writeSocket("Vægt lukkes");
					tui.printMessage("Program stoppet Q modtaget på com port"
							+ "\r\n");
					vaegtdata.setRun(false);
					cc = new ClientController(vaegtdata);
					cc.closeCC();
					closeServer();
				} else {
					throw new UnknownInputException();
					// TODO Auto-generated catch block
				}
			}
		} catch (InputLengthException | IOException
				| UnknownInputException e) {
			if (!listener.isClosed()) {
				writeSocket("ES");
				// TODO Auto-generated catch block
			}
		}
	}

	public void writeSocket(String s) {

			try {
				outstream.writeBytes(s + "crlf\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void closeServer() {
		try {
			if (outstream != null)
				outstream.close();
			if (instream != null)
				instream.close();
			if (!listener.isClosed())
				listener.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			user_Input();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
