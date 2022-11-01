package gui;

import javax.swing.*;

public class Register {

    public Register()
    {
        JFrame rFrame = new JFrame();

        JPanel rPanel = new JPanel();
        rPanel.setSize(500, 500);


        JButton checkout = new JButton("Checkout");




        checkout.setBounds(50, 50, 100, 25);



        rPanel.add(checkout);

        rPanel.setLayout(null);



        rFrame.add(rPanel);

//        frame.add(registerButton);
//        frame.add(inventoryButton);

        rFrame.setLayout(null);
        rFrame.setVisible(true);
        rFrame.setSize(500, 500);
        rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
