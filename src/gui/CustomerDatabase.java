package gui;

import javax.swing.*;
import java.sql.SQLException;

public class CustomerDatabase {
    public CustomerDatabase(){
        JDialog dialog = new JDialog();
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Customer Database");

        JButton viewCustomers = new JButton("View Customers");
        JButton registerCustomers = new JButton("Register Customer");
        JButton removeCustomer = new JButton("Remove Customer");

        registerCustomers.addActionListener(e -> {
            try {
                Register reg = new Register();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        label.setBounds(200,40,300,20);
        viewCustomers.setBounds(100,100,300,30);
        registerCustomers.setBounds(100,150,300,30);
        removeCustomer.setBounds(100,200,300,30);

        panel.setLayout(null);
        panel.setSize(500, 500);
        panel.add(label);
        panel.add(viewCustomers);
        panel.add(registerCustomers);
        panel.add(removeCustomer);

        dialog.add(panel);
        dialog.setLayout(null);
        dialog.setModal(true);
        dialog.setSize(500, 500);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }
}
