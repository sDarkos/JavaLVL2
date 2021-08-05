package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> logonUsers;
    private final AuthService authService;

    public Server() {
        authService = new AuthService();
        logonUsers = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler user){
        logonUsers.add(user);
    }

    public void unSubscribe(ClientHandler user){
        logonUsers.remove(user);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isUserOccupied(String name){
        return logonUsers.stream()
                .anyMatch(u-> u.getName().equals(name));
    }

    public void broadcastMessage(String message){
        logonUsers.forEach(clientHandler -> clientHandler.sendMessage(message));
    }

    public  void whisperMessage(String message, String nick, ClientHandler user){
        logonUsers.forEach(clientHandler -> {
            if (clientHandler.getNick().equals(nick)){
                clientHandler.sendMessage(message);
            } else {
                user.sendMessage("User is offline");
            }
        });
    }
}
