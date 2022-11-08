package DBconnection;
import  java.sql.*;
public class testDB {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/jans";
        String user = "root";
        String password = "mersades123";

        try {

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
            String query = "SELECT * FROM customer";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)+ "  "+ rs.getString(2));
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