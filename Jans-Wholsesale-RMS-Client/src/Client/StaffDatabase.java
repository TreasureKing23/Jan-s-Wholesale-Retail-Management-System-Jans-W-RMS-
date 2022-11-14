package Client;

import DBconnection.DatabaseConnection;
import Domain.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class StaffDatabase {

    public StaffDatabase(){

        DatabaseConnection dbconn = new DatabaseConnection();
        Enumeration enu = null;
        try {
            dbconn.connectToDB();
            Client client = new Client();
            client.sendAction("List Staff");
            Vector<Staff> cus = (Vector<Staff>) client.receiveObject();
            client.closeConnection();
            enu = cus.elements();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Enumeration finalEnu = enu;


        JDialog dialog = new JDialog();
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Staff Database");

        JButton viewStaff = new JButton("View Staff");
        JButton registerStaff = new JButton("Register Staff");
        JButton removeStaff = new JButton("Remove Staff");

        registerStaff.addActionListener(e -> {
            try {
                Register reg = new Register();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } //
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

        label.setBounds(200,40,300,20);
        viewStaff.setBounds(100,100,300,30);
        registerStaff.setBounds(100,150,300,30);
        removeStaff.setBounds(100,200,300,30);

        panel.setLayout(null);
        panel.setSize(500, 500);
        panel.add(label);
        panel.add(viewStaff);
        panel.add(registerStaff);
        panel.add(removeStaff);

        dialog.add(panel);
        dialog.setLayout(null);
        dialog.setModal(true);
        dialog.setSize(500, 500);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }
}
