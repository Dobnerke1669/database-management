package utcn.dobner.szabolcs.Validators;

import utcn.dobner.szabolcs.Model.Client;

/**
 * simple validator for client class
 */
public class ClientValidator {


    public String validate(Client client)
    {
        if (validateName(client.getName())==false)
        {
            return "invalid";
        }
        if (validateEmail(client.getEmail())==false)
        {
            return "invalid";
        }
        if (validatePhone(client.getPhoneNumber())==false)
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
        if (name.matches("[A-Za-z]+")==false)
            return false;
        return true;
    }
    public boolean validateEmail(String email)
    {
        if (email.isEmpty())
            return false;
        if (email.contains("@")==false)
            return false;
        if (email.contains(".")==false)
            return false;
        return true;
    }
    public boolean validatePhone(String phone)
    {
        if (phone.length()!=10)
            return false;
        if (phone.matches("[0-9]+")==false)
            return false;
        return true;
    }
}
