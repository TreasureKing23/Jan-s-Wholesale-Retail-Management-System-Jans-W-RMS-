package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import DBconnection.*;
import Domain.Products;

public class Inventory {

    public Inventory()  {

        DatabaseConnection dbconn = new DatabaseConnection();
        Enumeration enu = null;
        Client client = new Client();
        client.sendAction("List Products");
        Vector<Products> products = (Vector<Products>) client.receiveObject();
        client.closeConnection();
        enu = products.elements();
        Enumeration finalEnu = enu; 


        JFrame iFrame = new JFrame();

        JPanel iPanel = new JPanel();
        JLabel label = new JLabel("Inventory");
        label.setBounds(250,40,200,25);

        JButton add = new JButton("Add Product");
        add.setBounds(100, 100, 300, 25);

        JButton view = new JButton("View Products");
        view.setBounds(100, 150, 300, 25);

        JButton pDetails = new JButton("Search Product");
        pDetails.setBounds(100, 200, 300, 25);

        add.addActionListener(e -> {
            new AddItem();
        });

        pDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchID = JOptionPane.showInputDialog(null, "Enter Product ID", "ENTER INPUT", JOptionPane.QUESTION_MESSAGE);
                if(searchID != null){
                    new ProductDetails(searchID);
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame viewFrame = new JFrame("INVENTORY");
                String[] columnNames ={"Product ID", "Product Name", "Short Description", "Long Description", "Quantity", "Price"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                while(finalEnu.hasMoreElements()) {
                    Vector<String> row = new Vector<String>();
                    Products product = (Products) finalEnu.nextElement();
                    row.add(product.getProdCode());
                    row.add(product.getProdName());
                    row.add(product.getProdShortDesc());
                    row.add(product.getProdLongDesc());
                    row.add(Integer.toString(product.getProdStock()));
                    row.add(Double.toString(product.getUnitPrice()));
                    model.addRow(row);
                }
                JTable table = new JTable(model);
                table.setBounds(50, 50, 400, 400);
                JScrollPane sp=new JScrollPane(table);

                viewFrame.add(sp);
                viewFrame.setSize(800, 800);
                viewFrame.setVisible(true);
                viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        iPanel.setSize(500, 500);
        iPanel.add(label);
        iPanel.add(add);
        iPanel.add(view);
        iPanel.add(pDetails);
        iPanel.setLayout(null);
        iFrame.add(iPanel);
        iFrame.setVisible(true);
        iFrame.setSize(500, 500);
        iFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
