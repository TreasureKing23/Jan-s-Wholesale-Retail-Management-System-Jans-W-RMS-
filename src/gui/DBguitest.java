package gui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import DBconnection.*;
import entities.Customer;
import entities.Products;

public class DBguitest {

    public static void main(String[] args) throws SQLException {

        JFrame frame = new JFrame("Premier League System");


        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();
        Vector<Products> products = dbconn.showInventory();
        Enumeration e = products.elements();



        String[] columnNames ={"Product ID", "Product Name", "Short Description", "Long Description", "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);


        while(e.hasMoreElements()) {
            Vector<String> row = new Vector<String>();
            Products product = (Products) e.nextElement();
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

        frame.add(sp);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        while(e.hasMoreElements()){
//            Object[] o = {((Products) e.nextElement()).getProdCode(), ((Products) e.nextElement()).getProdName(), ((Products) e.nextElement()).getProdShortDesc(), ((Products) e.nextElement()).getProdLongDesc(), ((Products) e.nextElement()).getProdStock(), ((Products) e.nextElement()).getUnitPrice()};
//            model.addRow(o);
//        }




    }
}
