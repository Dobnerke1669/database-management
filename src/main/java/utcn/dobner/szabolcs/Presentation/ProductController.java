package utcn.dobner.szabolcs.Presentation;

import utcn.dobner.szabolcs.Business_Logic.ProductBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController{

    private final ProductView productView;
    private ProductBLL productBLL;

    public ProductController(ProductView productView) {
        this.productView = productView;
        productBLL=new ProductBLL(productView);
        productView.addBackListener(new BackListener());
        productView.addInsertListener(new InsertListener());
        productView.addDeleteListener(new DeleteListener());
        productView.addUpdateListener(new UpdateListener());
        productView.addShowAllListener(new ShowAllListener());

    }

    public class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView view=new MenuView();
            MenuController controller=new MenuController(view);
            view.setVisible(true);
            productView.setVisible(false);
        }
    }
    public class InsertListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.insertProduct();
        }
    }
    public class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.deleteProduct();
        }
    }
    public class UpdateListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.updateProduct();
        }
    }
    public class ShowAllListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.showAllProducts();
        }
    }
}
