import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    private ServerSocket server;
    private Socket client;

    public Server() {
        start();
        communication();
        System.out.println("Server close connection..");
    }

    private void start(){
        try {
            server = new ServerSocket(8889);
            System.out.println("Server created ...");
            System.out.println("Waiting client connect ...");
            client = server.accept();
            System.out.println("Client connected: " + client + " ...Status..OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void communication() {

        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            AtomicBoolean isAlive = new AtomicBoolean(true);

            new Thread(() -> {
                try {
                    while (true) {
                        String inboundMessage = in.readUTF();

                        if (inboundMessage.equals("-exit")) {
                            out.writeUTF("-exit");
                            System.out.println("client close connection...");
                            isAlive.set(false);
                            break;
                        }

                        System.out.println();
                        System.out.println("Client:");
                        System.out.println(inboundMessage);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                if (!isAlive.get()){
                    break;
                }
                String outboundMessage = scanner.nextLine();
                out.writeUTF(outboundMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
