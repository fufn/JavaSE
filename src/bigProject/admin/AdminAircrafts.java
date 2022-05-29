package bigProject.admin;

import bigProject.Aircraft;
import bigProject.PackageData;
import bigProject.admin.AdminFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdminAircrafts extends JPanel {
    JTable table;

    public AdminAircrafts (AdminFrame frame) throws IOException, ClassNotFoundException {
        setLayout(null);
        setSize(500,500);

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,50, 300, 250);
        add(scrollPane);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(100, 305, 300, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAircraft addAircraft = new AddAircraft();
                addAircraft.setVisible(true);
            }
        });
        add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(100, 340, 300, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = table.getSelectedRow();
                    Long id = (Long) table.getModel().getValueAt(row, 0);
                    String name = (String) table.getModel().getValueAt(row, 1);
                    String model = (String) table.getModel().getValueAt(row, 2);
                    int business = (int) table.getModel().getValueAt(row, 3);
                    int econom = (int) table.getModel().getValueAt(row, 4);
                    Aircraft aircraft = new Aircraft(id, name, model, business, econom);
                    EditAircraft editAircraft = new EditAircraft(aircraft);
                    editAircraft.setVisible(true);
                }
                catch (Exception f){
                    System.out.println("cannot edit null aircraft");
                }
            }
        });
        add(editButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBounds(100, 375, 300, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                Long id = (Long) table.getModel().getValueAt(row,0);
                PackageData packageData = new PackageData();
                packageData.setOperationType("DELETE AIRCRAFT");
                Aircraft aircraft = new Aircraft();
                aircraft.setId(id);
                packageData.setAircraft(aircraft);
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
        exitButton.setBounds(100, 410, 300, 30);
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
        packageData.setOperationType("LIST AIRCRAFTS");
        try {
            AdminGUI.outputStream.writeObject(packageData);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        packageData = (PackageData) AdminGUI.inputStream.readObject();
        ArrayList<Aircraft> aircrafts = packageData.getAircrafts();
        Object[][] data = new Object[aircrafts.size()][5];

        for (int i = 0; i < aircrafts.size(); i++) {
            data[i][0] = aircrafts.get(i).getId();
            data[i][1] = aircrafts.get(i).getName();
            data[i][2] = aircrafts.get(i).getModel();
            data[i][3] = aircrafts.get(i).getBusiness_class_capacity();
            data[i][4] = aircrafts.get(i).getEconom_class_capacity();
        }
        String[] header = {"ID", "Name", "Model", "Business_class_capacity", "Econom_class_capacity"};
        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        table.setModel(tableModel);
    }
}
