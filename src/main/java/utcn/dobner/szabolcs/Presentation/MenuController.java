package utcn.dobner.szabolcs.Presentation;

import utcn.dobner.szabolcs.Model.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private MenuView view;
    public MenuController(MenuView view)
    {
        this.view=view;
        view.addClientListener(new ClientListener());
        view.addProductListener(new ProductListener());
        view.addOrderListener(new OrderListener());

    }
    public class ClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientView view=new ClientView();
            ClientController controller=new ClientController(view);
            view.setVisible(true);
        }
    }
    public class ProductListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductView view=new ProductView();
            ProductController controller=new ProductController(view);
            view.setVisible(true);
        }
    }
    public class OrderListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderView view=new OrderView();
            OrderController controller=new OrderController(view);
            view.setVisible(true);
        }
    }

}
