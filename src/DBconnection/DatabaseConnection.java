package DBconnection;
import entities.Customer;

import java.sql.*;


public class DatabaseConnection {
    private static Connection conn = null;
    private static String url = "jdbc:mysql://localhost:3306/jans";
    private static String user = "root";
    private static String password = "mersades123";

    private static Customer cus;

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
        String id = cus.getCusID();
        String name = cus.getCusName();
        String dob = cus.getDob().toString();
        String email = cus.getEmail();
        String phone = cus.getTelephone();
        String regDate = cus.getDateOfMembership().toString();
        String expDate = cus.getGetDateOfMembershipExp().toString();
        Statement statement = conn.createStatement();
        String query = "INSERT INTO customer (CustomerID,Name,DOB,EmailAddress,DateOfMembership,MembershipExpiryDate) VALUES ('"+id+"','"+name+"','"+dob+"','"+email+"','"+regDate+"','"+expDate+"')";
        String query2 = "Insert into address (CustomerID,Street,Town,Parish) VALUES ('"+id+"','"+cus.getAddress().getStreet()+"','"+cus.getAddress().getTown()+"','"+cus.getAddress().getParish()+"')";
        statement.executeUpdate(query);
        statement.executeUpdate(query2);
        return conn;
    }



}
