package bigProject.cashier;

import bigProject.PackageData;
import bigProject.Ticket;
import bigProject.admin.FlightDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CashierMainPanel extends JPanel {
    JTable table;

    public CashierMainPanel(CashierFrame frame) throws IOException, ClassNotFoundException {
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
                AddTicket addTicket = null;
                try {
                    addTicket = new AddTicket();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                addTicket.setVisible(true);
            }
        });
        add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(100, 340, 1500, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                Long id = (Long) table.getModel().getValueAt(row, 0);
                String name = (String) table.getModel().getValueAt(row, 6);
                String surname = (String) table.getModel().getValueAt(row, 7);
                String passport_number = (String) table.getModel().getValueAt(row, 8);
                Ticket ticket = new Ticket();
                ticket.setName(name);
                ticket.setSurname(surname);
                ticket.setId(id);
                ticket.setPassport_name(passport_number);
                EditTicket editTicket = null;
                try {
                    editTicket = new EditTicket(ticket);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                editTicket.setVisible(true);
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
                Ticket ticket = new Ticket();
                ticket.setId(id);
                PackageData packageData = new PackageData();
                packageData.setOperationType("DELETE TICKET");
                packageData.setTicket(ticket);
                try {
                    CashierGUI.outputStream.writeObject(packageData);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Cashier.frame.mainPanel.generateTable();
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
                System.exit(1);
            }
        });
        add(exitButton);

    }
    public void generateTable() throws IOException, ClassNotFoundException {
        PackageData packageData = new PackageData();
        packageData.setOperationType("LIST TICKETS");
        try {
            CashierGUI.outputStream.writeObject(packageData);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        packageData = (PackageData) CashierGUI.inputStream.readObject();
        ArrayList<TicketDTO> ticketDTOS = packageData.getTicketDTOS();
        Object[][] data = new Object[ticketDTOS.size()][10];

        for (int i = 0; i < ticketDTOS.size(); i++) {
            data[i][0] = ticketDTOS.get(i).getId();
            data[i][1] = ticketDTOS.get(i).getDeparture_city();
            data[i][2] = ticketDTOS.get(i).getArrival_city();
            data[i][3] = ticketDTOS.get(i).getEconom_place_price();
            data[i][4] = ticketDTOS.get(i).getBusiness_place_price();
            data[i][5] = ticketDTOS.get(i).getDeparture_time();
            data[i][6] = ticketDTOS.get(i).getName();
            data[i][7] = ticketDTOS.get(i).getSurname();
            data[i][8] = ticketDTOS.get(i).getPassport_number();
            data[i][9] = ticketDTOS.get(i).getTicket_type();
        }
        String[] header = {"ID", "Departure City", "Arrival City", "Econom Place Price", "Business Place Price", "Departure Time", "Name", "Surname", "Passport number"
        , "TicketType"};
        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        table.setModel(tableModel);
    }
}
