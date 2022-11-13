package gui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DBconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainMenu {

    private static Logger logger = LogManager.getLogger(MainMenu.class);
    public MainMenu()
    {

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setSize(500, 500);

        JLabel label = new JLabel("Main Menu");

        JButton customerDatabase = new JButton("Customer Database");
        JButton inventoryButton = new JButton("Inventory");
        JButton exitButton = new JButton("Exit");

        label.setBounds(220,20,200,25);
        customerDatabase.setBounds(100, 50, 300, 25);
        inventoryButton.setBounds(100, 100, 300, 25);
        exitButton.setBounds(100, 150, 300, 25);

        logger.info("Main Menu loaded");
        customerDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerDatabase cd = new CustomerDatabase();
                logger.info("Customer Database button pressed");
            }
        });

        inventoryButton.addActionListener(e -> {
            Inventory inventory = new Inventory();
            logger.info("Inventory button pressed");
        });

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(label);
        panel.add(customerDatabase);
        panel.add(inventoryButton);
        panel.add(exitButton);
        panel.setLayout(null);



        frame.add(panel);

//        frame.add(customerDatabase);
//        frame.add(inventoryButton);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setTitle("Jans Wholesale Pilot Program");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu();
    }
}
