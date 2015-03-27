package control;

import java.io.IOException;

public interface IIOController {
	public void closeServer();
	public void get_User() throws IOException;
}
