package DBconnection;


import entities.Customer;
import format.Address;

import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbconn = new DatabaseConnection();

        Address address = new Address("No. 1", "Colombo", "SriLanka");
        Customer customer = new Customer("C001", "Janith", "date", address, "0712345678", "jdoe@gmail.com", "date", "date");

        dbconn.connectToDB();
        dbconn.insertIntoCustomer(customer);





    }
}
