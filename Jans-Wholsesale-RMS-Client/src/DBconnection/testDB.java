package DBconnection;
import Domain.Customer;
import format.Address;

import  java.sql.*;
import java.util.Vector;

public class testDB {

Customer customer = new Customer();
Address address = new Address();

    private static Connection conntest;

    static {
        try {
            conntest = new DatabaseConnection().connectToDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection checkout() throws SQLException{
        String query = "INSERT into checkout Values()";
        Statement statement = conntest.createStatement();
        statement.executeUpdate(query);
        return conntest;
    }

    public static Vector<Customer> showCustomers() throws SQLException {
        Vector<Customer> cusList = new Vector<>();
        String c = "customer";
        String query = "SELECT * FROM customer";
        String query2 = "SELECT * FROM address";
        PreparedStatement ps = conntest.prepareStatement(query);
        PreparedStatement ps2 = conntest.prepareStatement(query2);
        ResultSet rs = ps.executeQuery();
        ResultSet rs2 = ps2.executeQuery();
        while (rs.next() && rs2.next()) {
            String cusID = rs.getString(1);
            String cusName = rs.getString(2);
            String dob = rs.getString(3);
            String telephone = rs.getString(4);
            String email = rs.getString(5);
            String dateOfMembership = rs.getString(6);
            String dateOfMembershipExp = rs.getString(7);
            String street = rs2.getString(2);
            String town = rs2.getString(3);
            String parish = rs2.getString(4);
            Address address = new Address(street, town, parish);
            Customer customer = new Customer(cusID, cusName, dob,address, telephone, email, dateOfMembership, dateOfMembershipExp);
            cusList.add(customer);
        }
        return cusList;
    }

    public  static  Connection deleteCustomer(String id) throws SQLException {
        String query = "DELETE FROM customer WHERE CustomerID = '"+id+"'";
        String query2 = "DELETE FROM address WHERE CustomerID = '"+id+"'";
        Statement statement = conntest.createStatement();
        statement.executeUpdate(query);
        statement.executeUpdate(query2);
        return conntest;
    }




//For some reason the parish feild always null

    public static void main(String[] args) throws SQLException {

        try {
            Vector<Customer> cusList = showCustomers();
            for (Customer cus : cusList) {
                System.out.println(cus.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



//        String query = "SELECT * FROM inventory";
//        PreparedStatement ps = conn.prepareStatement(query);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()) {
//            System.out.println(rs.getString(rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2)));
//        }
    }

//    private static Connection conn = null;
//    private static String url = "jdbc:mysql://localhost:3306/jans";
//    private static String user = "root";
//    private static String password = "mersades123";
//
//    public static Connection connectToDB() throws SQLException
//    {
//        if(conn == null) {
//            conn = DriverManager.getConnection(url,user,password);
//        }
//        return conn;
//    }
//
//    public static Connection showInventory() throws SQLException {
//        String query = "SELECT * FROM inventory";
//        PreparedStatement ps = conn.prepareStatement(query);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()) {
//            System.out.println(rs.getString(rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2)));
//        }
//
//        return conn;
//    }

}