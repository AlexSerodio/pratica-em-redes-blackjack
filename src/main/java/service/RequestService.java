package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.RequestParam;
import enums.RequestType;
import exceptions.InvalidUserException;
import java.nio.charset.Charset;
import model.Card;
import model.Message;
import model.Player;
import model.User;

public class RequestService {

    private final TCPService tcpService;
    private final UDPService udpService;

    private final String userId;
    private final String userPassword;

    public RequestService(String userId, String userPassword) throws InvalidUserException {
        this.userId = userId;
        this.userPassword = userPassword;
        tcpService = new TCPService();
        udpService = new UDPService();

        if (!validUser())
            throw new InvalidUserException("Usuário e/ou senha inválido.");
    }

    public String getUserId() {
        return userId;
    }

    public Message getMessage() {

        String request = buildRequest(
                RequestType.GET,
                RequestParam.MESSAGE,
                userId,
                userPassword);

        // formato do retorno: <userid>,<msg>
        String[] response = tcpService.send(request);

        if (response.length < 2) {
            return null;
        } else {
            return new Message(response[0], response[1]);
        }
    }

    public void sendMessage(String content, String userIdTo) {

        String request = buildRequest(
                RequestType.SEND,
                RequestParam.MESSAGE,
                userId,
                userPassword,
                userIdTo,
                content);

        udpService.send(request);
    }

    public void sendGameCommand(String command) {

        String request = buildRequest(
                RequestType.SEND,
                RequestParam.GAME,
                userId,
                userPassword,
                command);

        udpService.send(request);
    }

    public List<User> getUsers() {

        String request = buildRequest(
                RequestType.GET,
                RequestParam.USERS,
                userId,
                userPassword);

        List<User> users = new ArrayList<User>();

        // formato do retorno: <userid>,<username>,<wins>,<userid>,<username>,<wins>, ...
        String[] response = tcpService.send(request);

        if (response.length >= 3) {
            for (int i = 0; i < response.length; i += 3)
                users.add(new User(response[i], response[i + 1], response[i + 2]));
        }

        return users;
    }

    public Card getCard() {

        String request = buildRequest(
                RequestType.GET,
                RequestParam.CARD,
                userId,
                userPassword);

        // formato do retorno: <number>,<suit>
        String[] response = tcpService.send(request);

        if (response.length >= 2)
            return new Card(response[0], response[1]);
        else
            return null;
    }

    public List<Player> getPlayers() {

        String request = buildRequest(
                RequestType.GET,
                RequestParam.PLAYERS,
                userId,
                userPassword);

        List<Player> players = new ArrayList<Player>();

        // formato do retorno: <userid>,<status>,<userid>,<status>, ...
        String[] response = tcpService.send(request);

        if (response.length >= 2) {
            for (int i = 0; i < response.length; i += 2)
                players.add(new Player(response[i], response[i + 1]));
        }

        return players;
    }

    private boolean validUser() {
        String request = buildRequest(
                RequestType.GET,
                RequestParam.USERS,
                userId,
                userPassword);

        try {
            String response = Arrays.toString(tcpService.send(request));
        
            response = new String(response.getBytes(), Charset.forName("UTF-8"));

            if ("[Usuário inválido!]".equals(response))
                return false;

            return true;
        } catch(NullPointerException e) {
            return false;
        }
    }

    private String buildRequest(String requestType, String requestParam, String... attributes) {
        StringBuilder request = new StringBuilder();
        request
            .append(requestType)
            .append(" ")
            .append(requestParam)
            .append(" ");

        for (int i = 0; i < attributes.length; i++) {
            request.append(attributes[i]);

            if (i < attributes.length - 1)
                request.append(":");
        }

        System.out.println(request.toString());
        return request.toString();
    }

}
