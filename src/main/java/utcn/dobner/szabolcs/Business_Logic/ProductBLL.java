package utcn.dobner.szabolcs.Business_Logic;

import utcn.dobner.szabolcs.Data_Access.AbstractDAO;
import utcn.dobner.szabolcs.Model.Product;
import utcn.dobner.szabolcs.Presentation.ProductView;
import utcn.dobner.szabolcs.Validators.ProductValidator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductView productView;
    private AbstractDAO<Product> abstractDAO;
    private ProductValidator validator;

    public ProductBLL(ProductView productView) {
        this.productView=productView;
        abstractDAO=new AbstractDAO<>(Product.class);
        validator=new ProductValidator();
    }

    /**
     * @param id-products id
     * @return product
     */
    public Product findProductById(int id) {
        Product st = abstractDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * insert
     */
    public void insertProduct()
    {
        Product product=new Product(productView.getID(),productView.getNames(),Integer.parseInt(productView.getPrice()),Integer.parseInt(productView.getNrStocks()));
        if(validator.validate(product).equals("valid"))
            abstractDAO.insert(product);
        else System.out.println("Not done!");
    }

    /**
     * delete
     */
    public void deleteProduct()
    {
        int id=productView.getID();
        abstractDAO.delete(id);
    }

    /**
     * update
     */
    public void updateProduct()
    {
        Product product=new Product(1,productView.getNames(),Integer.parseInt(productView.getPrice()),Integer.parseInt(productView.getNrStocks()));
        if(validator.validate(product).equals("valid"))
            abstractDAO.update(product);
        else System.out.println("Not done!");
    }

    /**
     * shows all products
     */
    public void showAllProducts()
    {
        ArrayList<Product> products=abstractDAO.findAll();
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(600,50,500,500);
        JTable table=new JTable();
        table=abstractDAO.createTable(products);
        table.setEnabled(true);
        table.setVisible(true);
        scrollPane.setViewportView(table);
        productView.getContentPane().add(scrollPane);
    }
}
