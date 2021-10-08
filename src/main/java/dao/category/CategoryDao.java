package dao.category;

import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static dao.product.ProductDao.CONNECTION;

public class CategoryDao implements ICategoryDao{

    public static final String SELECT_CATEGORY_BY_ID = "select * from category where id = ?";

    @Override
    public boolean save(Category category) {
        return false;
    }

    @Override
    public boolean edit(int id, Category category) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category findCategory(int id) {
        Category category = null;
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(SELECT_CATEGORY_BY_ID);
        statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
