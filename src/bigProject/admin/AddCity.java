package bigProject.admin;

import bigProject.Aircraft;
import bigProject.City;
import bigProject.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddCity extends JFrame {
    public AddCity(){
        setLayout(null);
        setSize(500,500);
        setTitle("ADD CITY");

        JLabel name = new JLabel("Name");
        name.setBounds(100,100,100,40);
        add(name);

        JTextField nameText = new JTextField();
        nameText.setBounds(150,100,100,40);
        add(nameText);

        JLabel country = new JLabel("Country");
        country.setBounds(100,150,100,40);
        add(country);

        JTextField countyText = new JTextField();
        countyText.setBounds(150,150,100,40);
        add(countyText);

        JLabel short_name = new JLabel("Short Name");
        short_name.setBounds(100,200,200,40);
        add(short_name);

        JTextField short_nameText = new JTextField();
        short_nameText.setBounds(250,200,100,40);
        add(short_nameText);


        JButton addButton = new JButton("ADD");
        addButton.setBounds(100, 250, 100, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String country = countyText.getText();
                String short_name = short_nameText.getText();
                City city = new City(null, name, country, short_name);
                PackageData packageData = new PackageData();
                packageData.setOperationType("ADD CITY");
                packageData.setCity(city);
                try {
                    AdminGUI.outputStream.writeObject(packageData);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Admin.frame.adminCities.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                nameText.setText("");
                countyText.setText("");
                short_nameText.setText("");
            }
        });
        add(addButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(250, 250, 100, 40);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(exitButton);
    }
}
