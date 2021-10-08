package service.product;

import dao.product.ProductDao;
import model.Product;

import java.util.List;

public class ProductService implements IProductService{
    ProductDao productDao = new ProductDao();
    @Override
    public boolean save(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean edit(int id, Product product) {
        return productDao.edit(id, product);
    }

    @Override
    public boolean delete(int id) {
        return productDao.delete(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> findProductName(String name) {
        name = "%" + name + "%";
        return productDao.findProductName(name);
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }
}
