package utcn.dobner.szabolcs.Validators;

import utcn.dobner.szabolcs.Model.Client;
import utcn.dobner.szabolcs.Model.Product;
/**
 * simple validator for product class
 */
public class ProductValidator {
    public String validate(Product product)
    {
        if (!validateName(product.getName()))
        {
            return "invalid";
        }
        if (!validatePrice(product.getPrice()))
        {
            return "invalid";
        }
        if (!validateStocks(product.getNrOfStocks()))
        {
            return "invalid";
        }
        return "valid";
    }

    public boolean validateName(String name)
    {
        if (name.isEmpty())
        {
            return false;
        }
        if (!name.matches("[A-Za-z]+"))
            return false;
        return true;
    }
    public boolean validatePrice(float price)
    {
        if (price<0)
            return false;
        return true;
    }
    public boolean validateStocks(int stocks)
    {
        if (stocks<0)
            return false;
        return true;
    }
}
