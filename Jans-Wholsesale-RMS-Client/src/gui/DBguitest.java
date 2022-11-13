package gui;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import DBconnection.*;
import Domain.Products;

public class DBguitest {

    public static void main(String[] args) throws SQLException {

        JFrame frame = new JFrame("TEST PROGRAM");


        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();
        Vector<Products> products = dbconn.showInventory();
        Enumeration e = products.elements();
        Vector<String> row = new Vector<String>();
        while(e.hasMoreElements()) {
            Products product = (Products) e.nextElement();
            row.add(product.getProdName());
        }

        JComboBox<String>comboBox = new JComboBox<String>(row);

        comboBox.setSelectedIndex(1);
        comboBox.setBounds(100, 50,150,20);
        frame.add(comboBox);
        frame.setLayout(null);
        frame.setSize(400,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




//        String[] columnNames ={"Product ID", "Product Name", "Short Description", "Long Description", "Quantity", "Price"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//
//
//        while(e.hasMoreElements()) {
//            Vector<String> row = new Vector<String>();
//            Products product = (Products) e.nextElement();
//            row.add(product.getProdCode());
//            row.add(product.getProdName());
//            row.add(product.getProdShortDesc());
//            row.add(product.getProdLongDesc());
//            row.add(Integer.toString(product.getProdStock()));
//            row.add(Double.toString(product.getUnitPrice()));
//            model.addRow(row);
//        }
//        JTable table = new JTable(model);
//        table.setBounds(50, 50, 400, 400);

//        JScrollPane sp=new JScrollPane(comboBox);

        frame.add(comboBox);
        frame.setLayout(null);
        frame.setSize(400,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        while(e.hasMoreElements()){
//            Object[] o = {((Products) e.nextElement()).getProdCode(), ((Products) e.nextElement()).getProdName(), ((Products) e.nextElement()).getProdShortDesc(), ((Products) e.nextElement()).getProdLongDesc(), ((Products) e.nextElement()).getProdStock(), ((Products) e.nextElement()).getUnitPrice()};
//            model.addRow(o);
//        }






    }
}
