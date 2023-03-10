package utcn.dobner.szabolcs.Model;

public class Order {
    private int ID;
    private int clientID;
    private int productID;
    private int quantity;
    public Order()
    {

    }
    public Order(int ID,int clientID, int productID, int quantity)
    {
        this.ID=ID;
        this.clientID=clientID;
        this.quantity=quantity;
        this.productID=productID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
