package utcn.dobner.szabolcs.Presentation;


import utcn.dobner.szabolcs.Business_Logic.OrderBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderController{
    private final OrderView orderView;
    private OrderBLL orderBLL;
    public OrderController(OrderView orderView) {
        this.orderView = orderView;
        orderView.addBackListener(new BackListener());
        orderView.addInsertListener(new InsertListener());
        orderView.addShowAllListener(new ShowAllListener());
        orderBLL=new OrderBLL(orderView);

    }
    public class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView view=new MenuView();
            MenuController controller=new MenuController(view);
            view.setVisible(true);
            orderView.setVisible(false);
        }
    }
    public class InsertListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderBLL.insertOrder();
        }
    }
    public class ShowAllListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderBLL.showAllOrders();
        }
    }


}
