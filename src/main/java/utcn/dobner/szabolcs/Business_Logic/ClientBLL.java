package utcn.dobner.szabolcs.Business_Logic;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import utcn.dobner.szabolcs.Data_Access.AbstractDAO;
import utcn.dobner.szabolcs.Model.Client;
import utcn.dobner.szabolcs.Presentation.ClientView;
import utcn.dobner.szabolcs.Validators.ClientValidator;

import javax.swing.*;


public class ClientBLL {
    private AbstractDAO<Client> abstractDAO;
    private ClientView view;
    private ClientValidator validator;

    /**
     * @param view -GUI
     */
    public ClientBLL(ClientView view) {
        abstractDAO=new AbstractDAO<>(Client.class);
        validator=new ClientValidator();
        this.view=view;
    }

    /**
     * @param id -id of client
     * @return client if found
     */
    public Client findClientById(int id) {
        Client st = abstractDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * inserts a new client
     */
    public void insertClient()
    {
        Client client=new Client(view.getID(),view.getNames(),view.getEmail(),view.getPhone());
        if (validator.validate(client).equals("valid"))
            abstractDAO.insert(client);
        else System.out.println("Not done!");
    }

    /**
     * deletes a client
     */
    public void deleteClient()
    {
        int id=view.getID();
        abstractDAO.delete(id);
    }

    /**
     * updates a client
     */
    public void updateClient()
    {
        Client client=new Client(view.getID(),view.getNames(),view.getEmail(),view.getPhone());
        if (validator.validate(client).equals("valid"))
            abstractDAO.update(client);
        else
            System.out.println("Not done!");
    }

    /**
     * shows all clients
     */
    public void showAllClients()
    {
        ArrayList<Client> clients=abstractDAO.findAll();
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(600,50,500,500);
        JTable table=new JTable();
        table=abstractDAO.createTable(clients);
        table.setEnabled(true);
        table.setVisible(true);
        scrollPane.setViewportView(table);
        view.getContentPane().add(scrollPane);
    }
}




