package service.product;

import model.Product;
import service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<Product> findProductName(String name);

    Product findProductById(int id);
}
