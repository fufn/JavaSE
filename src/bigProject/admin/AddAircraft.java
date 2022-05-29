package bigProject.admin;

import bigProject.Aircraft;
import bigProject.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddAircraft extends JFrame {
    public AddAircraft(){
        setLayout(null);
        setSize(500,500);
        setTitle("ADD AIRCRAFT");

        JLabel name = new JLabel("Name");
        name.setBounds(100,100,100,40);
        add(name);

        JTextField nameText = new JTextField();
        nameText.setBounds(150,100,100,40);
        add(nameText);

        JLabel model = new JLabel("Model");
        model.setBounds(100,150,100,40);
        add(model);

        JTextField modelText = new JTextField();
        modelText.setBounds(150,150,100,40);
        add(modelText);

        JLabel business_class_capacity = new JLabel("Business class capacity");
        business_class_capacity.setBounds(100,200,200,40);
        add(business_class_capacity);

        JTextField businessText = new JTextField();
        businessText.setBounds(250,200,100,40);
        add(businessText);

        JLabel econom_class_capacity = new JLabel("Econom class capacity");
        econom_class_capacity.setBounds(100,250,200,40);
        add(econom_class_capacity);

        JTextField economText = new JTextField();
        economText.setBounds(250,250,100,40);
        add(economText);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(100, 300, 100, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String model = modelText.getText();
                int business = Integer.parseInt(businessText.getText());
                int econom = Integer.parseInt(economText.getText());
                Aircraft aircraft = new Aircraft(null, name, model, business, econom);
                PackageData packageData = new PackageData();
                packageData.setOperationType("ADD AIRCRAFT");
                packageData.setAircraft(aircraft);
                try {
                    AdminGUI.outputStream.writeObject(packageData);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Admin.frame.adminAircrafts.generateTable();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                nameText.setText("");
                modelText.setText("");
                businessText.setText("");
                economText.setText("");
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
