package utcn.dobner.szabolcs.Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class OrderView extends JFrame {
    private JButton backButton;
    private JButton showAllButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JLabel idLabel;
    private JTextField idInput;

    private JLabel clientIdLabel;
    private JTextField clientIdInput;

    private JLabel productIdLabel;
    private JTextField productIdInput;

    private JLabel quantityLabel;
    private JTextField quantityInput;

    public OrderView()
    {
        this.setTitle("Order database");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLayout(null);

        idLabel = new JLabel("ID");
        idLabel.setBounds(100, 100, 100, 50);
        getContentPane().add(idLabel);

        idInput = new JTextField(40);
        idInput.setBounds(200, 100, 100, 25);
        getContentPane().add(idInput);

        clientIdLabel=new JLabel("Client id:");
        clientIdLabel.setBounds(100,200,100,50);
        getContentPane().add(clientIdLabel);

        clientIdInput= new JTextField(40);
        clientIdInput.setBounds(200,200,100,25);
        getContentPane().add(clientIdInput);

        productIdLabel=new JLabel("Product id:");
        productIdLabel.setBounds(100,300,100,50);
        getContentPane().add(productIdLabel);

        productIdInput= new JTextField(40);
        productIdInput.setBounds(200,300,200,25);
        getContentPane().add(productIdInput);

        quantityLabel=new JLabel("Quantity:");
        quantityLabel.setBounds(100,400,100,50);
        getContentPane().add(quantityLabel);

        quantityInput= new JTextField(40);
        quantityInput.setBounds(200,400,100,25);
        getContentPane().add(quantityInput);

        backButton = new JButton("Back");
        backButton.setBounds(100, 450, 75, 25);

        showAllButton = new JButton("Show all");
        showAllButton.setBounds(200, 450, 75, 25);

        insertButton = new JButton("Insert");
        insertButton.setBounds(300, 450, 75, 25);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 500, 75, 25);

        updateButton = new JButton("Update");
        updateButton.setBounds(250, 500, 75, 25);

        getContentPane().add(insertButton);
        getContentPane().add(showAllButton);
        getContentPane().add(backButton);
    }
    public void addUpdateListener(ActionListener mal) {
        updateButton.addActionListener(mal);
    }
    public void addDeleteListener(ActionListener mal) {
        deleteButton.addActionListener(mal);
    }
    public void addInsertListener(ActionListener mal) {
        insertButton.addActionListener(mal);
    }
    public void addShowAllListener(ActionListener mal) {
        showAllButton.addActionListener(mal);
    }
    public void addBackListener(ActionListener mal) {
        backButton.addActionListener(mal);
    }

    public int getID() {
        return Integer.parseInt(idInput.getText());
    }
    public String getClientID()
    {
        return clientIdInput.getText().toString();
    }
    public String getProductId()
    {
        return productIdInput.getText().toString();
    }
    public String getQuantity()
    {
        return quantityInput.getText().toString();
    }
}
