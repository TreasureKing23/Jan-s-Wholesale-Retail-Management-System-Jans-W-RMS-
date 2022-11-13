package Client;


import DBconnection.*;
import java.sql.SQLException;

public class Checkout {

    public  Checkout() throws SQLException {
        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();

    }
}
