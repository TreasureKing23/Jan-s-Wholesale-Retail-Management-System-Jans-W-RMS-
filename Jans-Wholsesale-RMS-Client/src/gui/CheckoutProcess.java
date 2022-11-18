package gui;

import Client.Client;
import Domain.Customer;
import Domain.Invoice;
import Domain.Products;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Vector;

public class CheckoutProcess {


    public void Select(int choice) throws SQLException {

        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        int invoiceNumber = (int)(Math.random()*(200-100+1)+100);
        Client client = new Client();

        client.sendAction("List Products");
        Vector<Products> products = (Vector<Products>) client.receiveObject();
        client.closeConnection();
        Enumeration e = products.elements();
        Vector<String> productName = new Vector<String>();
        Vector<Double> prices = new Vector<>();
        while(e.hasMoreElements()) {
            Products product = (Products) e.nextElement();
            productName.add(product.getProdName());
            prices.add(product.getUnitPrice());
        }
        JComboBox<String>productsList = new JComboBox<>(productName);
        productsList.setSelectedIndex(-1);



        JFrame frame = new JFrame("Checkout");
        JPanel panel = new JPanel();
        JLabel name = new JLabel("Customer Name");
        JLabel product = new JLabel("Product Name");
        JLabel quantity = new JLabel("Quantity");
        JLabel price = new JLabel("Price");
        JLabel subtotal = new JLabel("subTotal");

        JTextField nameField = new JTextField("Unregistered Customer");
        JTextField quantityField = new JTextField("1");
        JTextField priceField = new JTextField();
        JTextField subtotalField = new JTextField();


        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add");


        name.setBounds(50, 50, 100, 30);
        product.setBounds(50, 100, 100, 30);
        quantity.setBounds(50, 150, 100, 30);
        price.setBounds(50, 200, 100, 30);
        subtotal.setBounds(50, 250, 100, 30);


        nameField.setBounds(150, 50, 100, 30);
        productsList.setBounds(150, 100, 100, 30);
        quantityField.setBounds(150, 150, 100, 30);
        priceField.setBounds(150, 200, 100, 30);
        subtotalField.setBounds(150, 250, 100, 30);


        addButton.setBounds(50, 350, 100, 30);
        cancelButton.setBounds(200, 350, 100, 30);


        subtotalField.setEditable(false);
        nameField.setEditable(false);
        priceField.setEditable(false);


        productsList.addActionListener(e1 -> {
            int index = productsList.getSelectedIndex();
            priceField.setText(Double.toString(prices.get(index)));
            subtotalField.setText("");
            quantityField.setText("0");

        });

        quantityField.addActionListener(e12 -> {
            int index = productsList.getSelectedIndex();
            double price1 = prices.get(index);
            int quantity1 = Integer.parseInt(quantityField.getText());
            subtotalField.setText(Double.toString(price1 * quantity1));
        });

        panel.setLayout(null);
        panel.setSize(500, 500);
        panel.setVisible(true);
        panel.add(name);
        panel.add(product);
        panel.add(quantity);
        panel.add(price);
        panel.add(subtotal);



        panel.add(productsList);
        panel.add(quantityField);
        panel.add(priceField);
        panel.add(subtotalField);


        panel.add(cancelButton);
        panel.add(addButton);

        switch (choice) {
            case 1 -> {
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Client sclient = new Client();
                sclient.sendAction("List Customers");
                Vector<Customer> customerName = (Vector<Customer>) sclient.receiveObject();
                sclient.closeConnection();
                Enumeration ce = customerName.elements();
                Vector<String> cRow = new Vector<>();
                while (ce.hasMoreElements()) {
                    Customer cusName = (Customer) ce.nextElement();
                    cRow.add(cusName.getCusName());
                }
                JComboBox<String> comboBox = new JComboBox<>(cRow);
                comboBox.setSelectedIndex(1);
                comboBox.setBounds(150, 50, 100, 30);
                panel.add(comboBox);

                addButton.addActionListener(e13 -> {

                    if(productsList.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a product");
                    } else {
                        String customerName1 = comboBox.getSelectedItem().toString();
                        String billingDate = dateTime.format(LocalDateTime.now());
                        String itemName = productsList.getSelectedItem().toString();
                        int quantity12 = Integer.parseInt(quantityField.getText());
                        String Cashier = "Cashier";

                        Invoice invoice = new Invoice(invoiceNumber,billingDate,itemName, quantity12,Cashier, customerName1);
                        Client client1 = new Client();
                        client1.sendAction("Add Invoice");
                        client1.sendObject(invoice);
                        client1.receiveResponse();
                        client1.closeConnection();


                    }
                });

            }
            case 2 -> {
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(productsList.getSelectedIndex() == -1) {
                            JOptionPane.showMessageDialog(null, "Please select a product");
                        } else {
                            String customerName = nameField.getText();
                            String billingDate = dateTime.format(LocalDateTime.now());
                            String itemName = productsList.getSelectedItem().toString();
                            int quantity = Integer.parseInt(quantityField.getText());
                            String Cashier = "Cashier";

                            Invoice invoice = new Invoice(invoiceNumber,billingDate,itemName,quantity,Cashier, customerName);
                            Client client1 = new Client();
                            client1.sendAction("Add Invoice");
                            client1.sendObject(invoice);
                            client1.receiveResponse();
                            client1.closeConnection();
                        }
                    }
                });
                panel.add(nameField);

            }
        }

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Client client1 = new Client();
            client1.sendAction("Delete Invoice");
            client1.receiveResponse();
            client1.closeConnection();
            }
        });



        frame.add(panel);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        }

    public static void main(String[] args) {
        CheckoutProcess checkoutProcess = new CheckoutProcess();
        try {
            checkoutProcess.Select(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

