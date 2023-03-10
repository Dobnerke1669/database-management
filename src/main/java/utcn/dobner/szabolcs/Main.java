package utcn.dobner.szabolcs;

import utcn.dobner.szabolcs.Data_Access.AbstractDAO;
import utcn.dobner.szabolcs.Model.Client;
import utcn.dobner.szabolcs.Model.Order;
import utcn.dobner.szabolcs.Model.Product;
import utcn.dobner.szabolcs.Presentation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dobner Szabolcs-Imre
 * @Since 2022.04.20
 */
public class Main {
    public static void main(String[] args) {

        MenuView view=new MenuView();
        MenuController controller=new MenuController(view);
        view.setVisible(true);


    }
}
