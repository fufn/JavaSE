package bigProject.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AdminMainPanel extends JPanel {

    public AdminMainPanel(AdminFrame frame) throws IOException {
        setLayout(null);
        setSize(500,500);
        JButton manage_aircrafts = new JButton("Manage Aircrafts");
        manage_aircrafts.setBounds(150, 100, 200, 40);
        manage_aircrafts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    frame.adminAircrafts.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                frame.adminAircrafts.setVisible(true);
            }
        });
        add(manage_aircrafts);


        JButton manage_cities = new JButton("Manage Cities");
        manage_cities.setBounds(150, 160, 200, 40);
        manage_cities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    frame.adminCities.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                frame.adminCities.setVisible(true);
            }
        });
        add(manage_cities);

        JButton manage_flights = new JButton("Manage Flights");
        manage_flights.setBounds(150, 220, 200, 40);
        manage_flights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    frame.adminFlights.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                frame.adminFlights.setVisible(true);
            }
        });
        add(manage_flights);
    }
}
