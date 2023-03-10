package utcn.dobner.szabolcs.Validators;

import utcn.dobner.szabolcs.Model.Order;
import utcn.dobner.szabolcs.Model.Product;
/**
 * simple validator for order class
 */
public class OrderValidator {
    public String validate(Order order)
    {
        if (validateQuantity(order.getQuantity())==false)
            return "invalid";
        return "valid";
    }
    public boolean validateQuantity(int quantity)
    {
        if (quantity<0) return false;
        return true;
    }
}
