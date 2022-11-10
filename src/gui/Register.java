package gui;
import entities.*;
import DBconnection.*;
import format.Address;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import static DBconnection.DatabaseConnection.insertIntoCustomer;

public class Register {

    public Register() throws SQLException {

        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();
  //      DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JDialog rFrame = new JDialog();
        JPanel rPanel = new JPanel();

        JLabel label = new JLabel("Register a new customer");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel DobLabel = new JLabel("Date of Birth: ");
        JLabel streetLabel = new JLabel("Street: ");
        JLabel townLabel = new JLabel("Town/City: ");
        JLabel parishLabel = new JLabel("Parish: ");
        JLabel emailAddressLabel = new JLabel("Email: ");
        JLabel telLabel = new JLabel("Telephone: ");

        JTextField nameText = new JTextField();
        JFormattedTextField dobInput = new JFormattedTextField(df);
        JTextField streetText = new JTextField();
        JTextField townText = new JTextField();
        JTextField parishText = new JTextField();
        JTextField emailText = new JTextField();
        JTextField telText = new JTextField();

        JButton register = new JButton("Register");

        label.setBounds(150,40,200,30);
        nameLabel.setBounds(20, 100,100,25);
        nameText.setBounds(120, 100,200,25);
        DobLabel.setBounds(20, 130,100,25);
        dobInput.setBounds(120, 130,200,25);
        streetLabel.setBounds(20, 160,100,25);
        streetText.setBounds(120, 160,200,25);
        townLabel.setBounds(20, 190,100,25);
        townText.setBounds(120, 190,200,25);
        parishLabel.setBounds(20, 220,100,25);
        parishText.setBounds(120, 220,200,25);
        emailAddressLabel.setBounds(20, 250,100,25);
        emailText.setBounds(120, 250,200,25);
        telLabel.setBounds(20, 280,100,25);
        telText.setBounds(120, 280,200,25);

        register.setBounds(200, 400, 100, 25);

        register.addActionListener(e -> {
            int a = (int)(Math.random()*(200-100+1)+100);
            String id = "C" + a;
            String name = nameText.getText();
            String dob = dobInput.getText();
            String street = streetText.getText();
            String town = townText.getText();
            String parish = parishText.getText();
            String email = emailText.getText();
            String tel = telText.getText();
            String dateOfMembership = df.format(LocalDateTime.now());
            String dateOfMembershipExp = df.format(LocalDateTime.now().plusYears(1));
            Address address = new Address(street, town, parish);
            Customer customer = new Customer(id,name, dob, address, tel, email, dateOfMembership, dateOfMembershipExp);
            try {
                insertIntoCustomer(customer);
                JOptionPane.showMessageDialog(null, "Customer inserted", "Success",JOptionPane.INFORMATION_MESSAGE);
                rFrame.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



        });

        rPanel.setLayout(null);
        rPanel.setSize(500, 500);

        rPanel.add(register);
        rPanel.add(label);
        rPanel.add(nameLabel);
        rPanel.add(nameText);
        rPanel.add(DobLabel);
        rPanel.add(dobInput);
        rPanel.add(streetLabel);
        rPanel.add(streetText);
        rPanel.add(townLabel);
        rPanel.add(townText);
        rPanel.add(parishLabel);
        rPanel.add(parishText);
        rPanel.add(emailAddressLabel);
        rPanel.add(emailText);
        rPanel.add(telLabel);
        rPanel.add(telText);


        rFrame.add(rPanel);
        rFrame.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        rFrame.setLayout(null);
        rFrame.setSize(500, 500);
        rFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        rFrame.setVisible(true);
    }

}
