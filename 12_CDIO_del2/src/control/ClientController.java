package control;

import java.io.IOException;

public class ClientController {
	private TUI TUI = new TUI();
	
	private String getStringInput() {
		String input = "";
		try{
			input = TUI.getResponse();
		} catch (IOException e) {
			TUI.printMessage("No input detected");
			return getStringInput();
		}
		return input;
	}
	private int getIntInput() {
		int output=0;
		String input = getStringInput();
		
		try{
			output = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			TUI.printMessage("Couldn't recognize the input");
			return getIntInput();
		}
		
		return output;	
	}
	
	
		

}
