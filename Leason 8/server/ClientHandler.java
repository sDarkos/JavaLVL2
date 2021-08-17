package server;

import javax.swing.table.TableRowSorter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private AtomicBoolean isAuth = new AtomicBoolean(false);

    public String getName() {
        return name;
    }


    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try {
                    doAuthentication(socket);
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    closeConnection(socket);
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket){
        server.unSubscribe(this);

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

    private void doAuthentication (Socket socket) throws IOException {
        sendMessage("Greeting your outstanding chat ");
        sendMessage("Please authentication: -auth login password");

        while (true) {

            new Thread(() -> {
                long timeToAuth = System.currentTimeMillis();
                while (true){
                    if((System.currentTimeMillis() - timeToAuth) >= 30000){
                        sendMessage("Time is out..");
                        sendMessage("Close connection...");
                        closeConnection(socket);
                        break;
                    } else if (isAuth.get()){
                        break;
                    }
                }
            }).start();

                String maybeCredentials = in.readUTF();
                if (maybeCredentials.startsWith("-auth")) {
                    String[] credentials = maybeCredentials.split("\\s");

                    Optional<AuthService.Entry> maybeUser = server.getAuthService()
                            .findUserByLoginAndPassword(credentials[1], credentials[2]);

                    if (maybeUser.isPresent()) {
                        AuthService.Entry user = maybeUser.get();
                        if (!server.isUserOccupied(user.getName())) {
                            name = user.getName();
                            isAuth.set(true);
                            sendMessage("Login OK...");
                            sendMessage("Welcome");
                            server.broadcastMessage("User " + name + " entered chat", "CHAT");
                            server.subscribe(this);
                            return;
                        } else {
                            sendMessage("Current user is already logged in...");
                        }
                    } else {
                        sendMessage("invalid credentials..");
                    }
                } else {
                    sendMessage("invalid uth operation");
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
                    String whisperMessage = "User " + name + " whisper you:";
                    for (int i = 2; i < whisper.length; i++) {
                        whisperMessage = whisperMessage + " "  + whisper[i];
                    }
                        server.whisperMessage(whisperMessage, whisper[1], this);
                }
                else {
                    sendMessage("User is not fond.");
                }
            } else {
                server.broadcastMessage(inBoundMessage ,name);
            }
        }
    }
}
