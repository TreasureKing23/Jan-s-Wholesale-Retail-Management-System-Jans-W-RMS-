package gui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes

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


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

<<<<<<< Updated upstream
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Inventory inventory = new Inventory();
            }
=======
        inventoryButton.addActionListener(e -> {
            Inventory inventory = new Inventory();

>>>>>>> Stashed changes
        });

        exitButton.addActionListener(e -> System.exit(0));

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

    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu();
    }
}
