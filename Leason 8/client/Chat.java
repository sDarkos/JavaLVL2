package client;

import client.communication.ClientCommunicator;
import client.gui.ChatFrame;

import java.util.function.Consumer;

public class Chat {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;

    public Chat() {
        communicator = new ClientCommunicator();

        Consumer<String> outBoundMessageConsumer = communicator::sendMessage;

        frame = new ChatFrame(outBoundMessageConsumer);

        new Thread(()-> {
            while (true){
                String inBoundMessage = communicator.receiveMessage();
                frame.getInBoundMessageConsumer().accept(inBoundMessage);
            }
        })
                .start();
    }

}
