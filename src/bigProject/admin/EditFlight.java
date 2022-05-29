package bigProject.admin;

import bigProject.Aircraft;
import bigProject.City;
import bigProject.Flight;
import bigProject.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class EditFlight extends JFrame {
    public EditFlight(FlightDTO flightDTO) throws IOException, ClassNotFoundException {
        setLayout(null);
        setSize(500,500);
        setTitle("ADD AIRCRAFT");
        PackageData packageData = new PackageData();
        packageData.setOperationType("LIST AIRCRAFTS");
        AdminGUI.outputStream.writeObject(packageData);
        packageData = (PackageData) AdminGUI.inputStream.readObject();
        ArrayList<Aircraft> aircrafts = packageData.getAircrafts();

        PackageData packageData1 = new PackageData();
        packageData1.setOperationType("LIST CITIES");
        AdminGUI.outputStream.writeObject(packageData1);
        packageData1 = (PackageData) AdminGUI.inputStream.readObject();
        ArrayList<City> cities = packageData1.getCities();

        String[] aircrafts_name = new String[100];
        for (int i = 0; i < aircrafts.size(); i++){
            aircrafts_name[i] = aircrafts.get(i).getName() + " " + aircrafts.get(i).getModel();
        }

        String[] cities_name = new String[100];
        for (int i = 0; i < cities.size(); i++){
            cities_name[i] = cities.get(i).getName() + " " + cities.get(i).getCountry();
        }


        JLabel aircraft = new JLabel("Aircraft");
        aircraft.setBounds(100,50,100,40);
        add(aircraft);

        JComboBox aircraftBox = new JComboBox(aircrafts_name);
        aircraftBox.setBounds(250,50,150,40);
        add(aircraftBox);

        JLabel departure = new JLabel("Departure City");
        departure.setBounds(100,100,100,40);
        add(departure);

        JComboBox departureBox = new JComboBox(cities_name);
        departureBox.setBounds(250,100,150,40);
        add(departureBox);

        JLabel arrival_city = new JLabel("Arrival City");
        arrival_city.setBounds(100,150,200,40);
        add(arrival_city);

        JComboBox arrivalBox = new JComboBox(cities_name);
        arrivalBox.setBounds(250,150,150,40);
        add(arrivalBox);

        JLabel departure_time = new JLabel("Departure time");
        departure_time.setBounds(100,200,200,40);
        add(departure_time);

        JTextField departure_timeText = new JTextField(flightDTO.getDeparture_time());
        departure_timeText.setBounds(250,200,150,40);
        add(departure_timeText);

        JLabel econom_place_priece = new JLabel("Econom place priece");
        econom_place_priece.setBounds(100,250,200,40);
        add(econom_place_priece);

        JTextField econom_place_prieceText = new JTextField(""+ flightDTO.getEconom_prlace_price());
        econom_place_prieceText.setBounds(250,250,150,40);
        add(econom_place_prieceText);

        JLabel business_place_priece = new JLabel("Business place priece");
        business_place_priece.setBounds(100,300,200,40);
        add(business_place_priece);

        JTextField business_place_prieceText = new JTextField("" + flightDTO.getBusines_place_price());
        business_place_prieceText.setBounds(250,300,150,40);
        add(business_place_prieceText);



        JButton addButton = new JButton("EDIT");
        addButton.setBounds(100, 350, 100, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long aircraft_id = null, departure_id = null , arrival_id = null;
                String aircraft_name = (String) aircraftBox.getSelectedItem();
                String departure_name = (String) departureBox.getSelectedItem();
                String arrival_name = (String) arrivalBox.getSelectedItem();
                for (Aircraft a: aircrafts){
                    if (aircraft_name.equals(a.getName() + " " + a.getModel())) {
                        aircraft_id = a.getId();
                        break;
                    }
                }
                for (City c: cities){
                    if (departure_name.equals(c.getName() + " " + c.getCountry())){
                        departure_id = c.getId();
                    }
                    if (arrival_name.equals(c.getName() + " " + c.getCountry())){
                        arrival_id = c.getId();
                    }
                }
                String departure_time = departure_timeText.getText();
                int econom = Integer.parseInt(econom_place_prieceText.getText());
                int business = Integer.parseInt(business_place_prieceText.getText());
                PackageData packageData2 = new PackageData();
                packageData2.setOperationType("EDIT FLIGHT");
                packageData2.setFlight(new Flight(flightDTO.getId(), aircraft_id, departure_id, arrival_id, departure_time, econom, business));
                try {
                    AdminGUI.outputStream.writeObject(packageData2);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Admin.frame.adminFlights.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        add(addButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(250, 350, 100, 40);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(exitButton);
    }
}
