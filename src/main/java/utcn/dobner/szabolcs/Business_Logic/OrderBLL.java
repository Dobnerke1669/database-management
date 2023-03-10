package utcn.dobner.szabolcs.Business_Logic;


import utcn.dobner.szabolcs.Data_Access.AbstractDAO;
import utcn.dobner.szabolcs.Model.Order;
import utcn.dobner.szabolcs.Model.Product;
import utcn.dobner.szabolcs.Presentation.OrderView;
import utcn.dobner.szabolcs.Validators.OrderValidator;


import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class OrderBLL {
    private AbstractDAO<Order> abstractDAO;
    private AbstractDAO<Product> productAbstractDAO;
    private OrderView orderView;
    private OrderValidator validator;
    public OrderBLL(OrderView orderView) {

        abstractDAO=new AbstractDAO<>(Order.class);
        productAbstractDAO=new AbstractDAO<>(Product.class);
        this.orderView=orderView;
        validator=new OrderValidator();
    }

    /**
     * @param id- orders id
     * @return order
     */
    public Order findOrderById(int id) {
        Order st = abstractDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public void writeBill(Order order,Product product)
    {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("bills.txt", true));
            output.append("Order id:"+order.getID());
            output.newLine();
            output.append("Product name:"+product.getName());
            output.newLine();
            output.append("Quantity:"+order.getQuantity());
            output.newLine();
            output.append("Price:"+order.getQuantity()+product.getPrice());
            output.newLine();
            output.newLine();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * insert
     */
    public void insertOrder()
    {
        Order order=new Order(orderView.getID(),Integer.parseInt(orderView.getClientID()),Integer.parseInt(orderView.getProductId()),Integer.parseInt(orderView.getQuantity()));
        if(validator.validate(order).equals("valid"))
        {
            int id=Integer.parseInt(orderView.getProductId());
            Product product=productAbstractDAO.findById(id);
            if (product.getNrOfStocks()>Integer.parseInt(orderView.getQuantity()))
            {
                product.setNrOfStocks(product.getNrOfStocks()-order.getQuantity());
                productAbstractDAO.update(product);
                abstractDAO.insert(order);
                writeBill(order,product);
            }

            else
            {
                System.out.println("NOT ENOUGH STOCKS");
            }
        }

        else System.out.println("Not done!");
    }

    /**
     * show all orders
     */
    public void showAllOrders()
    {
        ArrayList<Order> orders=abstractDAO.findAll();
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(600,50,500,500);
        JTable table=new JTable();
        table=abstractDAO.createTable(orders);
        table.setEnabled(true);
        table.setVisible(true);
        scrollPane.setViewportView(table);
        orderView.getContentPane().add(scrollPane);
    }
}
