package book.services.interfaces;

import book.entities.Category;

public interface CategoryService {

    void saveCategory(Category category);


    int categoriesSize();

    Category findById(Long id);
}
