package utcn.dobner.szabolcs.Model;

public class Client {
    private int clientID;
    private String name;
    private String email;
    private String phoneNumber;
    public Client() {
    }
    public Client(int ID,String name,String email,String phoneNumber)
    {
        this.clientID=ID;
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Student [id=" + clientID + ", name=" + name +  ", email=" + email + ", phone=" + phoneNumber
                + "]";
    }

}
