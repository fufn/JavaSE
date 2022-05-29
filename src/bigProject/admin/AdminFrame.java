package bigProject.admin;

import javax.swing.*;
import java.io.IOException;

public class AdminFrame extends JFrame {
    AdminMainPanel adminMainPanel;
    AdminAircrafts adminAircrafts;
    AdminGUI adminGui;
    AdminCities adminCities;
    AdminFlights adminFlights;

    public AdminFrame() throws IOException, ClassNotFoundException {
        setLayout(null);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN");
        adminGui = new AdminGUI(this);
        add(adminGui);
        adminGui.setVisible(true);

        adminMainPanel = new AdminMainPanel(this);
        add(adminMainPanel);
        adminMainPanel.setVisible(false);

        adminAircrafts = new AdminAircrafts(this);
        add(adminAircrafts);
        adminAircrafts.setVisible(false);

        adminCities = new AdminCities(this);
        add(adminCities);
        adminCities.setVisible(false);

        adminFlights = new AdminFlights(this);
        add(adminFlights);
        adminFlights.setVisible(false);

    }
}
