package Client;

import Domain.Customer;
import Domain.Products;

import javax.swing.*;

public class ProductDetails {
    public ProductDetails(String id){
        JDialog addFrame = new JDialog();
        JPanel addPanel = new JPanel();

        JLabel label = new JLabel("Product Details");
        JLabel idLabel = new JLabel("Product Code: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel shortDescLabel = new JLabel("Short Description: ");
        JLabel longDescLabel = new JLabel("Long Description: ");
        JLabel quantityLabel = new JLabel("Quantity: ");
        JLabel priceLabel = new JLabel("Unit Price: ");

        JTextField idText = new JTextField();
        JTextField nameText = new JTextField();
        JTextField shortDescText = new JTextField();
        JTextField longDescText = new JTextField();
        JTextField quantityText = new JTextField();
        JTextField priceText = new JTextField();

        JButton Update = new JButton("Update");
        JButton Delete = new JButton("Delete");

        Client client = new Client();
        client.sendAction("Find Product");
        client.sendID(id);
        Products product = (Products) client.receiveObject();
        client.closeConnection();

        idText.setText(product.getProdCode());
        nameText.setText(product.getProdName());
        shortDescText.setText(product.getProdShortDesc());
        longDescText.setText(product.getProdLongDesc());
        quantityText.setText(Integer.toString(product.getProdStock()));
        priceText.setText(Double.toString(product.getUnitPrice()));

        label.setBounds(150,40,200,30);
        idLabel.setBounds(20,100,100,25);
        idText.setBounds(120,100,100,25);
        nameLabel.setBounds(20, 130,100,25);
        nameText.setBounds(120, 130,200,25);
        shortDescLabel.setBounds(20, 160,100,25);
        shortDescText.setBounds(120, 160,200,25);
        longDescLabel.setBounds(20, 190,100,25);
        longDescText.setBounds(120, 190,200,25);
        quantityLabel.setBounds(20, 220,100,25);
        quantityText.setBounds(120, 220,200,25);
        priceLabel.setBounds(20, 250,100,25);
        priceText.setBounds(120, 250,200,25);
        Update.setBounds(100, 300, 100, 25);
        Delete.setBounds(300, 300, 100, 25);



        Update.addActionListener(e -> {
            String name = nameText.getText();
            String shortDesc = shortDescText.getText();
            String longDesc = longDescText.getText();
            int quantity = Integer.parseInt(quantityText.getText());
            float price = Float.parseFloat(priceText.getText());

            Products prod = new Products(id, name, shortDesc, longDesc, quantity, price);
            Client client1 = new Client();
            client1.sendAction("Update Product");
            client1.sendID(id);
            client1.sendObject(prod);
            client1.receiveResponse();
            client1.closeConnection();
        });

        Delete.addActionListener(e -> {
            Client client1 = new Client();
            client1.sendAction("Delete Product");
            client1.sendID(id);
            client1.receiveResponse();
            client1.closeConnection();
        });

        addPanel.setLayout(null);
        addPanel.setSize(400,400);

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
        addPanel.add(Update);
        addPanel.add(Delete);

        addFrame.add(addPanel);
        addFrame.setLayout(null);
        addFrame.setSize(400,400);
        addFrame.setVisible(true);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
