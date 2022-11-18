package DBconnection;


import Domain.Customer;
import format.Address;

import java.sql.SQLException;
import java.util.Vector;

public class Driver {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbconn = new DatabaseConnection();

        dbconn.connectToDB();
        Vector<String> sum = dbconn.salesReport("kool");
        System.out.println(sum);





    }
}
