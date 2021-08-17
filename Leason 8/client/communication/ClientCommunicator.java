package client.communication;

import java.io.IOException;

public class ClientCommunicator {

    private final ClientConnector connector;

    public ClientCommunicator() {
        connector = new ClientConnector();
    }

    public void sendMessage (String boundMessage){
        try {
            connector.getOut().writeUTF(boundMessage);
        } catch (IOException e) {
            throw new RuntimeException("issue occurred while sending a message", e);
        }
    }

    public String receiveMessage(){
        try {
            return connector.getIn().readUTF();
        } catch (IOException e) {
            throw new RuntimeException("issue occurred while receiving a message", e);
        }
    }
}
