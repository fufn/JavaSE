package bigProject.admin;

import bigProject.Aircraft;
import bigProject.City;
import bigProject.PackageData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdminCities extends JPanel {
    JTable table;

    public AdminCities (AdminFrame frame){
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
                AddCity addCity = new AddCity();
                addCity.setVisible(true);
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
                    String country = (String) table.getModel().getValueAt(row, 2);
                    String short_name = (String) table.getModel().getValueAt(row, 3);
                    City city = new City(id, name, country, short_name);
                    EditCity editCity = new EditCity(city);
                    editCity.setVisible(true);
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
                Long id = (Long) table.getModel().getValueAt(row, 0);
                City city = new City();
                city.setId(id);
                PackageData packageData = new PackageData();
                packageData.setCity(city);
                packageData.setOperationType("DELETE CITY");
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
        packageData.setOperationType("LIST CITIES");
        try {
            AdminGUI.outputStream.writeObject(packageData);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        packageData = (PackageData) AdminGUI.inputStream.readObject();
        ArrayList<City> cities = packageData.getCities();
        Object[][] data = new Object[cities.size()][4];

        for (int i = 0; i < cities.size(); i++) {
            data[i][0] = cities.get(i).getId();
            data[i][1] = cities.get(i).getName();
            data[i][2] = cities.get(i).getCountry();
            data[i][3] = cities.get(i).getShort_name();
        }
        String[] header = {"ID", "Name", "Country", "Short Name"};
        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        table.setModel(tableModel);
    }
}
