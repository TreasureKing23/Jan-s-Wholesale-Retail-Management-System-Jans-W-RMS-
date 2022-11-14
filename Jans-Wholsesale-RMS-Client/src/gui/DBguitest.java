package gui;
import javax.swing.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

import DBconnection.*;
import Domain.Products;

public class DBguitest {

    public static void main(String[] args) throws SQLException {


//        DatabaseConnection dbconn = new DatabaseConnection();
//        dbconn.connectToDB();
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        Vector<Products> products = dbconn.showInventory();
//        Enumeration e = products.elements();
//        Vector<String> productName = new Vector<String>();
//        while(e.hasMoreElements()) {
//            Products product = (Products) e.nextElement();
//            productName.add(product.getProdName());
//        }
//        JComboBox<String>productsList = new JComboBox<>(productName);
//        productsList.setSelectedIndex(-1);
//
//
//        JFrame frame = new JFrame("Summary Report");
//        JPanel panel = new JPanel();
//        JLabel product = new JLabel("Product Name");
//        JLabel date = new JLabel("Date: ");
//        JLabel date2 = new JLabel("Date: ");
//
//        JFormattedTextField dateField = new JFormattedTextField(df);
//        JFormattedTextField dateField2 = new JFormattedTextField(df);
//
//        JButton searchButton = new JButton("Search");
//
//        product.setBounds(50, 50, 100, 30);
//        date.setBounds(50, 100, 100, 30);
//        date2.setBounds(50, 150, 100, 30);
//        productsList.setBounds(150, 50, 100, 30);
//        dateField.setBounds(150, 100, 100, 30);
//        dateField2.setBounds(150, 150, 100, 30);
//
//        panel.add(product);
//        panel.add(productsList);
//        panel.add(date);
//        panel.add(dateField);
//        panel.add(date2);
//        panel.add(dateField2);
//        panel.add(searchButton);
//
//        frame.add(panel);
//        frame.setSize(400, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);


        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();

        Vector<String> stuff= dbconn.salesReport("patt");

        System.out.println(stuff);

    }

}
