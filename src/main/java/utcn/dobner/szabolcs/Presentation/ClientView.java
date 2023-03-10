package utcn.dobner.szabolcs.Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JButton backButton;
    private JButton showAllButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JLabel idLabel;
    private JTextField idInput;

    private JLabel nameLabel;
    private JTextField nameInput;

    private JLabel emailLabel;
    private JTextField emailInput;

    private JLabel phoneLabel;
    private JTextField phoneInput;

    public ClientView() {
        this.setTitle("Client database");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLayout(null);

        idLabel = new JLabel("ID");
        idLabel.setBounds(100, 100, 100, 50);
        getContentPane().add(idLabel);

        idInput = new JTextField(40);
        idInput.setBounds(200, 100, 100, 25);
        getContentPane().add(idInput);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 200, 100, 50);
        getContentPane().add(nameLabel);

        nameInput = new JTextField(40);
        nameInput.setBounds(200, 200, 100, 25);
        getContentPane().add(nameInput);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 300, 100, 50);
        getContentPane().add(emailLabel);

        emailInput = new JTextField(40);
        emailInput.setBounds(200, 300, 200, 25);
        getContentPane().add(emailInput);

        phoneLabel = new JLabel("Phone nr:");
        phoneLabel.setBounds(100, 400, 100, 50);
        getContentPane().add(phoneLabel);

        phoneInput = new JTextField(40);
        phoneInput.setBounds(200, 400, 100, 25);
        getContentPane().add(phoneInput);

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

        getContentPane().add(updateButton);
        getContentPane().add(deleteButton);
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

    public String getNames() {
        return nameInput.getText().toString();
    }

    public String getEmail() {
        return emailInput.getText().toString();
    }

    public String getPhone() {
        return phoneInput.getText().toString();
    }
}
