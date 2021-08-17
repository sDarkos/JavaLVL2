import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private Socket client;

    public Client() {
        start();
        communication();
    }

    private void start(){
        try {
            client = new Socket("localhost", 8889);
            System.out.println("Connected to server...");
            System.out.println("Inter your message...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void communication(){
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
                            isAlive.set(false);
                            System.out.println("Please press ENTER to close client...");
                            break;
                        }

                        System.out.println();
                        System.out.println("Server");
                        System.out.println(inboundMessage);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                if (!isAlive.get()) {
                    System.out.println("Client closing...");
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
