package DBconnection;
import entities.*;



import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnection {
    private static Connection conn = null;
    private static String url = "jdbc:mysql://localhost:3306/jans";
    private static String user = "root";
    private static String password = "mersades123";

    private static Customer cus;
    private static Products pro;
    private static Staff staff;

    public static Connection connectToDB() throws SQLException
    {
        conn = DriverManager.getConnection(url,user,password);
        System.out.println("Connected to the database");
        return conn;
    }

    public static Connection showInventory() throws SQLException {
        String query = "SELECT * FROM inventory";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String shortDesc = rs.getString(3);
            String longDesc = rs.getString(4);
            String stock = rs.getString(5);
            String price = rs.getString(6);
        }
        return conn;
    }

    public static Connection insertIntoCustomer(Customer cus) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO customer (CustomerID,Name,DOB,EmailAddress,DateOfMembership,MembershipExpiryDate) VALUES ('"+cus.getCusID()+"','"+cus.getCusName()+"','"+cus.getDob().toString()+"','"+cus.getEmail()+"','"+cus.getDateOfMembership().toString()+"','"+cus.getDateOfMembershipExp().toString()+"')";
        String query2 = "Insert into address (CustomerID,Street,Town,Parish) VALUES ('"+cus.getCusID()+"','"+cus.getAddress().getStreet()+"','"+cus.getAddress().getTown()+"','"+cus.getAddress().getParish()+"')";
        statement.executeUpdate(query);
        statement.executeUpdate(query2);
        return conn;
    }

    public static  Connection insertIntoInventory(Products pro) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO inventory Values ('"+pro.getProdCode()+"','"+pro.getProdName()+"','"+pro.getProdShortDesc()+"','"+pro.getProdLongDesc()+"','"+pro.getProdStock()+"','"+pro.getUnitPrice()+"')";
        statement.executeUpdate(query);
        return conn;
    }

    public static ArrayList showInventory(Products pro) throws SQLException
    {
        ArrayList<Products> prodList = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM customer";
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
        return prodList;
    }

    public static Connection insertIntoStaff(Staff staff) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "INSERT INTO Staff Values ('"+staff.getStaffID()+"','"+staff.getName()+"','"+staff.getPosition()+"','"+staff.getDepartment()+"','"+staff.getDateOfBirth().toString()+"')";
        statement.executeUpdate(query);
        return conn;
    }

    public static ArrayList showStaff(Staff staff) throws SQLException
    {
        ArrayList<Staff> staffList = new ArrayList<>();
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
        return staffList;
    }



}
