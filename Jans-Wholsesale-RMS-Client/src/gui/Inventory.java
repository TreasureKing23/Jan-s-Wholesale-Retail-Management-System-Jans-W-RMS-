package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import DBconnection.*;
import Domain.Products;

public class Inventory {

    public Inventory()  {

        DatabaseConnection dbconn = new DatabaseConnection();
        Enumeration enu = null;
        try {
            dbconn.connectToDB();
            Vector<Products> products = dbconn.showInventory();
            enu = products.elements();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Enumeration finalEnu = enu; 


        JFrame iFrame = new JFrame();

        JPanel iPanel = new JPanel();

        JButton add = new JButton("Add");
        add.setBounds(50, 50, 100, 25);

        JButton view = new JButton("View");
        view.setBounds(50, 100, 100, 25);

        JButton goback = new JButton("Return");
        goback.setBounds(50, 150, 100, 25);

        goback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iFrame.dispose();
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



        iPanel.add(add);
        iPanel.add(view);
        iPanel.add(goback);

        iPanel.setLayout(null);

        iFrame.add(iPanel);

        iFrame.setVisible(true);
        iFrame.setSize(500, 500);
        iFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }
}
