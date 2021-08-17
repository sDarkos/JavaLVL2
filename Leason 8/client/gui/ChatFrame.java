package client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame {

    private final JFrame mainframe;
    private JTextArea chatArea;
    private JButton sendButton;
    private final Consumer<String> inBoundMessageConsumer;
    private final Consumer<String> outBoundMessageConsumer;

    public ChatFrame(Consumer<String> outBoundMessageConsumer) {
        this.outBoundMessageConsumer = outBoundMessageConsumer;
        inBoundMessageConsumer = createInBoundMessageConsumer();

        mainframe = new JFrame();

        mainframe.setTitle("Chat v.1.0");
        mainframe.setBounds(new Rectangle(400,700));
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setLayout(new BorderLayout());

        mainframe.add(createTop(), BorderLayout.CENTER);
        mainframe.add(createBot(), BorderLayout.SOUTH);

        mainframe.setVisible(true);

    }

    public Consumer<String> getInBoundMessageConsumer() {
        return inBoundMessageConsumer;
    }

    private  Consumer<String> createInBoundMessageConsumer (){
        return inBoundMessage -> chatArea.append(inBoundMessage + "\n");
    }

    private JPanel createTop(){
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        top.add(chatArea, BorderLayout.CENTER);
        return top;
    }

    private JPanel createBot(){
        JPanel bot = new JPanel();
        bot.setLayout(new BorderLayout());

        JTextField inputArea = new JTextField();
        inputArea.addActionListener(e-> {
            String text = inputArea.getText();
            outBoundMessageConsumer.accept(text);
            inputArea.setText("");
        });

        sendButton = new JButton("Submit");
        sendButton.addActionListener(e -> {
            String text = inputArea.getText();
            outBoundMessageConsumer.accept(text);
            inputArea.setText("");
        });

        bot.add(inputArea, BorderLayout.CENTER);
        bot.add(sendButton, BorderLayout.EAST);
        return bot;
    }
}
