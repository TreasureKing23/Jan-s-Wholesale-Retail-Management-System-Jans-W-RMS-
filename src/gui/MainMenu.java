package gui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenu {

    public MainMenu()
    {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setSize(500, 500);


        JButton registerButton = new JButton("Register");
        JButton inventoryButton = new JButton("Inventory");
        JButton exitButton = new JButton("Exit");



        registerButton.setBounds(50, 50, 100, 25);

        inventoryButton.setBounds(50, 100, 100, 25);

        exitButton.setBounds(50, 150, 100, 25);

        panel.add(registerButton);
        panel.add(inventoryButton);
        panel.add(exitButton);
        panel.setLayout(null);



        frame.add(panel);

//        frame.add(registerButton);
//        frame.add(inventoryButton);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
