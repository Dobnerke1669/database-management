package utcn.dobner.szabolcs.Model;

public class Product {
    private int ID;
    private String name;
    private float price;
    private int nrOfStocks;
    public Product()
    {

    }

    public Product(int ID,String name, float price, int nrOfStocks)
    {
        this.ID=ID;
        this.name=name;
        this.price=price;
        this.nrOfStocks=nrOfStocks;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNrOfStocks() {
        return nrOfStocks;
    }

    public void setNrOfStocks(int nrOfStocks) {
        this.nrOfStocks = nrOfStocks;
    }
}
