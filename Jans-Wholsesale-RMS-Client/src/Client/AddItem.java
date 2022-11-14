package Client;

import DBconnection.DatabaseConnection;
import Domain.Products;
import format.Address;

import javax.swing.*;
import java.sql.SQLException;

public class AddItem {

    public AddItem() throws SQLException {

        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();

        JDialog addFrame= new JDialog();
        JPanel addPanel = new JPanel();

        JLabel label = new JLabel("Register a new Item: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel shortDescLabel = new JLabel("Short Description: ");
        JLabel longDescLabel = new JLabel("Long Description: ");
        JLabel quantityLabel = new JLabel("Quantity: ");
        JLabel priceLabel = new JLabel("Unit Price: ");

        JTextField nameText = new JTextField();
        JTextField shortDescText = new JTextField();
        JTextField longDescText = new JTextField();
        JTextField quantityText = new JTextField();
        JTextField priceText = new JTextField();

        JButton register = new JButton("Register");

        label.setBounds(150,40,200,30);
        nameLabel.setBounds(20, 100,100,25);
        nameText.setBounds(120, 100,200,25);
        shortDescLabel.setBounds(20, 130,100,25);
        shortDescText.setBounds(120, 130,200,25);
        longDescLabel.setBounds(20, 160,100,25);
        longDescText.setBounds(120, 160,200,25);
        quantityLabel.setBounds(20, 190,100,25);
        quantityText.setBounds(120, 190,200,25);
        priceLabel.setBounds(20, 220,100,25);
        priceText.setBounds(120, 220,200,25);

        register.setBounds(150, 250, 100, 25);

        register.addActionListener(e -> {

            String id = "P" + (int)(Math.random() * 1000000);
            String name = nameText.getText();
            String shortDesc = shortDescText.getText();
            String longDesc = longDescText.getText();
            int quantity = Integer.parseInt(quantityText.getText());
            float price = Float.parseFloat(priceText.getText());

            Products product = new Products(id, name, shortDesc, longDesc, quantity, price);
            try {
                dbconn.insertIntoInventory(product);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        addPanel.add(label);
        addPanel.add(nameLabel);
        addPanel.add(nameText);
        addPanel.add(shortDescLabel);
        addPanel.add(shortDescText);
        addPanel.add(longDescLabel);
        addPanel.add(longDescText);
        addPanel.add(quantityLabel);
        addPanel.add(quantityText);
        addPanel.add(priceLabel);
        addPanel.add(priceText);
        addPanel.add(register);

        addFrame.add(addPanel);
        addFrame.setSize(400,400);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




    }

    public static void main(String[] args) throws SQLException {
        AddItem addItem = new AddItem();
    }
}
