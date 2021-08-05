package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private String nick;

    public String getNick() {
        return nick;
    }

    public String getName() {
        return name;
    }


    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection(socket);
        }
    }

    private void closeConnection(Socket socket){
        server.unSubscribe(this);
        server.broadcastMessage("User " + name + " is logout." );

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAuthentication () throws IOException {
        while (true){
            String maybeCredentials = in.readUTF();
            if (maybeCredentials.startsWith("-auth")){
                String[] credentials = maybeCredentials.split("\\s");

                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if(maybeUser.isPresent()){
                    AuthService.Entry user = maybeUser.get();
                    if (!server.isUserOccupied(user.getName())){
                        name = user.getName();
                        nick = user.getLogin();
                        sendMessage("Login OK...");
                        sendMessage("Welcome");
                        server.broadcastMessage("User " + name + "entered chat");
                        server.subscribe(this);
                        return;
                    }
                    else {
                        sendMessage("Current user is already logged in...");
                    }
                }else {
                    sendMessage("invalid credentials..");
                }
            }
        }
    }

    public void sendMessage(String outBoundMessage){
        try {
            out.writeUTF(outBoundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true){
            String inBoundMessage = in.readUTF();
            if (inBoundMessage.equals("-exit")){
                break;
            }
            if (inBoundMessage.startsWith("/w")){
                String[] whisper = inBoundMessage.split("\\s");
                if (server.getAuthService().isCreated(whisper[1])){
                    String whisperMessage = "";
                    for (int i = 2; i < whisper.length; i++) {
                        whisperMessage = whisperMessage + " "  + whisper[i];
                    }
                        server.whisperMessage(inBoundMessage, whisper[1], this);
                }
                else {
                    sendMessage("User is not fond.");
                }
            }
            server.broadcastMessage(inBoundMessage);
        }
    }
}
