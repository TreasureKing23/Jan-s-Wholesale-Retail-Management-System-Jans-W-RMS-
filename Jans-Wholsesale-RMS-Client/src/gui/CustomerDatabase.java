package gui;

import DBconnection.DatabaseConnection;
import entities.Customer;
import entities.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class CustomerDatabase {
    public CustomerDatabase(){

        DatabaseConnection dbconn = new DatabaseConnection();
        Enumeration enu = null;
        try {
            dbconn.connectToDB();
            Vector<Customer> cus = dbconn.showCustomers();
            enu = cus.elements();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Enumeration finalEnu = enu;

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
            } //
        });

        viewCustomers.addActionListener(e -> {
            JDialog viewFrame = new JDialog();
            String[] columnNames ={"CUSTOMER ID","NAME","DOB","ADDRESS","Telephone","Email","Date of Membership","Date of Membership Expiry"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while(finalEnu.hasMoreElements()){
                Vector<String> row = new Vector<String>();
                Customer customer = (Customer) finalEnu.nextElement();
                row.add(customer.getCusID());
                row.add(customer.getCusName());
                row.add(customer.getDob());
                row.add(customer.getAddress().getStreet() + " " + customer.getAddress().getTown() + " " + customer.getAddress().getParish());
                row.add(customer.getTelephone());
                row.add(customer.getEmail());
                row.add(customer.getDateOfMembership());
                row.add(customer.getDateOfMembershipExp());
                model.addRow(row);
            }
            JTable table = new JTable(model);
            table.setBounds(50, 50, 400, 400);
            JScrollPane sp=new JScrollPane(table);
            viewFrame.add(sp);
            viewFrame.setModal(true);
            viewFrame.setResizable(false);
            viewFrame.setSize(1000, 1000);
            viewFrame.setVisible(true);
            viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
