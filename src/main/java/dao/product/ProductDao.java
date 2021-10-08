package dao.product;

import config.DBConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    public static final Connection CONNECTION = DBConnection.getConnection();
    private static final String ADD_PRODUCT_TO_SQL = "insert into product(name, price, quantity, color, description, categoryId) values (?,?,?,?,?,?);";
    private static final String UPDATE_PRODUCT = "update product set name = ?, price = ?, quantity = ?, color = ?, description = ?, categoryId = ? where  id = ?;";
    private static final String DELETE_PRODUCT = "delete from product where id = ?";
    private static final String SELECT = "select * from product";
    private static final String SEARCH_BY_NAME = "select * from product where name like ?";
    public static final String FIND_BY_ID = "select * from product where id = ?;";

    @Override
    public boolean save(Product product) {
        boolean isSave = false;
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(ADD_PRODUCT_TO_SQL);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategoryId());


            isSave = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSave;
    }

    @Override
    public boolean edit(int id, Product product) {
        boolean isEdit = false;
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategoryId());
            statement.setInt(7, id);

            isEdit = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEdit;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, id);
            isDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(SELECT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findProductName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(SEARCH_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("quantity"),
                resultSet.getString("color"),
                resultSet.getString("description"),
                resultSet.getInt("categoryId")
        );
    }

    @Override
    public Product findProductById(int id) {
        Product product = null;
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = getProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
