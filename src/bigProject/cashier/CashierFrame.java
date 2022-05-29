package bigProject.cashier;

import bigProject.Aircraft;
import bigProject.PackageData;
import bigProject.admin.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CashierFrame extends JFrame {
    CashierMainPanel mainPanel;
    CashierGUI cashierGUI;

    public CashierFrame () throws IOException, ClassNotFoundException {
        setLayout(null);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cashierGUI = new CashierGUI(this);
        add(cashierGUI);
        cashierGUI.setVisible(true);

        mainPanel = new CashierMainPanel(this);
        add(mainPanel);
        mainPanel.setVisible(false);

    }
}
