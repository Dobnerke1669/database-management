package utcn.dobner.szabolcs.Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    public MenuView()
    {
        this.setTitle("Menu window");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,450);
        this.setLayout(null);
        clientButton=new JButton("Client database");
        clientButton.setBounds(100,50,100,50);
        productButton=new JButton("Product database");
        productButton.setBounds(100,150,100,50);
        orderButton=new JButton("Order database");
        orderButton.setBounds(100,250,100,50);
        getContentPane().add(clientButton);
        getContentPane().add(productButton);
        getContentPane().add(orderButton);

    }
    public void addClientListener(ActionListener mal) {
        clientButton.addActionListener(mal);
    }
    public void addOrderListener(ActionListener mal) {
        orderButton.addActionListener(mal);
    }
    public void addProductListener(ActionListener mal) {
        productButton.addActionListener(mal);
    }
}
