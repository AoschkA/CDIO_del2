package control;

import java.io.IOException;

import exceptions.InputLengthException;
import exceptions.UnknownInputException;
import exceptions.UnsupportedWeightException;

public interface IWeightController {
	public int server_Run() throws IOException, UnknownInputException,
	InputLengthException, UnsupportedWeightException;
	public void writeSocket(String s) throws IOException;
	public void closeStreams();
}
