package bigProject.cashier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cashier {
    static CashierFrame frame;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        frame = new CashierFrame();
        frame.setVisible(true);
    }
}
