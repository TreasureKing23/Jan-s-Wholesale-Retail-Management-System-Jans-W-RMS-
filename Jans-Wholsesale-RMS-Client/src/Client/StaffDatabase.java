package Client;

import DBconnection.DatabaseConnection;
import Domain.Customer;
import Domain.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

public class StaffDatabase {

    public StaffDatabase(){
        Enumeration enu = null;
        Client client = new Client();
        client.sendAction("List Staff");
        Vector<Staff> cus = (Vector<Staff>) client.receiveObject();
        client.closeConnection();
        enu = cus.elements();
        Enumeration finalEnu = enu;


        JDialog dialog = new JDialog();
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Staff Database");

        JButton viewStaff = new JButton("View Staff");
        JButton registerStaff = new JButton("Register Staff");
        JButton infoStaff = new JButton("Staff Details");

        registerStaff.addActionListener(e -> {
            new Employ();
        });

        viewStaff.addActionListener(e -> {
            JDialog viewFrame = new JDialog();
            String[] columnNames ={"STAFF ID","NAME","POSITION","DEPARTMENT","DOB"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while(finalEnu.hasMoreElements()){
                Vector<String> row = new Vector<String>();
                Staff staff = (Staff) finalEnu.nextElement();
                row.add(staff.getStaffID());
                row.add(staff.getName());
                row.add(staff.getPosition());
                row.add(staff.getDepartment());
                row.add(staff.getDateOfBirth());
                model.addRow(row);
            }
            JTable table = new JTable(model);
            table.setBounds(50, 50, 400, 400);
            JScrollPane sp=new JScrollPane(table);
            viewFrame.add(sp);
            viewFrame.setModal(true);
            viewFrame.setResizable(false);
            viewFrame.setSize(1000, 1000);
            viewFrame.setVisible(true);
            viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });

        infoStaff.addActionListener(e -> {
            String searchID = JOptionPane.showInputDialog(null, "Enter Staff ID", "ENTER INPUT", JOptionPane.QUESTION_MESSAGE);
            if(searchID != null){
                JDialog info = new JDialog();
                JPanel sPanel = new JPanel();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String departmentList[] = {"Management","Inventory","Accounting and Sales"};
                String positionList[] = {"Manager","Supervisor","Cashier","LineWorker","Cashier"};

                JLabel sLabel = new JLabel("EMPLOYEE DETAILS");
                JLabel idLabel = new JLabel("ID: ");
                JLabel firstNameLabel = new JLabel("First Name: ");
                JLabel lastNameLabel = new JLabel("Last Name: ");
                JLabel DobLabel = new JLabel("Date of Birth: ");
                JLabel departmentLabel = new JLabel("Department: ");
                JLabel positionLabel = new JLabel("Position: ");

                JTextField idText = new JTextField();
                JTextField firstNameText = new JTextField();
                JTextField lastNameText = new JTextField();
                JFormattedTextField dobInput = new JFormattedTextField(df);
                JComboBox  departmentBox = new JComboBox(departmentList);
                JComboBox  positionBox = new JComboBox(positionList);

                Client sClient = new Client();
                sClient.sendAction("Find Customer");
                sClient.sendID(searchID);
                Staff  staff = (Staff) sClient.receiveObject();
                sClient.closeConnection();

                idText.setText(staff.getStaffID());
                firstNameText.setText(staff.getName());
                dobInput.setText(staff.getDateOfBirth());
                departmentBox.setSelectedItem(staff.getDepartment());
                positionBox.setSelectedItem(staff.getPosition());

                JButton Update = new JButton("Update");
                JButton Delete = new JButton("Delete");
                idText.setEditable(false);

                Update.addActionListener(f -> {
                    String id = idText.getText();
                    String name = firstNameText.getText() + " " + lastNameText.getText();
                    String dob = dobInput.getText();
                    String department = departmentBox.getSelectedItem().toString();
                    String position = positionBox.getSelectedItem().toString();

                    Staff staff1 = new Staff(name,id,position,department,dob);

                    Client client1 = new Client();
                    client1.sendAction("Update Staff");
                    client1.sendID(searchID);
                    client1.sendObject(staff1);
                    client1.receiveResponse();
                    client1.closeConnection();
                    info.dispose();
                });  
                
                Delete.addActionListener(f -> {
                    Client client1 = new Client();
                    client1.sendAction("Delete Staff");
                    client1.sendID(searchID);
                    client1.receiveResponse();
                    client1.closeConnection();
                    info.dispose();
                });

                sLabel.setBounds(150,40,200,30);
                idLabel.setBounds(20,100,100,25);
                idText.setBounds(120,100,100,25);
                firstNameLabel.setBounds(20, 130,100,25);
                firstNameText.setBounds(120, 130,200,25);
                lastNameLabel.setBounds(20, 160,100,25);
                lastNameText.setBounds(120, 160,200,25);
                DobLabel.setBounds(20, 190,100,25);
                dobInput.setBounds(120, 190,200,25);
                departmentLabel.setBounds(20, 220,100,25);
                departmentBox.setBounds(120, 220,200,25);
                positionLabel.setBounds(20, 250,100,25);
                positionBox.setBounds(120, 250,200,25);
                Update.setBounds(75, 400, 100, 25);
                Delete.setBounds(225, 400, 100, 25);

                sPanel.add(sLabel);
                sPanel.add(firstNameLabel);
                sPanel.add(firstNameText);
                sPanel.add(lastNameLabel);
                sPanel.add(lastNameText);
                sPanel.add(DobLabel);
                sPanel.add(dobInput);
                sPanel.add(departmentLabel);
                sPanel.add(departmentBox);
                sPanel.add(positionLabel);
                sPanel.add(positionBox);
                sPanel.add(Update);
                sPanel.add(Delete);

                sPanel.setLayout(null);
                info.add(sPanel);
                info.setModal(true);
                info.setSize(400,400);
                info.setVisible(true);
                info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        label.setBounds(200,40,300,20);
        viewStaff.setBounds(100,100,300,30);
        registerStaff.setBounds(100,150,300,30);
        infoStaff.setBounds(100,200,300,30);

        panel.setLayout(null);
        panel.setSize(500, 500);
        panel.add(label);
        panel.add(viewStaff);
        panel.add(registerStaff);
        panel.add(infoStaff);

        dialog.add(panel);
        dialog.setLayout(null);
        dialog.setModal(true);
        dialog.setSize(500, 500);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }
}
