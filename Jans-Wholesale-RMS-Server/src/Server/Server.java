package Server;

import Domain.*;
import format.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private static Connection dBConn = null;
    private Statement stmt;
    private ResultSet result = null;

    private static Logger logger = LogManager.getLogger(Server.class);

    public Server(){
        this.createConnection();
        this.waitForRequest();
    }

    private void createConnection() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void waitForRequest(){
        String action = "";
        getDatabaseConnection();
        String id = "";

        Customer customer = null;
        Invoice invoice = null;
        Products product = null;
        Staff staff = null;

        Vector<Customer> customerList = new Vector<>();
        Vector<Invoice> invoiceList = new Vector<>();
        Vector<Products> productList = new Vector<>();
        Vector<Staff> staffList = new Vector<>();

        try{
            while (true) {
                connectionSocket = serverSocket.accept();
                this.configureStreams();
                try {
                    action = (String) objIs.readObject();
                    switch (action) {
                        case "Add Customer" -> {
                            customer = (Customer) objIs.readObject();
                            insertIntoCustomer(customer);
                            objOs.writeObject(true);
                        }
                        case "Update Customer" -> {
                            id = (String) objIs.readObject();
                            customer = (Customer) objIs.readObject();
                            updateCustomer(id, customer);
                            objOs.writeObject(true);
                        }
                        case "Delete Customer" -> {
                            id = (String) objIs.readObject();
                            deleteCustomer(id);
                            objOs.writeObject(true);
                        }
                        case "Find Customer" -> {
                            id = (String) objIs.readObject();
                            customer = findCustomerById(id);
                            objOs.writeObject(customer);
                        }
                        case "List Customers" -> {
                            customerList = showCustomers();
                            objOs.writeObject(customerList);
                        }
                        case "Add Invoice" -> {
                            invoice = (Invoice) objIs.readObject();
                            insertIntoInvoices(invoice);
                            objOs.writeObject(true);
                        }
                        case "Find Invoice" -> {
                            id = (String) objIs.readObject();
                            invoice = findInvoiceById(id);
                            objOs.writeObject(invoice);
                        }
                        case "Delete Invoice" -> {
                            id = (String) objIs.readObject();
                            deleteInvoice(id);
                            objOs.writeObject(true);
                        }
                        case "List Invoices" -> {
                            invoiceList = showInvoices();
                            objOs.writeObject(invoiceList);
                        }
                        case "Add Product" -> {
                            product = (Products) objIs.readObject();
                            insertIntoInventory(product);
                            objOs.writeObject(true);
                        }
                        case "Update Product" -> {
                            id = (String) objIs.readObject();
                            product = (Products) objIs.readObject();
                            updateProduct(id, product);
                            objOs.writeObject(true);
                        }
                        case "Delete Product" -> {
                            id = (String) objIs.readObject();
                            deleteProduct(id);
                            objOs.writeObject(true);
                        }
                        case "Find Product" -> {
                            id = (String) objIs.readObject();
                            product = findProductById(id);
                            objOs.writeObject(product);
                        }
                        case "List Products" -> {
                            productList = showInventory();
                            objOs.writeObject(productList);
                        }
                        case "Add Staff" -> {
                            staff = (Staff) objIs.readObject();
                            insertIntoStaff(staff);
                            objOs.writeObject(true);
                        }
                        case "Update Staff" -> {
                            id = (String) objIs.readObject();
                            staff = (Staff) objIs.readObject();
                            updateStaff(id, staff);
                            objOs.writeObject(true);
                        }
                        case "Delete Staff" -> {
                            id = (String) objIs.readObject();
                            deleteStaff(id);
                            objOs.writeObject(true);
                        }
                        case "Find Staff" -> {
                            id = (String) objIs.readObject();
                            staff = findStaffById(id);
                            objOs.writeObject(staff);
                        }
                        case "List Staff" -> {
                            staffList = showStaff();
                            objOs.writeObject(staffList);
                        }
                    }
                } catch (ClassNotFoundException | ClassCastException ex){
                    ex.printStackTrace();
                }
                this.closeConnection();
            }
        } catch (EOFException ex){
            System.out.println("Client has terminated connections with the server");
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void configureStreams(){
        try {
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static Connection getDatabaseConnection() {
        if(dBConn == null){
            try {
                String url = "jdbc:mysql://localhost:3306/jans";
                dBConn = DriverManager.getConnection(url, "root", "admin");
                JOptionPane.showMessageDialog(null, "Database Connection Successful", "CONNECTION STATUS", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Database Connection Successful");
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Could not connect to database", "CONNECTION FAILURE", JOptionPane.ERROR_MESSAGE);
                logger.error("Could not connect to database");
            }
        }
        return dBConn;
    }

    private void closeConnection(){
        try {
            objOs.close();
            objIs.close();
            connectionSocket.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    // CUSTOMER ACTIONS
    public void insertIntoCustomer(Customer cus){
        String cusUrl = "INSERT INTO customer (CustomerID,Name,DOB,Telephone,EmailAddress,DateOfMembership,MembershipExpiryDate) " +
                "VALUES ('"+cus.getCusID()+"','"+cus.getCusName()+"','"+cus.getDob()+"','"+cus.getTelephone()+"','"+cus.getEmail()+"'," +
                "'"+cus.getDateOfMembership()+"','"+cus.getDateOfMembershipExp()+"')";
        String addressUrl = "Insert into address (CustomerID,Street,Town,Parish) VALUES ('"+cus.getCusID()+"','" +
                ""+cus.getAddress().getStreet()+"','"+cus.getAddress().getTown()+"','"+cus.getAddress().getParish()+"')";
        try {
            stmt = dBConn.createStatement();

            if(stmt.executeUpdate(cusUrl) == 1 && stmt.executeUpdate(addressUrl) == 1){
                objOs.writeObject(true);
                logger.info("Customer added to the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
            logger.error("Customer could not be added to the database");
        }
    }

    public void deleteCustomer(String id){
        String query = "DELETE FROM customer WHERE CustomerID = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(query) == 1)
                objOs.writeObject(true);
                logger.info("Customer deleted from the database");
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
            logger.error("Customer could not be deleted from the database");
        }
    }

    public Customer findCustomerById(String id){
        Customer customer = new Customer();
        String query = "SELECT * FROM customer WHERE CustomerID = '" + id + "'";
        String addQuery = "SELECT * FROM address WHERE CustomerID = '" + id + "'";
        try{
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()){
                customer.setCusID(result.getString("CustomerID"));
                customer.setCusName(result.getString("Name"));
                customer.setDob(result.getString("DOB"));
                customer.setTelephone(result.getString("Telephone"));
                customer.setEmail(result.getString("EmailAddress"));
                customer.setDateOfMembership(result.getString("DateOfMembership"));
                customer.setGetDateOfMembershipExp(result.getString("MembershipExpiryDate"));
            }
            result = stmt.executeQuery(addQuery);
            if (result.next()){
                customer.getAddress().setStreet(result.getString("Street"));
                customer.getAddress().setCity(result.getString("Town"));
                customer.getAddress().setParish(result.getString("Parish"));
            }
            logger.info("Customer found");
        } catch (SQLException ex){
            ex.printStackTrace();
            logger.error("Error Searching for Customer");
        }
        return customer;
    }

    public void updateCustomer(String id, Customer customer){
        String url = "UPDATE customer SET Name = '"+customer.getCusName()+"', DOB = '"+customer.getDob()+"', Telephone = '"+customer.getTelephone()+"'" +
                ", EmailAddress = '"+customer.getEmail()+"' WHERE CustomerID = '" + id + "'";
        String addUrl = "UPDATE address SET Street = '"+customer.getAddress().getStreet()+"', Town = '"+customer.getAddress().getTown()+"'," +
                " Parish = '"+customer.getAddress().getParish()+"' WHERE CustomerID = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(url) == 1 && stmt.executeUpdate(addUrl) == 1){
                objOs.writeObject(true);
                logger.info("Customer updated in the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public Vector<Customer> showCustomers(){
        Vector<Customer> customerList = new Vector<>();
        Customer customer = new Customer();
        String query = "SELECT * FROM customer";
        String query2 = "SELECT * FROM address";
        try{
            PreparedStatement ps = dBConn.prepareStatement(query);
            PreparedStatement ps2 = dBConn.prepareStatement(query2);
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            while (rs.next() && rs2.next()){
                customer.setCusID(rs.getString("CustomerID"));
                customer.setCusName(rs.getString("Name"));
                customer.setDob(rs.getString("DOB"));
                customer.setTelephone(rs.getString("Telephone"));
                customer.setEmail(rs.getString("EmailAddress"));
                customer.setDateOfMembership(rs.getString("DateOfMembership"));
                customer.setGetDateOfMembershipExp(rs.getString("MembershipExpiryDate"));

                customer.getAddress().setStreet(rs2.getString("Street"));
                customer.getAddress().setCity(rs2.getString("Town"));
                customer.getAddress().setParish(rs2.getString("Parish"));

                customerList.add(customer);
                customer = new Customer();
            }
            logger.info("Customer list sent to the client");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    // INVENTORY ACTIONS
    public void insertIntoInventory(Products pro){
        String url = "INSERT INTO inventory Values ('"+pro.getProdCode()+"','"+pro.getProdName()+"','"+pro.getProdShortDesc()+"','" +
                ""+pro.getProdLongDesc()+"','"+pro.getProdStock()+"','"+pro.getUnitPrice()+"')";
        try {
            stmt = dBConn.createStatement();

            if(stmt.executeUpdate(url) == 1){
                objOs.writeObject(true);
                logger.info("Product added to the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
            logger.error("Error adding product to the database");
        }
    }

    public void updateProduct(String id, Products product){
        String url = "UPDATE customer SET Name = '"+product.getProdName()+"', ShortDesc = '"+product.getProdShortDesc()+"', " +
                "LongDesc = '"+product.getProdLongDesc()+"'" + ", ItemsInStock = '"+product.getProdStock()+"', " +
                "UnitPrice = '"+product.getUnitPrice()+"' WHERE CustomerID = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(url) == 1){
                objOs.writeObject(true);
                logger.info("Product updated in the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
            logger.error("Error updating product in the database");
        }
    }

    public void deleteProduct(String id){
        String query = "DELETE FROM inventory WHERE ProductCode = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(query) == 1)
                objOs.writeObject(true);
                logger.info("Product deleted from the database");
            else
                objOs.writeObject(false);
                logger.info("Product not deleted from the database");
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public Products findProductById(String id){
        Products product = new Products();
        String query = "SELECT * FROM inventory WHERE ProductCode = '" + id  + "'";
        try{
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()){
                product.setProdCode(result.getString("ProductCode"));
                product.setProdName(result.getString("Name"));
                product.setProdShortDesc(result.getString("ShortDesc"));
                product.setProdLongDesc(result.getString("LongDesc"));
                product.setProdStock(result.getInt("ItemsInStock"));
                product.setUnitPrice(result.getFloat("UnitPrice"));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return product;
    }

    public Vector<Products> showInventory(){
        Vector<Products> inventory = new Vector<>();
        Products product = new Products();
        String query = "SELECT * FROM inventory";
        try{
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()){
                product.setProdCode(result.getString("ProductCode"));
                product.setProdName(result.getString("Name"));
                product.setProdShortDesc(result.getString("ShortDesc"));
                product.setProdLongDesc(result.getString("LongDesc"));
                product.setProdStock(result.getInt("ItemsInStock"));
                product.setUnitPrice(result.getFloat("UnitPrice"));

                inventory.add(product);
                product = new Products();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return inventory;
    }

    // STAFF ACTIONS
    public void insertIntoStaff(Staff staff){
        String url = "INSERT INTO Staff Values ('"+staff.getStaffID()+"','"+staff.getName()+"','"+staff.getPosition()+"','" +
                ""+staff.getDepartment()+"','"+staff.getDateOfBirth().toString()+"')";
        try {
            stmt = dBConn.createStatement();

            if(stmt.executeUpdate(url) == 1){
                objOs.writeObject(true);
                logger.info("Staff added to the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateStaff(String id, Staff staff){
        String url = "UPDATE customer SET Name = '"+staff.getName()+"', Position = '"+staff.getPosition()+"', " +
                "Department = '"+staff.getDepartment()+"'" + ", DOB = '"+staff.getDateOfBirth()+
                "' WHERE CustomerID = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(url) == 1){
                objOs.writeObject(true);
                logger.info("Staff updated in the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteStaff(String id){
        String query = "DELETE FROM staff WHERE StaffID = '" + id + "'";
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(query) == 1)
                objOs.writeObject(true);
                logger.info("Staff deleted from the database");
            else
                objOs.writeObject(false);
                logger.info("Staff not deleted from the database");
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public Staff findStaffById(String id){
        Staff staff = new Staff();
        String query = "SELECT * FROM staff WHERE StaffID = '" + id + "'";
        try{
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()){
                staff.setStaffID(result.getString("StaffID"));
                staff.setName(result.getString("Name"));
                staff.setPosition(result.getString("Position"));
                staff.setDepartment(result.getString("Department"));
                staff.setDateOfBirth(result.getString("DOB"));

            }
            logger.info("Staff found in the database");
        } catch (SQLException ex){
            ex.printStackTrace();
            logger.error("Staff not found in the database");
        }
        return staff;
    }

    public Vector<Staff> showStaff() {
        Vector<Staff> staffList = new Vector<>();
        Staff staff = new Staff();
        String query = "SELECT * FROM staff";
        try {
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                staff.setStaffID(result.getString("StaffID"));
                staff.setName(result.getString("Name"));
                staff.setPosition(result.getString("Position"));
                staff.setDepartment(result.getString("Department"));
                staff.setDateOfBirth(result.getString("DOB"));
                staffList.add(staff);
                staff = new Staff();
            }
            logger.info("Staff list retrieved from the database");
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Error retrieving staff list from the database");
        }
        return staffList;
    }

    // INVOICE ACTIONS
    public void insertIntoInvoices(Invoice invoice){
        String url = "INSERT INTO checkout Values ('" + invoice.getInvoiceNo() + "','" + invoice.getBillingDate() + "','" +
                invoice.getItem() + "','" + invoice.getQuantity() + "','" + invoice.getCashierName() + "','" + invoice.getCustomerName() + "')";
        try {
            stmt = dBConn.createStatement();

            if(stmt.executeUpdate(url) == 1){
                objOs.writeObject(true);
                logger.info("Invoice added to the database");
            }
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public Invoice findInvoiceById(String id){
        Invoice invoice = new Invoice();
        String query = "SELECT * FROM checkout WHERE InvoiceNumber = " + id;
        try{
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()){
                invoice.setInvoiceNo(result.getInt("InvoiceNumber"));
                invoice.setBillingDate(result.getString("BillingDate"));
                invoice.setItem(result.getString("Item"));
                invoice.setQuantity(result.getInt("Quantity"));
                invoice.setCashierName(result.getString("Cashier"));
                invoice.setCustomerName(result.getString("Customer"));
                logger.info("Invoice found in the database");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return invoice;
    }

    public void deleteInvoice(String id){
        String query = "DELETE FROM checkout WHERE StaffID = " + id ;
        try {
            stmt = dBConn.createStatement();
            if(stmt.executeUpdate(query) == 1)
                objOs.writeObject(true);
            logger.info("Invoice deleted from the database");
            else
                objOs.writeObject(false);
        } catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public Vector<Invoice> showInvoices() {
        Vector<Invoice> invoiceList = new Vector<>();
        Invoice invoice = new Invoice();
        String query = "SELECT * FROM checkout";
        try {
            stmt = dBConn.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                invoice.setInvoiceNo(result.getInt("InvoiceNumber"));
                invoice.setBillingDate(result.getString("BillingDate"));
                invoice.setItem(result.getString("Item"));
                invoice.setQuantity(result.getInt("Quantity"));
                invoice.setCashierName(result.getString("Cashier"));
                invoice.setCustomerName(result.getString("Customer"));

                invoiceList.add(invoice);
                invoice = new Invoice();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invoiceList;
    }

}
