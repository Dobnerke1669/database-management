package utcn.dobner.szabolcs.Presentation;


import utcn.dobner.szabolcs.Business_Logic.ClientBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController{
    private ClientView clientView;
    private ClientBLL clientBLL;

    /** Constructor */
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        clientView.addBackListener(new BackListener());
        clientView.addInsertListener(new InsertListener());
        clientView.addDeleteListener(new DeleteListener());
        clientView.addUpdateListener(new UpdateListener());
        clientView.addShowAllListener(new ShowAllListener());
        clientBLL=new ClientBLL(clientView);
    }


    public class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView view=new MenuView();
            MenuController controller=new MenuController(view);
            view.setVisible(true);
            clientView.setVisible(false);
        }
    }
    public class InsertListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.insertClient();
        }
    }
    public class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.deleteClient();
        }
    }
    public class UpdateListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.updateClient();
        }
    }
    public class ShowAllListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.showAllClients();
        }
    }

}



