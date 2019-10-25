import java.io.*;
import java.net.*;

public class TCPService {
	
	private Socket socket;
	private static final String RESPONSE_SEPARATOR = ":";
	
	public TCPService() {
		try {
			socket = new Socket(Environment.HOST_ADRESS, Environment.TCP_PORT);
			// socket.setKeepAlive(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] send(String input) {
        DataOutputStream dataStream;
		try {
			dataStream = new DataOutputStream(socket.getOutputStream());
			
			dataStream.write((input + '\n').getBytes());

	        InputStreamReader stream = new InputStreamReader(socket.getInputStream());
	        BufferedReader rec = new BufferedReader(stream);
	        String output = rec.readLine();

	        return output.split(RESPONSE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
			return new String[0];
		}

        
	}
	
	public void close() throws IOException {
        if (socket != null) {
        	socket.close();
        	socket = null;
        }
    }
}
