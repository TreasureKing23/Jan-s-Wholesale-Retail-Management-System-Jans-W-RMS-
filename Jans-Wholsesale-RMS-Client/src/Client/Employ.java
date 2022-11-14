package Client;

import DBconnection.DatabaseConnection;
import Domain.Staff;

import javax.swing.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Employ {

    public Employ() throws SQLException {
        DatabaseConnection dbconn = new DatabaseConnection();
        dbconn.connectToDB();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String departmentList[] = {"Management","Inventory","Accounting and Sales"};
        String positionList[] = {"Manager","Supervisor","Cashier","LineWorker","Cashier"};

        JDialog staffFrame = new JDialog();
        JPanel staffPanel = new JPanel();

        JLabel label = new JLabel("HIRE A NEW EMPLOYEE");
        JLabel firstNameLabel = new JLabel("Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel DobLabel = new JLabel("Date of Birth: ");
        JLabel departmentLabel = new JLabel("Department: ");
        JLabel positionLabel = new JLabel("Position: ");

        JTextField firstNameText = new JTextField();
        JTextField lastNameText = new JTextField();
        JFormattedTextField dobInput = new JFormattedTextField(df);
        JComboBox  departmentBox = new JComboBox(departmentList);
        JComboBox  positionBox = new JComboBox(positionList);

        JButton employButton = new JButton("Employ");


        label.setBounds(150,40,200,30);
        firstNameLabel.setBounds(20, 100,100,25);
        firstNameText.setBounds(120, 100,200,25);
        lastNameLabel.setBounds(20, 130,100,25);
        lastNameText.setBounds(120, 130,200,25);
        DobLabel.setBounds(20, 160,100,25);
        dobInput.setBounds(120, 160,200,25);
        departmentLabel.setBounds(20, 190,100,25);
        departmentBox.setBounds(120, 190,200,25);
        positionLabel.setBounds(20, 220,100,25);
        positionBox.setBounds(120, 220,200,25);
        employButton.setBounds(120, 250,200,25);


        employButton.addActionListener(e -> {
            if(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || dobInput.getText().isEmpty() || departmentBox.getSelectedItem().toString().isEmpty() || positionBox.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            }else{
                int a = (int)(Math.random()*(200-100+1)+100);
                String id = "S" + a;
                String name = firstNameText.getText() + " " + lastNameText.getText();
                String dob = dobInput.getText();
                String department = departmentBox.getSelectedItem().toString();
                String position = positionBox.getSelectedItem().toString();

                Staff staff = new Staff(name,id,position,department,dob);
                try {
                    dbconn.insertIntoStaff(staff);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        staffPanel.add(label);
        staffPanel.add(firstNameLabel);
        staffPanel.add(firstNameText);
        staffPanel.add(lastNameLabel);
        staffPanel.add(lastNameText);
        staffPanel.add(DobLabel);
        staffPanel.add(dobInput);
        staffPanel.add(departmentLabel);
        staffPanel.add(departmentBox);
        staffPanel.add(positionLabel);
        staffPanel.add(positionBox);
        staffPanel.add(employButton);

        staffPanel.setLayout(null);
        staffFrame.add(staffPanel);
        staffFrame.setSize(400,400);
        staffFrame.setVisible(true);
        staffFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public static void main(String[] args) throws SQLException {

        Employ employ = new Employ();
    }

}
