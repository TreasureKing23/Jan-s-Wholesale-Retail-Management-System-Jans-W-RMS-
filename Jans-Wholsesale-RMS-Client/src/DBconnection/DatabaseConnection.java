package DBconnection;
import Domain.*;
import format.Address;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Vector;


public class DatabaseConnection {
    private static Connection conn = null;
    private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
    private static String url = "jdbc:mysql://localhost:3306/jans";
    private static String user = "root";
    private static String password = "admin";

    private static Customer cus;
    private static Products pro;
    private static Staff staff;

    public static Connection connectToDB() throws SQLException
    {
        conn = DriverManager.getConnection(url,user,password);
        logger.info("Connected to database");
        return conn;
    }


    public static void insertIntoCustomer(Customer cus) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO customer (CustomerID,Name,DOB,Telephone,EmailAddress,DateOfMembership,MembershipExpiryDate) VALUES ('"+cus.getCusID()+"','"+cus.getCusName()+"','"+cus.getDob()+"','"+cus.getTelephone()+"','"+cus.getEmail()+"','"+cus.getDateOfMembership()+"','"+cus.getDateOfMembershipExp()+"')";
        String query2 = "Insert into address (CustomerID,Street,Town,Parish) VALUES ('"+cus.getCusID()+"','"+cus.getAddress().getStreet()+"','"+cus.getAddress().getTown()+"','"+cus.getAddress().getParish()+"')";
        statement.executeUpdate(query);
        statement.executeUpdate(query2);
        logger.info("Customer added to the database");
    }

    public static Connection deleteCustomer(String id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "DELETE FROM customer WHERE CustomerID = '"+id+"'";
        statement.executeUpdate(query);
        logger.info("Customer deleted from the database");
        return conn;
    }


    public static  Connection insertIntoInventory(Products pro) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO inventory Values ('"+pro.getProdCode()+"','"+pro.getProdName()+"','"+pro.getProdShortDesc()+"','"+pro.getProdLongDesc()+"','"+pro.getProdStock()+"','"+pro.getUnitPrice()+"')";
        statement.executeUpdate(query);
        logger.info("Product added to the database");
        return conn;
    }

    public static  Vector<Products> showInventory() throws SQLException
    {
        Vector<Products> prodList = new Vector<>();
        String query = "SELECT * FROM inventory";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String prodCode = rs.getString(1);
            String prodName = rs.getString(2);
            String prodShortDesc = rs.getString(3);
            String prodLongDesc = rs.getString(4);
            String prodStock = rs.getString(5);
            String unitPrice = rs.getString(6);
            int prodStockInt = Integer.parseInt(prodStock);
            double unitPriceDouble = Double.parseDouble(unitPrice);
            pro = new Products(prodCode,prodName,prodShortDesc,prodLongDesc,prodStockInt,unitPriceDouble);
            prodList.add(pro);
        }
        logger.info("Product list retrieved from database");
        return prodList;
    }



    public static Connection deleteFromInventory(String id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "DELETE FROM inventory WHERE ProductCode = '"+id+"'";
        statement.executeUpdate(query);
        logger.info("Product deleted from the database");
        return conn;
    }

    public static Connection insertIntoStaff(Staff staff) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO Staff Values ('"+staff.getStaffID()+"','"+staff.getName()+"','"+staff.getPosition()+"','"+staff.getDepartment()+"','"+staff.getDateOfBirth().toString()+"')";
        statement.executeUpdate(query);
        logger.info("Staff added to the database");
        return conn;
    }

    public static Vector showStaff(Staff staff) throws SQLException
    {
        Vector<Staff> staffList = new Vector<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM staff";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String staffID = rs.getString(1);
            String name = rs.getString(2);
            String position = rs.getString(3);
            String department = rs.getString(4);
            String dateOfBirth = rs.getString(5);
            staff = new Staff(staffID,name,position,department,dateOfBirth);
            staffList.add(staff);
        }
        logger.info("Staff list retrieved from database");
        return staffList;
    }

    public static Connection deleteFromStaff(String id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "DELETE FROM staff WHERE StaffID = '"+id+"'";
        statement.executeUpdate(query);
        logger.info("Staff deleted from the database");
        return conn;
    }


    public  static  Connection insertIntoInvoice(Invoice invoice) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO checkout Values ('"+invoice.getInvoiceNo()+"','"+invoice.getBillingDate()+"','"+invoice.getItem()+"','"+invoice.getQuantity()+"','"+invoice.getCashierName()+"', '"+invoice.getCustomerName()+"')";
        statement.executeUpdate(query);
        logger.info("Invoice added to the database");
        return conn;
    }


    public static Connection cancelSale(int invoice) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "DELETE FROM checkout WHERE InvoiceNumber = '"+invoice+"'";

        try {
            statement.executeUpdate(query);
            logger.info("Transaction deleted from the database");
        } catch (SQLException e) {
            logger.error("Transaction not deleted from the database");
            e.printStackTrace();
        }
        return conn;
    }

    public static Vector salesReport(String itemName) throws  SQLException{
        Vector<String> summary = new Vector<>();
        Statement statement = conn.createStatement();
        String query = "Select Item, SUM(Quantity) as TotalSales from checkout WHERE Item = '"+itemName+"'";  //AND BillingDate between '"+firstDate+"' AND '"+secondDate+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
                String item = rs.getString(1);
                String quantity = String.valueOf(rs.getInt(2));;
                summary.add(item);
                summary.add(quantity);
            logger.info("Sales report generated");

        } catch (SQLException e) {
            logger.error("Sales report not generated");
            e.printStackTrace();
        }

        return summary;

    }





}
