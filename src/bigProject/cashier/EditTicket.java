package bigProject.cashier;

import bigProject.PackageData;
import bigProject.Ticket;
import bigProject.admin.FlightDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class EditTicket extends JFrame {
    public EditTicket(Ticket ticket) throws IOException, ClassNotFoundException {
        setLayout(null);
        setSize(1500,1000);
        setTitle("ADD TICKET");
        PackageData packageData = new PackageData();
        packageData.setOperationType("LIST FLIGHTS");
        CashierGUI.outputStream.writeObject(packageData);
        packageData = (PackageData) CashierGUI.inputStream.readObject();
        ArrayList<FlightDTO> flightDTOS = packageData.getFlightDTOS();

        String[] flights = new String[100];
        String[] ticket_type = {"ec", "bc"};

        for (int i = 0; i < flightDTOS.size(); i++){
            flights[i] = "From " + flightDTOS.get(i).getDeparture_city() + " to " + flightDTOS.get(i).getArrival_city() + " depature time is " + flightDTOS.get(i).getDeparture_time()
                    + " econom price is " + flightDTOS.get(i).getEconom_prlace_price() + " business price is " + flightDTOS.get(i).getBusines_place_price();
        }

        JLabel aircraft = new JLabel("Flights");
        aircraft.setBounds(100,50,100,40);
        add(aircraft);

        JComboBox flightsBox = new JComboBox(flights);
        flightsBox.setBounds(250,50,1000,40);
        add(flightsBox);

        JLabel name = new JLabel("Name");
        name.setBounds(100,100,200,40);
        add(name);

        JTextField nameText = new JTextField(ticket.getName());
        nameText.setBounds(250,100,150,40);
        add(nameText);

        JLabel surname = new JLabel("Surname");
        surname.setBounds(100,150,200,40);
        add(surname);

        JTextField surnameText = new JTextField(ticket.getSurname());
        surnameText.setBounds(250,150,150,40);
        add(surnameText);

        JLabel passport = new JLabel("Passport Number");
        passport.setBounds(100,200,200,40);
        add(passport);

        JTextField passportText = new JTextField(ticket.getPassport_name());
        passportText.setBounds(250,200,150,40);
        add(passportText);

        JLabel ticketType = new JLabel("Ticket Type");
        ticketType.setBounds(100,250,200,40);
        add(ticketType);

        JComboBox ticketTypeText = new JComboBox(ticket_type);
        ticketTypeText.setBounds(250,250,150,40);
        add(ticketTypeText);

        JButton addButton = new JButton("EDIT");
        addButton.setBounds(100, 300, 100, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long flight_id = null;
                for (FlightDTO f: flightDTOS){
                    if (flightsBox.getSelectedItem().equals("From " + f.getDeparture_city() + " to " + f.getArrival_city() + " depature time is " + f.getDeparture_time()
                            + " econom price is " + f.getEconom_prlace_price() + " business price is " + f.getBusines_place_price())) {
                        flight_id = f.getId();
                        break;
                    }
                }
                String ticket_type = (String) ticketTypeText.getSelectedItem();
                String name = nameText.getText();
                String surname = surnameText.getText();
                String passport_number = passportText.getText();
                Ticket ticket1 = new Ticket(ticket.getId(), flight_id, name, surname, passport_number, ticket_type);
                PackageData packageData1 = new PackageData();
                packageData1.setOperationType("EDIT TICKET");
                packageData1.setTicket(ticket1);
                try {
                    CashierGUI.outputStream.writeObject(packageData1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Cashier.frame.mainPanel.generateTable();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        add(addButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(250, 300, 100, 40);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(exitButton);
    }
}
