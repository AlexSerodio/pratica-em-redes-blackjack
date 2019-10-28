package service;

import java.io.*;
import java.net.*;

import enums.Environment;
import java.nio.charset.Charset;

public class TCPService {

    private static final String RESPONSE_SEPARATOR = ":";

    public String[] send(String input) {
        DataOutputStream dataStream;
        try {
            Socket socket = new Socket(Environment.HOST_ADRESS, Environment.TCP_PORT);

            dataStream = new DataOutputStream(socket.getOutputStream());
            dataStream.write((input + '\n').getBytes());

            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader rec = new BufferedReader(stream);
            String output = rec.readLine();
                
            socket.close();
            return output.split(RESPONSE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }

    }
}
