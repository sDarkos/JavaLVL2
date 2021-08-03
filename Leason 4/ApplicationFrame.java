import javax.swing.*;
import java.awt.*;

public class ApplicationFrame {

    private JFrame mainFrame;
    private final int EXIT_CODE = -1;


    public ApplicationFrame(){
        mainFrame = new JFrame();
        mainFrame.setTitle("Telekilogram v 1.0");
        mainFrame.setBounds(200,200,500,500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setJMenuBar(jMenuBar());

        LayoutManager borderLayout = new BorderLayout();
        mainFrame.setLayout(borderLayout);

        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(chatArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Container bottomContainer = new Container();

        TextField enterField = new TextField();
        enterField.addActionListener(e -> enterMessage(enterField,chatArea));

        JButton buttonEnter = new JButton("Enter");
        buttonEnter.addActionListener(e -> enterMessage(enterField,chatArea));

        LayoutManager borderLayoutManager = new BorderLayout();
        bottomContainer.setLayout(borderLayoutManager);

        bottomContainer.add(enterField, BorderLayout.CENTER);
        bottomContainer.add(buttonEnter, BorderLayout.EAST);

        mainFrame.add(jScrollPane, BorderLayout.CENTER);
        mainFrame.add(bottomContainer, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

    }

    private void enterMessage(TextField enterField, TextArea chatArea){

        if(isNotEmpty(enterField.getText())) {
            chatArea.setText(chatArea.getText() + "\n\n USER \n" + enterField.getText());
            enterField.setText("");
        }
    }

    private boolean isNotEmpty(String text){
        if(text.isEmpty()){
            return false;
        }else
            return true;
    }

    private JMenuBar jMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        jMenuBar.add(jMenuFile());

        return jMenuBar;
    }

    private JMenu jMenuFile(){

        JMenu jMenuFile = new JMenu("File");

        JMenuItem jMenuItemExit = new JMenuItem("Exit");

        jMenuItemExit.addActionListener(e -> System.exit(EXIT_CODE));

        jMenuFile.add(jMenuItemExit);

        return jMenuFile;
    }

}
