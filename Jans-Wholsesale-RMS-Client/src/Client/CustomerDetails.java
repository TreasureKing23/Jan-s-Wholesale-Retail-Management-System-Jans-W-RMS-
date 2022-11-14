package Client;

import Domain.Customer;
import format.Address;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class CustomerDetails {
    public CustomerDetails(String id){
        JDialog rFrame = new JDialog();
        JPanel rPanel = new JPanel();
        String parishList[] = {"Kingston","St. Andrew","St. Thomas","Portland","St. Mary","St. Ann","Trelawny","St. James","Hanover","Westmoreland","St. Elizabeth","Manchester","Clarendon"};

        JLabel label = new JLabel("Register a new customer");
        JLabel idLabel = new JLabel("ID: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel DobLabel = new JLabel("Date of Birth: ");
        JLabel streetLabel = new JLabel("Street: ");
        JLabel townLabel = new JLabel("Town/City: ");
        JLabel parishLabel = new JLabel("Parish: ");
        JLabel emailAddressLabel = new JLabel("Email: ");
        JLabel telLabel = new JLabel("Telephone: ");

        JTextField IdText = new JTextField();
        JTextField nameText = new JTextField();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JFormattedTextField dobInput = new JFormattedTextField(df);
        JTextField streetText = new JTextField();
        JTextField townText = new JTextField();
        JComboBox  parishBox = new JComboBox(parishList);
        JTextField emailText = new JTextField();
        JTextField telText = new JTextField();

        JButton Update = new JButton("Update");
        JButton Delete = new JButton("Delete");

        Client client = new Client();
        client.sendAction("Find Customer");
        client.sendID(id);
        Customer customer = (Customer) client.receiveObject();
        client.closeConnection();

        IdText.setText(customer.getCusID());
        nameText.setText(customer.getCusName());
        dobInput.setText(customer.getDob());
        streetText.setText(customer.getAddress().getStreet());
        townText.setText(customer.getAddress().getTown());
        parishBox.setSelectedItem(customer.getAddress().getParish());
        emailText.setText(customer.getEmail());
        telText.setText(customer.getTelephone());


        label.setBounds(150,40,200,30);
        idLabel.setBounds(20,100,100,25);
        IdText.setBounds(120,100,200,25);
        nameLabel.setBounds(20, 130,100,25);
        nameText.setBounds(120, 130,200,25);
        DobLabel.setBounds(20, 160,100,25);
        dobInput.setBounds(120, 160,200,25);
        streetLabel.setBounds(20, 190,100,25);
        streetText.setBounds(120, 190,200,25);
        townLabel.setBounds(20, 220,100,25);
        townText.setBounds(120, 220,200,25);
        parishLabel.setBounds(20, 250,100,25);
        parishBox.setBounds(120, 250,200,25);
        emailAddressLabel.setBounds(20, 280,100,25);
        emailText.setBounds(120, 280,200,25);
        telLabel.setBounds(20, 310,100,25);
        telText.setBounds(120, 310,200,25);

        Update.setBounds(100, 400, 100, 25);
        Delete.setBounds(300, 400, 100, 25);

        IdText.setEditable(false);


        Delete.addActionListener(e ->{
            Client client1 = new Client();
            client1.sendAction("Delete Customer");
            client1.sendID(id);
            client1.receiveResponse();
            client1.closeConnection();
            rFrame.dispose();
        });

        Update.addActionListener(e ->{
            String name = nameText.getText();
            String dob = dobInput.getText();
            String street = streetText.getText();
            String town = townText.getText();
            String parish = parishBox.getSelectedItem().toString();
            String email = emailText.getText();
            String tel = telText.getText();
            Address address = new Address(street, town, parish);
            Customer cus = new Customer(id,name, dob, address, tel, email, null, null);


            Client client1 = new Client();
            client1.sendAction("Update Customer");
            client1.sendID(id);
            client1.sendObject(cus);
            client1.receiveResponse();
            client1.closeConnection();
            rFrame.dispose();
        });

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
        rPanel.add(streetLabel);
        rPanel.add(streetText);
        rPanel.add(townLabel);
        rPanel.add(townText);
        rPanel.add(parishLabel);
        rPanel.add(parishBox);
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
