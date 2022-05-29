package bigProject.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AdminGUI extends JPanel {
    static ObjectOutputStream outputStream;
    static ObjectInputStream inputStream;

    public AdminGUI(AdminFrame frame) throws IOException {
        setLayout(null);
        setSize(500,500);

        JLabel label = new JLabel("IP:");
        label.setBounds(100,100,100,50);
        add(label);

        JTextField textField = new JTextField("");
        textField.setBounds(150,110,200,30);
        add(textField);

        JLabel label1 = new JLabel("PORT:");
        label1.setBounds(100, 150, 100, 50);
        add(label1);

        JTextField textField1 = new JTextField("");
        textField1.setBounds(150,160,200,30);
        add(textField1);

        JButton button = new JButton("CONNECT");
        button.setBounds(100,200, 250, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ip = textField.getText();
                    int port = Integer.parseInt(textField1.getText());
                    Socket socket = new Socket(ip, port);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    setVisible(false);
                    frame.adminMainPanel.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    textField.setText("");
                    textField1.setText("");
                }
            }
        });
        add(button);
    }
}
