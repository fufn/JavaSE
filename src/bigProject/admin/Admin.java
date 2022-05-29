package bigProject.admin;

import java.io.IOException;

public class Admin {
    static AdminFrame frame;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        frame = new AdminFrame();
        frame.setVisible(true);
    }
}
