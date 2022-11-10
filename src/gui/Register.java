package gui;
import entities.*;
import DBconnection.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Register {

    public Register() throws SQLException {
        Customer customer = new Customer();
        DatabaseConnection dbconn = new DatabaseConnection();
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JDialog rFrame = new JDialog();
        JPanel rPanel = new JPanel();

        JLabel label = new JLabel("Register a new customer");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel DobLabel = new JLabel("Date of Birth: ");
        JLabel AddressLabel = new JLabel("Address: ");
        JLabel emailAddressLabel = new JLabel("Email: ");
        JLabel telLabel = new JLabel("Telephone: ");

        JTextField nameText = new JTextField();
        JFormattedTextField dobInput = new JFormattedTextField(df);
        JTextArea addressText = new JTextArea();
        JTextField emailText = new JTextField();
        JTextField telText = new JTextField();

        JButton register = new JButton("Register");

        label.setBounds(150,40,200,30);
        nameLabel.setBounds(20, 100,100,25);
        nameText.setBounds(120, 100,200,25);
        DobLabel.setBounds(20, 130,100,25);
        dobInput.setBounds(120, 130,200,25);
        AddressLabel.setBounds(20, 160,100,25);
        addressText.setBounds(120, 160,200,60);
        emailAddressLabel.setBounds(20, 240,100,25);
        emailText.setBounds(120, 240,200,25);
        telLabel.setBounds(20, 270,100,25);
        telText.setBounds(120, 270,200,25);

        register.setBounds(200, 400, 100, 25);

        register.addActionListener(e -> {
            String name = nameText.getText();
            String dob = dobInput.getText();
            String address = addressText.getText();
            String email = emailText.getText();
            String tel = telText.getText();
            String dateOfMembership = dateTime.format(LocalDateTime.now());
            String dateOfMembershipExp = dateTime.format(LocalDateTime.now().plusYears(1));
           // customer = new Customer(name, dob, address, tel, email, dateOfMembership, dateOfMembershipExp);
        });

        rPanel.setLayout(null);
        rPanel.setSize(500, 500);

        rPanel.add(register);
        rPanel.add(label);
        rPanel.add(nameLabel);
        rPanel.add(nameText);
        rPanel.add(DobLabel);
        rPanel.add(dobInput);
        rPanel.add(AddressLabel);
        rPanel.add(addressText);
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
