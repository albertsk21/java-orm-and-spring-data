package bookshopsystem.services.interfaces;


import bookshopsystem.entities.Category;

import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);
    List<Category> getAllCategories();
    Category findCategoryById(Long id);

}
