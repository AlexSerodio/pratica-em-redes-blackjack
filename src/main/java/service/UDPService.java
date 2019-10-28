package service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import enums.Environment;

public class UDPService {

    public void send(String message) {
        byte[] byteMessage = message.getBytes();

        try {
            InetAddress ip = InetAddress.getByName(Environment.HOST_ADRESS);
            DatagramPacket pack = new DatagramPacket(byteMessage, byteMessage.length, ip, Environment.UDP_PORT);

            DatagramSocket socket = new DatagramSocket();

            socket.send(pack);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
