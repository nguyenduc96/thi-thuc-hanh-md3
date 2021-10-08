package dao.category;

import dao.IGeneralDao;
import model.Category;

public interface ICategoryDao extends IGeneralDao<Category> {
    Category findCategory(int id);
}
