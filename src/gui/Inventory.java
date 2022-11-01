package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory {

    public Inventory()
    {

        JFrame iFrame = new JFrame();

        JPanel iPanel = new JPanel();

        JButton add = new JButton("Add");
        add.setBounds(50, 50, 100, 25);

        JButton goback = new JButton("Return");
        goback.setBounds(50, 100, 100, 25);

        goback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
            }
        });




        iPanel.setSize(500, 500);



        iPanel.add(add);
        iPanel.add(goback);

        iPanel.setLayout(null);

        iFrame.add(iPanel);

        iFrame.setLayout(null);
        iFrame.setVisible(true);
        iFrame.setSize(500, 500);
        iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
