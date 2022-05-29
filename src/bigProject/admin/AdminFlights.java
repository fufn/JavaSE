package bigProject.admin;

import bigProject.Flight;
import bigProject.PackageData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdminFlights extends JPanel {
    JTable table;
    public AdminFlights(AdminFrame frame){
        setLayout(null);
        setSize(1700,1000);

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,50, 1500, 250);
        add(scrollPane);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(100, 305, 1500, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFlight addFlight = null;
                try {
                    addFlight = new AddFlight();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                addFlight.setVisible(true);
            }
        });
        add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(100, 340, 1500, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = table.getSelectedRow();
                    Long id = (Long) table.getModel().getValueAt(row, 0);
                    String aircraft_name = (String) table.getModel().getValueAt(row, 1);
                    String aircraft_model = (String) table.getModel().getValueAt(row, 2);
                    String departure_city = (String) table.getModel().getValueAt(row, 3);
                    String departure_country = (String) table.getModel().getValueAt(row, 4);
                    String arrival_city = (String) table.getModel().getValueAt(row, 5);
                    String arrival_country = (String) table.getModel().getValueAt(row, 6);
                    String departure_time = (String) table.getModel().getValueAt(row, 9);
                    int econom = (int) table.getModel().getValueAt(row, 7);
                    int business = (int) table.getModel().getValueAt(row, 8);
                    FlightDTO flightDTO = new FlightDTO(id, aircraft_name, aircraft_model, econom, business, departure_city, departure_country, arrival_city, arrival_country,
                            departure_time);
                    EditFlight editFlight = new EditFlight(flightDTO);
                    editFlight.setVisible(true);
                } catch (Exception f){
                    f.printStackTrace();
                }
            }
        });
        add(editButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBounds(100, 375, 1500, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                Long id = (Long) table.getModel().getValueAt(row, 0);
                PackageData packageData = new PackageData();
                packageData.setOperationType("DELETE FLIGHT");
                Flight flight = new Flight();
                flight.setId(id);
                packageData.setFlight(flight);
                try {
                    AdminGUI.outputStream.writeObject(packageData);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        add(deleteButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(100, 410, 1500, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Admin.frame.adminMainPanel.setVisible(true);
            }
        });
        add(exitButton);

    }
    public void generateTable() throws IOException, ClassNotFoundException {
        PackageData packageData = new PackageData();
        packageData.setOperationType("LIST FLIGHTS");
        try {
            AdminGUI.outputStream.writeObject(packageData);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        packageData = (PackageData) AdminGUI.inputStream.readObject();
        ArrayList<FlightDTO> flights = packageData.getFlightDTOS();
        Object[][] data = new Object[flights.size()][10];

        for (int i = 0; i < flights.size(); i++) {
            data[i][0] = flights.get(i).getId();
            data[i][1] = flights.get(i).getName();
            data[i][2] = flights.get(i).getModel();
            data[i][3] = flights.get(i).getDeparture_city();
            data[i][4] = flights.get(i).getDeparture_country();
            data[i][5] = flights.get(i).getArrival_city();
            data[i][6] = flights.get(i).getArrival_country();
            data[i][7] = flights.get(i).getEconom_prlace_price();
            data[i][8] = flights.get(i).getBusines_place_price();
            data[i][9] = flights.get(i).getDeparture_time();
        }
        String[] header = {"ID", "Aircraft Name", "Aircraft Model", "Departure City", "Departure Country", "Arrival City", "Arrival Country", "Econom Place Price",
        "Business Place Price", "Departure Time"};
        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        table.setModel(tableModel);
    }
}
