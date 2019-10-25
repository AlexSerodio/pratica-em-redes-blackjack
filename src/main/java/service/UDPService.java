package service;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import enums.Environment;

public class UDPService {

	private DatagramSocket socket;
	
	public UDPService() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message) {
    	byte[] byteMessage = message.getBytes();
    	
		try {
			InetAddress ip = InetAddress.getByName(Environment.HOST_ADRESS);
			DatagramPacket pack = new DatagramPacket(byteMessage, byteMessage.length, ip, Environment.UDP_PORT);
			
			socket.send(pack);
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
        if (socket != null) {
        	socket.close();
        	socket = null;
        }
    }
	
}
