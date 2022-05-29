package bigProject.admin;

import bigProject.Aircraft;
import bigProject.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditAircraft extends JFrame {
    public EditAircraft(Aircraft aircraft){
        setLayout(null);
        setSize(500,500);
        setTitle("EDIT AIRCRAFT");
        JLabel name = new JLabel("Name");
        name.setBounds(100,100,100,40);
        add(name);

        JTextField nameText = new JTextField(aircraft.getName());
        nameText.setBounds(150,100,100,40);
        add(nameText);

        JLabel model = new JLabel("Model");
        model.setBounds(100,150,100,40);
        add(model);

        JTextField modelText = new JTextField(aircraft.getModel());
        modelText.setBounds(150,150,100,40);
        add(modelText);

        JLabel business_class_capacity = new JLabel("Business class capacity");
        business_class_capacity.setBounds(100,200,200,40);
        add(business_class_capacity);

        JTextField businessText = new JTextField(""+aircraft.getBusiness_class_capacity());
        businessText.setBounds(250,200,100,40);
        add(businessText);

        JLabel econom_class_capacity = new JLabel("Econom class capacity");
        econom_class_capacity.setBounds(100,250,200,40);
        add(econom_class_capacity);

        JTextField economText = new JTextField(""+aircraft.getEconom_class_capacity());
        economText.setBounds(250,250,100,40);
        add(economText);

        JButton addButton = new JButton("EDIT");
        addButton.setBounds(100, 300, 100, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String model = modelText.getText();
                int business = Integer.parseInt(businessText.getText());
                int econom = Integer.parseInt(economText.getText());
                Aircraft aircraft1 = new Aircraft(aircraft.getId(), name, model, business, econom);
//                aircraft.setName(name);
//                aircraft.setModel(model);
//                aircraft.setBusiness_class_capacity(business);
//                aircraft.setEconom_class_capacity(econom);
                PackageData packageData = new PackageData();
                packageData.setOperationType("EDIT AIRCRAFT");
                packageData.setAircraft(aircraft1);
                try {
                    AdminGUI.outputStream.writeObject(packageData);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Admin.frame.adminAircrafts.generateTable();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
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
