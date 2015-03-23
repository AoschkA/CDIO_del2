package control;

import java.io.IOException;

public class ClientController {
	public static void main(String args[]){
		
		try {
			IOController test = new IOController();
			test.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
