package Client;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustomerDetails {
    public CustomerDetails(){
        JDialog rFrame = new JDialog();
        JPanel rPanel = new JPanel();

        JLabel label = new JLabel("Register a new customer");
        JLabel idLabel = new JLabel("ID: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel DobLabel = new JLabel("Date of Birth: ");
        JLabel AddressLabel = new JLabel("Address: ");
        JLabel emailAddressLabel = new JLabel("Email: ");
        JLabel telLabel = new JLabel("Telephone: ");

        JTextField IdText = new JTextField();
        JTextField nameText = new JTextField();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JFormattedTextField dobInput = new JFormattedTextField(df);
        JTextArea addressText = new JTextArea();
        JTextField emailText = new JTextField();
        JTextField telText = new JTextField();

        JButton Update = new JButton("Update");
        JButton Delete = new JButton("Delete");

        label.setBounds(150,40,200,30);
        idLabel.setBounds(20,100,100,25);
        IdText.setBounds(120,100,200,25);
        nameLabel.setBounds(20, 130,100,25);
        nameText.setBounds(120, 130,200,25);
        DobLabel.setBounds(20, 160,100,25);
        dobInput.setBounds(120, 160,200,25);
        AddressLabel.setBounds(20, 190,100,25);
        addressText.setBounds(120, 190,200,60);
        emailAddressLabel.setBounds(20, 270,100,25);
        emailText.setBounds(120, 270,200,25);
        telLabel.setBounds(20, 300,100,25);
        telText.setBounds(120, 300,200,25);

        Update.setBounds(175, 400, 100, 25);
        Delete.setBounds(225, 400, 100, 25);

        IdText.setEditable(false);

        rPanel.setLayout(null);
        rPanel.setSize(500, 500);

        rPanel.add(Update);
        rPanel.add(Delete);
        rPanel.add(label);
        rPanel.add(idLabel);
        rPanel.add(IdText);
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
        rFrame.setResizable(false);
        rFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        rFrame.setVisible(true);
    }
}
